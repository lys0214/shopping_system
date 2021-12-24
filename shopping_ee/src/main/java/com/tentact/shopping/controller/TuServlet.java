package com.tentact.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.tentact.shopping.entity.Tu;
import com.tentact.shopping.service.LogLoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "TuServlet",
    urlPatterns = {
        "/tu"
    }
)
public class TuServlet extends HttpServlet {

    private LogLoginService logLoginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //获取标志
        String flag = req.getParameter("flag");
        /*if ("getAll".equals(flag)) {
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
            resp.setHeader("Access-Control-Allow-Origin", "*");
            PrintWriter pw = resp.getWriter();
            pw.write(s);
            pw.close();
        }*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
