package com.eword.membership.filter;

import com.eword.general.util.RequestUtils;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This filter checks if the user is NOT connected. If they are, they are
 * redirected.
 */
@WebFilter("/signup")
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

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //We get the adress to which the user will be redirected
        String address = RequestUtils.getPreviousPage(req);

        //If the user is connected, they are redirected
        if (req.getSession().getAttribute(ATT_USER_ID) != null) {
            res.sendRedirect(address);
            return;
        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
