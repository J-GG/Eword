package com.eword.servlets;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LanguageSelection extends HttpServlet {

    /**
     * Name of the request attribute containing the language of the user
     */
    private final static String ATT_LANG = "language";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Creation of the path for the redirection. It is either the previous page or the home page
        String address = req.getHeader("referer");
        if (address == null) {
            address = req.getContextPath();
        }

        //Verification that the URL is correct. if not, the user is redirected
        String context = req.getContextPath();
        Matcher matcher = Pattern.compile(context + "/lang/([a-z]{2})$").matcher(req.getRequestURI());

        if (!matcher.find()) {
            resp.sendRedirect(address);
        }

        //The language is extracted from the URL and inserted into a request attribute
        String lang = matcher.group(1);

        HttpSession session = req.getSession();
        session.setAttribute(ATT_LANG, lang);

        //The user is redirected
        resp.sendRedirect(address);
    }
}
