package com.tentact.shopping.service;

import com.alibaba.fastjson.JSON;
import com.tentact.shopping.dao.LogLoginDao;
import com.tentact.shopping.entity.LogLogin;
import com.tentact.shopping.entity.Tu;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class LogLoginService {

    private LogLoginDao logLoginDao = new LogLoginDao();

    public List<Tu> selectRoleCounts() {
        return logLoginDao.selectRoleCounts();
    }

    public List<Tu> selectTimes() {
        return logLoginDao.selectTimes();
    }

    public List<Tu> selectDayTimes() {
        return logLoginDao.selectDayTimes();
    }

    public int insert(LogLogin logLogin) {
        return logLoginDao.insert(logLogin);
    }

    public List<LogLogin> selectAll() {
        return logLoginDao.selectAll();
    }

    public int getCountTimes() throws SQLException {
        int rowsCount = logLoginDao.getRowsCount();
        return rowsCount;
    }
    public Integer getMaxTimeCount() throws SQLException {
        return logLoginDao.getMaxTimeCount();
    }
    public int insertTimestamp(Integer times) {
        return logLoginDao.insertTimestamp(times);
    }

    public List<LogLogin> selectByPages(int offer, int pagesize) {
        return logLoginDao.selectByPages(offer, pagesize);
    }

    public static void main(String[] args) {
        LogLoginService logLoginService = new LogLoginService();
        List<Tu> roleCounts = logLoginService.selectRoleCounts();
        List<Tu> selectTimes = logLoginService.selectTimes();
        List<Tu> dayTimes = logLoginService.selectDayTimes();
        HashMap<String, Object> map = new HashMap<>();
        map.put("roleCounts", roleCounts);
        map.put("selectTimes", selectTimes);
        map.put("dayTimes", dayTimes);
        System.out.println(JSON.toJSONString(map));
    }
}
