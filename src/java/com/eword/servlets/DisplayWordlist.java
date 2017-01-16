package com.eword.servlets;

import com.eword.beans.Wordlist;
import com.eword.business.WordlistBusiness;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayWordlist extends HttpServlet {

    private static final String VIEW = "/WEB-INF/wordlist.jsp";
    private static final String ATT_WORDLIST = "wordlist";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String context = req.getContextPath();
        Matcher matcher = Pattern.compile(context + "/lists/([0-9]+)$").matcher(req.getRequestURI());

        if (!matcher.find()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            int wordlistId = Integer.parseInt(matcher.group(1));
            Wordlist wordlist = WordlistBusiness.getPopulatedWordlist(wordlistId);
            req.setAttribute(ATT_WORDLIST, wordlist);
            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);

        }
    }

}
