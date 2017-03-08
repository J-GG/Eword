package com.eword.users;

import com.eword.general.RequestUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signout")
public class Signout extends HttpServlet {

    /**
     * Name of the cookie storing the token
     */
    private static final String COOKIE_TOKEN_NAME = "token";

    /**
     * Name of the cookie storing the id
     */
    private static final String COOKIE_ID_NAME = "id";

    /**
     * Max age of the cookie set to 0 second
     */
    private static final int COOKIE_MAX_AGE = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //The session is destroyed
        HttpSession session = req.getSession();
        session.invalidate();

        //Cookies are destroyed
        Cookie cookieId = new Cookie(COOKIE_ID_NAME, null);
        cookieId.setMaxAge(COOKIE_MAX_AGE);
        cookieId.setPath(req.getContextPath());
        resp.addCookie(cookieId);

        Cookie cookieToken = new Cookie(COOKIE_TOKEN_NAME, null);
        cookieToken.setMaxAge(COOKIE_MAX_AGE);
        cookieToken.setPath(req.getContextPath());
        resp.addCookie(cookieToken);

        //We get the adress to which the user will be redirected
        String address = RequestUtils.getPreviousPage(req);

        resp.sendRedirect(address);
    }
}
