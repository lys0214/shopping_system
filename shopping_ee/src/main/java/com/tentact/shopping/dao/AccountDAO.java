package com.tentact.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.mysql.cj.jdbc.admin.MiniAdmin;
import com.tentact.shopping.entity.Account;
import com.tentact.shopping.entity.Role;
import com.tentact.shopping.util.ConnDB;

// 数据持久层  操作数据
public class AccountDAO extends BaseDAO {
	
	// 查询所有数据
	public List<Account> selectAll() {
		String sql="SELECT \r\n"
				+ "ta.accountid,\r\n"
				+ "ta.accountname,\r\n"
				+ "ta.accountpwd,\r\n"
				+ "ta.accountrealname,\r\n"
				+ "ta.accountbirthday,\r\n"
				+ "ta.accountphone,\r\n"
				+ "ta.roleid,\r\n"
				+ "ta.createtime,\r\n"
				+ "ta.createauthor,\r\n"
				+ "ta.updatetime,\r\n"
				+ "ta.updateauthor,\r\n"
				+ "ta.del_flag,\r\n"
				+ "tr.rolename,\r\n"
				+ "tr.rolememo\r\n"
				+ "FROM account ta\r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "role tr\r\n"
				+ "ON\r\n"
				+ "ta.roleid = tr.roleid\r\n"
				+ "WHERE  "
				+ "  ta.del_flag='0' "
				+ " order by ta.updatetime  desc";
		Object[] arr = {};
		return super.selectAll(sql, arr);
	}

	//通过角色名查询
	public List<Account> selectAllByRoleId(int roleId) {
		String sql="SELECT \r\n"
				+ "ta.accountid,\r\n"
				+ "ta.accountname,\r\n"
				+ "ta.accountpwd,\r\n"
				+ "ta.accountrealname,\r\n"
				+ "ta.accountbirthday,\r\n"
				+ "ta.accountphone,\r\n"
				+ "ta.roleid,\r\n"
				+ "ta.createtime,\r\n"
				+ "ta.createauthor,\r\n"
				+ "ta.updatetime,\r\n"
				+ "ta.updateauthor,\r\n"
				+ "ta.del_flag,\r\n"
				+ "tr.rolename,\r\n"
				+ "tr.rolememo\r\n"
				+ "FROM account ta\r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "role tr\r\n"
				+ "ON\r\n"
				+ "ta.roleid = tr.roleid\r\n"
				+ "WHERE  "
				+ "  ta.del_flag='0'  and ta.roleid=? "
				+ " order by ta.updatetime  desc";
		Object[] arr = {roleId};
		return super.selectAll(sql, arr);
	}
	// 删除
	public boolean delete(String accountId) {
		String sql="update taccount set del_flag='1',updatetime=to_char(sysdate,'yyyymmddhh24miss') where accountid=?";
		Object[] arr= {accountId};
		int row  =  super.update(sql, arr);
		if(row==1) {
			return true;
		}
		return false;
	}
	// 通过账户名查询
	public List<Account> select(String accountName) {
		String sql="SELECT \r\n"
				+ "ta.accountid,\r\n"
				+ "ta.accountname,\r\n"
				+ "ta.accountpwd,\r\n"
				+ "ta.accountrealname,\r\n"
				+ "to_char(ta.accountbirthday,'yyyy-mm-dd') as birthday ,\r\n"
				+ "ta.accountphone,\r\n"
				+ "ta.roleid,\r\n"
				+ "ta.createtime,\r\n"
				+ "ta.createauthor,\r\n"
				+ "ta.updatetime,\r\n"
				+ "ta.updateauthor,\r\n"
				+ "ta.del_flag,\r\n"
				+ "tr.rolename,\r\n"
				+ "tr.rolememo\r\n"
				+ "FROM taccount ta\r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "trole tr\r\n"
				+ "ON\r\n"
				+ "ta.roleid = tr.roleid\r\n"
				+ "WHERE  "
				+ " ta.del_flag='0' "
				+ " and ta.accountname like ?"
				+ " order by ta.updatetime  desc";
		Object[] arr = {"%"+accountName+"%"};
		return super.selectAll(sql, arr);
	}
	// 登录方法
	public  Account login(Account account) {
		String sql="SELECT \r\n"
				+ "ta.accountid,\r\n"
				+ "ta.accountname,\r\n"
				+ "ta.accountpwd,\r\n"
				+ "ta.accountrealname,\r\n"
				+ "ta.accountbirthday ,\r\n"
				+ "ta.accountphone,\r\n"
				+ "ta.roleid,\r\n"
				+ "ta.createtime,\r\n"
				+ "ta.createauthor,\r\n"
				+ "ta.updatetime,\r\n"
				+ "ta.updateauthor,\r\n"
				+ "ta.del_flag,\r\n"
				+ "tr.rolename,\r\n"
				+ "tr.rolememo\r\n"
				+ "FROM account ta\r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "role tr\r\n"
				+ "ON\r\n"
				+ "ta.roleid = tr.roleid\r\n"
				+ "WHERE ta.accountname =? AND ta.accountpwd = ? \r\n"
				+ "AND ta.del_flag='0' ";
		Object[] arr = {account.getAccountName(),account.getAccountPwd()};
		return  super.select(sql, arr);
	}
	
