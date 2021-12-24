package com.tentact.shopping.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("会话创建" + se.getSession().getId());
    //    获取上下文对象
        ServletContext servletContext = se.getSession().getServletContext();
    //    判断是否是新的回话
        if (se.getSession().isNew()) {
        //    获取实时在线
            if (servletContext.getAttribute("onLine") == null) {
                servletContext.setAttribute("onLine", 1);
            } else {
                Integer num = (Integer) (servletContext.getAttribute("onLine"));
                servletContext.setAttribute("onLine", num++);
            }
        }
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    //    对话销毁
        System.out.println("对话销毁" + se.getSession().getId());
        ServletContext servletContext = se.getSession().getServletContext();
        //    判断是否是新的回话
        if (servletContext.getAttribute("onLine") == null) {
            servletContext.setAttribute("onLine", 0);
        } else {
            Integer num = (Integer) (servletContext.getAttribute("onLine"));
            if (num <= 0) {
                num = 0;
            } else {
                num--;
            }
            servletContext.setAttribute("onLine", num);
        }
    }
}
