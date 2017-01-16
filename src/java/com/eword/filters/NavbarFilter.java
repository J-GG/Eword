package com.eword.filters;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NavbarFilter implements Filter {

    private static final String ATT_NAVBAR_HOME = "home";
    private static final String ATT_NAVBAR_LISTS = "lists";
    private static final String ATT_NAVBAR_NAME = "navbar";
    private static final String ATT_NAVBAR_SIGNUP = "signup";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String context = req.getContextPath();
        Pattern pattern_home = Pattern.compile(context + "/$");
        Pattern pattern_lists = Pattern.compile(context + "/lists(/?)([0-9]*)$");
        Pattern pattern_signup = Pattern.compile(context + "/signup$");
        String navbar = null;

        if (pattern_home.matcher(req.getRequestURI()).find()) {
            navbar = ATT_NAVBAR_HOME;
        } else if (pattern_lists.matcher(req.getRequestURI()).find()) {
            navbar = ATT_NAVBAR_LISTS;
        } else if (pattern_signup.matcher(req.getRequestURI()).find()) {
            navbar = ATT_NAVBAR_SIGNUP;
        }

        request.setAttribute(ATT_NAVBAR_NAME, navbar);

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
