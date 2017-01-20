package com.eword.filters;

import com.eword.beans.User;
import com.eword.business.AuthenticationBusiness;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RememberMeFilter implements Filter {

    /**
     * Name of the cookie storing the token
     */
    private static final String COOKIE_TOKEN_NAME = "token";

    /**
     * Name of the cookie storing the id
     */
    private static final String COOKIE_ID_NAME = "id";
    /**
     * Name of the session attribute containing the translations
     */
    private static final String ATT_LANG = "lang";

    /**
     * Name of the session attribute containing the id of the user
     */
    private static final String ATT_USER_ID = "user_id";
    /**
     * Max age of the cookie set to 1 year
     */
    private static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 365;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String hashedId = null;
        String token = null;

        //We retrieve the cookies we are interested in
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null) {
                    if (COOKIE_ID_NAME.equals(cookie.getName())) {
                        hashedId = cookie.getValue();
                    } else if (COOKIE_TOKEN_NAME.equals(cookie.getName())) {
                        token = cookie.getValue();
                    }
                }
            }
        }

        //We check if the information of the cookie matches a user
        User user = AuthenticationBusiness.checkRememberMe(hashedId, token);
        if (user != null) {
            AuthenticationBusiness.authenticatedUser(user, true, req, res);
        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
