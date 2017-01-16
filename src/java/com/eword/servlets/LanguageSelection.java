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

    private final static String ATT_LANG = "language";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address = req.getHeader("referer");
        if (address == null) {
            address = req.getContextPath();
        }

        String context = req.getContextPath();
        Matcher matcher = Pattern.compile(context + "/lang/([a-z]{2})$").matcher(req.getRequestURI());

        if (!matcher.find()) {
            resp.sendRedirect(address);
        }

        String lang = matcher.group(1);

        HttpSession session = req.getSession();
        session.setAttribute(ATT_LANG, lang);

        resp.sendRedirect(address);
    }
}
