package com.eword.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggedinFilter implements Filter {

    /**
     * Name of the session attribute containing the user's id
     */
    private static final String ATT_USER_ID = "user_id";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //Parameters are extracted from the request
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //Creation of the path for the redirection. It is either the previous page or the home page
        String address = req.getHeader("referer");
        if (address == null) {
            address = req.getContextPath();
        }

        //If the user is connected, they are redirected
        if (req.getSession().getAttribute(ATT_USER_ID) != null) {
            res.sendRedirect(address);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
