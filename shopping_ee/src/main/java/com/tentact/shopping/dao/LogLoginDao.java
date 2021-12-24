package com.tentact.shopping.dao;

import com.tentact.shopping.entity.Account;
import com.tentact.shopping.entity.LogLogin;
import com.tentact.shopping.entity.Role;
import com.tentact.shopping.entity.Tu;
import com.tentact.shopping.util.ConnDB;
import jdk.jfr.Timestamp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static javax.swing.UIManager.getString;

public class LogLoginDao extends BaseDAO{

    //查询：每个角色的数量
    public List<Tu> selectRoleCounts(){
        ArrayList<Tu> list = new ArrayList<>();
        Connection connection = ConnDB.getConnection();
        PreparedStatement pst=null;
        ResultSet rs=null;
        String sql="-- 查询：每个角色的数量\n" +
                "SELECT r.rolename,count(*)\n" +
                "FROM account a\n" +
                "LEFT JOIN role r\n" +
                "on a.roleid=r.roleid\n" +
                "GROUP BY a.roleid\n";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Tu tu = new Tu();
                tu.setName(rs.getString(1));
                tu.setValue(rs.getInt(2));
                list.add(tu);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnDB.closeDB(rs,pst,connection);
        }
        return list;
    }
    //统计登录次数前5位：用户名+次数
    public List<Tu> selectTimes() {
        ArrayList<Tu> list = new ArrayList<>();
        Connection connection = ConnDB.getConnection();
        PreparedStatement pst=null;
        ResultSet rs=null;
        String sql="-- 统计登录次数前5位：用户名+次数\n" +
                "SELECT\n" +
                "\ta.accountname,\n" +
                "\tcount(*) \n" +
                "FROM\n" +
                "\tlog l\n" +
                "\tLEFT JOIN account a ON l.account_id = a.accountid \n" +
                "GROUP BY\n" +
                "\ta.accountid \n" +
                "ORDER BY\n" +
                "\tcount(*) DESC \n" +
                "\tLIMIT 0,\n" +
                "\t5";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Tu tu = new Tu();
                tu.setName(rs.getString(1));
                tu.setValue(rs.getInt(2));
                list.add(tu);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnDB.closeDB(rs,pst,connection);
        }
        return list;
    }

    //每日登录次数统计
    public List<Tu> selectDayTimes(){
        ArrayList<Tu> list = new ArrayList<>();
        Connection connection = ConnDB.getConnection();
        PreparedStatement pst=null;
        ResultSet rs=null;
        String sql="-- 查询：每日登录次数总统计\n" +
                "SELECT DATE_FORMAT(time,'%Y-%m-%d'),count(*)\n" +
                "FROM log\n" +
                "GROUP BY DATE_FORMAT(time,'%Y-%m-%d')";
        try {
            pst=connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Tu tu = new Tu();
                tu.setName(rs.getString(1));
                tu.setValue(rs.getInt(2));
                list.add(tu);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnDB.closeDB(rs,pst,connection);
        }
        return list;
    }

    //    添加
    public int insert(LogLogin logLogin) {
        String sql = "insert into log(time,ip,account_id) values(CURRENT_TIMESTAMP,?,?)";
        return super.update(sql, new Object[]{logLogin.getLogIp(), logLogin.getAccountId()});
    }

    //查
    public List<LogLogin> selectAll() {
        String sql = "SELECT *\n" +
                "FROM\n" +
                "\tlog l\n" +
                "\tLEFT JOIN account a ON l.account_id = a.accountid \n" +
                "ORDER BY\n" +
                "\tl.time desc";
        return super.selectAll(sql, new Object[]{});
    }

    //分页查询
    public List<LogLogin> selectByPages(int offer, int pagesize) {
        String sql = "SELECT *\n" +
                "FROM\n" +
                "\tlog l\n" +
                "\tLEFT JOIN account a ON l.account_id = a.accountid \n" +
                "ORDER BY\n" +
                "\tl.time desc limit ?,?";
        return super.selectAll(sql, new Object[]{offer, pagesize});
    }


    //统计次数
    public int getRowsCount() throws SQLException {
    //    获取连接对象
        Connection connection = ConnDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from log");
        ResultSet rs = preparedStatement.executeQuery();
        int count=0;
        while (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }

    //查询最大登录次数
    public Integer getMaxTimeCount() throws SQLException {
        Connection connection = ConnDB.getConnection();
        String sql = "SELECT MAX(timesCount) FROM logtimes";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    //服务器关闭，记录登录次数
    public int insertTimestamp(Integer times) {
        String sql = "insert into logtimes values(0,?,CURRENT_TIMESTAMP)";
        return super.update(sql, new Object[]{times});
    }


    /*测试*/
    public static void main(String[] args) {
        LogLoginDao logLoginDao = new LogLoginDao();
        System.out.println(logLoginDao.selectDayTimes());
    }


    @Override
    public LogLogin rowMapper(ResultSet rs) {
        AccountDAO accountDAO = new AccountDAO();
        try {
            int id = rs.getInt("id");
            String time = rs.getString("time");
            String ip = rs.getString("ip");
            int accountId = rs.getInt("account_id");
            Account account = accountDAO.checkAccountId(Integer.toString(accountId));
            return new LogLogin(id, time, ip, accountId, account);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
