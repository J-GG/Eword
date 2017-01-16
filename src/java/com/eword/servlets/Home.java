package com.eword.servlets;

import com.eword.beans.Quote;
import com.eword.business.QuoteBusiness;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends HttpServlet {

    private static final String VIEW = "/WEB-INF/index.jsp";
    private static final String ATT_QUOTE = "quote";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Quote quote = QuoteBusiness.getRandomQuote();
        req.setAttribute(ATT_QUOTE, quote);
        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

}
