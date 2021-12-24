package com.tentact.shopping.listener;

import com.tentact.shopping.service.LogLoginService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.desktop.ScreenSleepListener;
import java.sql.SQLException;
import java.util.logging.Logger;

public class LogLoginListener implements ServletContextListener {
    LogLoginService logLoginService = new LogLoginService();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("启动");
    //  服务器启动，自动去查询登录次数，再有人登录就登录次数 +1
        ServletContext servletContext = sce.getServletContext();
        LogLoginService logLoginService = new LogLoginService();
        try {
            Integer times=logLoginService.getMaxTimeCount();
        //    将登录次数放置到上下文对象属性中
            servletContext.setAttribute("countTimes", times);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("销毁");
    //    服务器关闭，记录登录次数
        ServletContext servletContext = sce.getServletContext();
        try {
            int countTimes1 = logLoginService.getCountTimes();
            logLoginService.insertTimestamp(countTimes1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
