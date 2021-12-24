package com.tentact.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tentact.shopping.entity.Province;
import com.tentact.shopping.service.ProvinceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(
        urlPatterns = {"/pro"}
)
public class ProvinceServlet extends HttpServlet {
    private ProvinceService provinceService = new ProvinceService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flag = req.getParameter("flag");
        String str = "";
        if ("selectPro".equals(flag)) {
            List<Province> provinces = provinceService.selectAll();
            System.out.println(provinces);
            str = JSONArray.toJSONString(provinces);

        } else if ("selectCity".equals(flag)) {
            System.out.println("select city");
        }
        resp.setHeader("Context-type", "text/plain,charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(str);
        writer.close();
    }
}
