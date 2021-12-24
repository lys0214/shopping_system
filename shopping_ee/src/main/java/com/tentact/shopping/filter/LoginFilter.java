package com.tentact.shopping.filter;

import com.tentact.shopping.entity.Account;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        dispatcherTypes = {DispatcherType.REQUEST},
        urlPatterns = {"/admin/*"}
)
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    //    通过session中的属性来判断是否登录成功
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Account loginAccount=(Account) session.getAttribute("account");
        //是否登录
        if (loginAccount== null) {
            request.setAttribute("msg", "请登录后尝试");
            request.getRequestDispatcher("../login.jsp").forward(request, servletResponse);
        }
        //是否是管理员
        else if (loginAccount.getRoleId() != 1) {
            request.setAttribute("msg", "请以管理员身份登录");
            request.getRequestDispatcher("../login.jsp").forward(request, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
