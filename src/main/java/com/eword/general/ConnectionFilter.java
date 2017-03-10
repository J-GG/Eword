package com.eword.general;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.transaction.UserTransaction;

@WebFilter("/*")
public class ConnectionFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Resource
    private UserTransaction utx;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            utx.begin();
            chain.doFilter(request, response);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}