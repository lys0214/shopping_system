package com.tentact.shopping.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(
    dispatcherTypes = {
            //枚举类型，定义常量
            DispatcherType.REQUEST,
            DispatcherType.FORWARD
    },
        urlPatterns = {
            "/admin432432"
        }
)
public class IpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //    获取ip地址
        String ip = servletRequest.getRemoteAddr();
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            servletRequest.setAttribute("msg", "该IP无法登录（拉黑）");
            servletRequest.getRequestDispatcher("login.jsp").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
