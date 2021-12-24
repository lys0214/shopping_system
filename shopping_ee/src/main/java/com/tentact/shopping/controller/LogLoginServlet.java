package com.tentact.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.tentact.shopping.entity.LogLogin;
import com.tentact.shopping.entity.Page;
import com.tentact.shopping.entity.Tu;
import com.tentact.shopping.service.AccountService;
import com.tentact.shopping.service.LogLoginService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet(
        urlPatterns = {"/log"}
)
public class LogLoginServlet extends HttpServlet {
    private LogLoginService logLoginService = new LogLoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flag = req.getParameter("flag");
        if ("selectAll".equals(flag)) {
        //   查找全部数据
            List<LogLogin> logLogins = logLoginService.selectAll();
            req.setAttribute("logs", logLogins);
            req.getRequestDispatcher("log_info.jsp").forward(req, resp);
        }
    //    分页显示
        if ("showByPage".equals(flag)) {
            //总数据条目
            int countTimes = 0;
            try {
                countTimes = logLoginService.getCountTimes();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //每页显示数量
            ServletContext servletContext = req.getServletContext();
            int pageSize = 10;
            //一共多少页
            int pageNum = 0;
            if (countTimes % pageSize == 0) {
                pageNum = countTimes / pageSize;
            }else {
                pageNum = countTimes / pageSize+1;
            }
            //获取当前页数
            int currentPage;
            String current = req.getParameter("current");
            if (current != null) {
                currentPage = Integer.parseInt(current);
            } else {
                currentPage = 1;
            }
            //要显示页的第一条数据位置
            int offset = (currentPage - 1) * pageSize;
            //查找改页要显示的数据
            List<LogLogin> logLogins = logLoginService.selectByPages(offset,pageSize);

            //Page page = new Page().pageCount(pageNum).allRecord(countTimes).showPage(currentPage);
            Page page = new Page();
            page.setAllRecord(countTimes);
            page.setPageCount(pageNum);
            page.setShowPage(currentPage);
            req.setAttribute("logs", logLogins);
            req.setAttribute("pages", page);
            req.getRequestDispatcher("log_info.jsp").forward(req, resp);
        }
        if ("getAll".equals(flag)) {
            //    查询数据
            List<Tu> roleCounts = logLoginService.selectRoleCounts();
            List<Tu> selectTimes = logLoginService.selectTimes();
            List<Tu> dayTimes = logLoginService.selectDayTimes();
            HashMap<String, Object> map = new HashMap<>();
            map.put("roleCounts", roleCounts);
            map.put("selectTimes", selectTimes);
            map.put("dayTimes", dayTimes);
            //    将map转成json
            String s = JSON.toJSONString(map);
            System.out.println(s);
            
            //    设置相应头
            resp.setHeader("Context-type", "text/plain,charset=utf-8");
            PrintWriter pw = resp.getWriter();
            pw.write(s);
            pw.close();

        }
    //
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
