package com.tentact.shopping.controller;

import com.tentact.shopping.entity.Role;
import com.tentact.shopping.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/role"})
public class RoleServlet extends HttpServlet {
    private RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flag = req.getParameter("flag");
        if ("selectAll".equals(flag)) {
            List<Role> roles = roleService.selectAll();
            req.setAttribute("roles", roles);
            req.getRequestDispatcher("role_info.jsp").forward(req, resp);
        }
    }
}
