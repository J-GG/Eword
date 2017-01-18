package com.eword.servlets;

import java.io.IOException;
import java.util.Arrays;
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

    private final static String[] LANGUAGES = {"us", "fr"};

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Creation of the path for the redirection. It is either the previous page or the home page
        String address = req.getHeader("referer");
        String context = req.getContextPath();
        if (address == null) {
            address = context;
        }

        //Verification that the URL is correct. if not, the user is redirected
        Matcher matcher = Pattern.compile(context + "/lang/([a-z]{2})$").matcher(req.getRequestURI());

        if (!matcher.find()) {
            resp.sendRedirect(address);
            return;
        }

        //The language is extracted from the URL and inserted into a request attribute
        String lang = matcher.group(1);

        //If the extracted language doesn't belong to the list, the user is redirected
        if (!Arrays.asList(LANGUAGES).contains(lang)) {
            resp.sendRedirect(address);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute(ATT_LANG, lang);

        //The user is redirected
        resp.sendRedirect(address);
    }
}