	// 查询账户名是否存在
	public boolean checkAccountName(String accountName) {
		Connection conn = ConnDB.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql =" select * from account where accountname =?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, accountName);
			rs = pst.executeQuery();
			if(rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnDB.closeDB(rs, pst, conn);
		}
		return true;
	}
	// 修改方法
	public boolean update(Account account) {
		String sql="update account set accountpwd=?,accountphone=?,accountrealname=?,"
				+ "accountbirthday=?,updatetime=CURRENT_TIME,updateauthor=?,roleid=?"
				+ " where accountid=?";
		Object[] arr =  {account.getAccountPwd(),account.getAccountPhone(),account.getAccountRealName(),
				account.getAccountBirthday(),account.getUpdateAuthor(),account.getRoleId(),account.getAccountId()};
		int row  =  super.update(sql, arr);
		if(row==1) {
			return true;
		}
		return false;
	}
	// 注册方法
	public boolean register(Account account) {
		String sql="insert into account(accountname,accountpwd,accountrealname,"
				+ "accountbirthday,accountphone,roleid,createtime,createauthor,del_flag) values(?,?,?,?,?,?,CURRENT_TIME,?,0)";
		Object[] arr = {account.getAccountName(),account.getAccountPwd(),account.getAccountRealName()
				,account.getAccountBirthday(),account.getAccountPhone(),2,"小李"};
		int row  =  super.update(sql, arr);
		if(row==1) {
			return true;
		}
		return false;
	}

	//手动添加用户
	public boolean add(Account account,String manageName) {
		String sql="insert into account(accountname,accountpwd,accountrealname,"
				+ "accountbirthday,accountphone,roleid,createtime,createauthor,del_flag) values(?,?,?,?,?,?,CURRENT_TIME,?,0)";
		Object[] arr = {account.getAccountName(),account.getAccountPwd(),account.getAccountRealName()
				,account.getAccountBirthday(),account.getAccountPhone(),account.getRoleId(),manageName};
		int row  =  super.update(sql, arr);
		if(row==1) {
			return true;
		}
		return false;
	}


	// 查询id
	public Account checkAccountId(String accountId) {
		String sql="SELECT \r\n"
				+ "ta.accountid,\r\n"
				+ "ta.accountname,\r\n"
				+ "ta.accountpwd,\r\n"
				+ "ta.accountrealname,\r\n"
				+ "ta.accountbirthday,\r\n"
				+ "ta.accountphone,\r\n"
				+ "ta.roleid,\r\n"
				+ "ta.createtime,\r\n"
				+ "ta.createauthor,\r\n"
				+ "ta.updatetime,\r\n"
				+ "ta.updateauthor,\r\n"
				+ "ta.del_flag,\r\n"
				+ "tr.rolename,\r\n"
				+ "tr.rolememo\r\n"
				+ "FROM account ta\r\n"
				+ "LEFT OUTER JOIN \r\n"
				+ "role tr\r\n"
				+ "ON\r\n"
				+ "ta.roleid = tr.roleid\r\n"
				+ "WHERE ta.accountid =? "
				+ "AND ta.del_flag='0' ";
		Object[] arr= {accountId};
		return super.select(sql, arr);
	}
		
	// main是主程序的入口  主要作用 测试当前类
	public static void main(String[] args) {
		AccountDAO accountDAO = new AccountDAO();
		/*List<Account> accounts = accountDAO.selectAllByRoleId(3);
		for (Account account1 : accounts) {
			System.out.println(account1);
		}*/
	//	修改
	//	Account a = new Account().accountRealName("小玲").accountPwd("123").accountId("3");
	//	accountDAO.update(a);
	//	测试用户名是否可用
	//	System.out.println(accountDAO.checkAccountName("admin1"));

	//	添加用户
		Account account = new Account(null, "shu", "shu", "shu", "2020-10-20", "234512", new Role().roleId(2));
		boolean register = accountDAO.add(account,"张三");

	}
	@Override
	public Account rowMapper(ResultSet rs) {
		Account account = new Account();
		try {
			account.setAccountId(rs.getString("accountid"));
			account.setAccountName(rs.getString("accountname"));
			account.setAccountPwd(rs.getString("accountpwd"));
			account.setAccountBirthday(rs.getString("accountbirthday"));
			account.setAccountPhone(rs.getString("accountphone"));
			account.setAccountRealName(rs.getString("accountrealname"));
			Role role = new Role();
			role.setRoleId(rs.getInt("roleid"));
			role.setRoleName(rs.getString("rolename"));
			role.setRoleMemo(rs.getString("rolememo"));
			account.setRole(role);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}
}
