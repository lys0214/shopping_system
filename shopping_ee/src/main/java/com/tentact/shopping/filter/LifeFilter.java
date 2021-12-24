package com.tentact.shopping.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(
        description = "测试过滤器",
        dispatcherTypes = {DispatcherType.REQUEST},
        urlPatterns = {"/1233424"},
        initParams = {
                @WebInitParam(name = "start", value = "10")
        }

)
public class LifeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFileter");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
