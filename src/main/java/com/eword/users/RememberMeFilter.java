package com.eword.users;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class RememberMeFilter implements Filter {

    /**
     * Name of the session attribute containing the id of the user
     */
    private static final String ATT_USER_ID = "user_id";

    /**
     * Name of the cookie storing the id
     */
    private static final String COOKIE_ID_NAME = "id";
    /**
     * Name of the cookie storing the token
     */
    private static final String COOKIE_TOKEN_NAME = "token";

    /**
     * Object enabling to communicate with the User data layer
     */
    @EJB
    private UserDAO userDAO;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //We try to log in the user only if it has not been logged in yet
        HttpSession session = req.getSession();
        if (session.getAttribute(ATT_USER_ID) == null) {
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

            if (hashedId != null && token != null) {
                //We check if the information of the cookie matches a user. If it is the case, the user is authenticated
                User user = AuthenticationBusiness.checkRememberMe(hashedId, token, userDAO);
                if (user != null) {
                    AuthenticationBusiness.authenticatedUser(user, true, userDAO, req, res);
                }
            }
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
