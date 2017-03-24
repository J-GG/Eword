package com.eword.general.filter;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class NavbarFilter implements Filter {

    /**
     * Name of the request attribute indicating on which tab the user is
     */
    private static final String ATT_NAVBAR_NAME = "navbar";

    /**
     * Value of the request attribute indicating that the user in on the home
     * tab
     */
    private static final String NAVBAR_HOME = "home";

    /**
     * Value of the request attribute indicating that the user in on the lists
     * tab
     */
    private static final String NAVBAR_LISTS = "lists";

    /**
     * Value of the request attribute indicating that the user in on the sign up
     * tab
     */
    private static final String NAVBAR_SIGNUP = "signup";

    /**
     * Value of the request attribute indicating that the user in on the
     * membership tab
     */
    private static final String NAVBAR_MEMBERSHIP = "membership";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //We compare the URL to patterns to determine on which tab the user is
        String context = req.getContextPath();
        Pattern pattern_home = Pattern.compile(context + "/$");
        Pattern pattern_lists = Pattern.compile(context + "/lists(/?)([0-9]*)$");
        Pattern pattern_signup = Pattern.compile(context + "/signup$");
        Pattern pattern_membership = Pattern.compile(context + "/membership");

        String navbar = null;
        if (pattern_home.matcher(req.getRequestURI()).find()) {
            navbar = NAVBAR_HOME;
        } else if (pattern_lists.matcher(req.getRequestURI()).find()) {
            navbar = NAVBAR_LISTS;
        } else if (pattern_signup.matcher(req.getRequestURI()).find()) {
            navbar = NAVBAR_SIGNUP;
        } else if (pattern_membership.matcher(req.getRequestURI()).find()) {
            navbar = NAVBAR_MEMBERSHIP;
        }

        //The variable is set into a request attribute
        request.setAttribute(ATT_NAVBAR_NAME, navbar);

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
