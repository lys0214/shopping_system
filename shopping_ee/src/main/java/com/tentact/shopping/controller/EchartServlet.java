package com.tentact.shopping.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/chart"})
public class EchartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flag = req.getParameter("flag");
        //跳转数据可视化页
        if ("toChart".equals(flag)) {
            req.getRequestDispatcher("dataShow.jsp").forward(req, resp);
        }
    }
}
