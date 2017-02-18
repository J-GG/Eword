package com.eword.servlets;

import com.eword.beans.Quote;
import com.eword.business.QuoteBusiness;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class Home extends HttpServlet {

    /**
     * Name of the request attribute containing a Quote object
     */
    private static final String ATT_QUOTE = "quote";

    /**
     * JSP file to display
     */
    private static final String VIEW = "/WEB-INF/index.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //A random quote is selected and set as a request attribute
        QuoteBusiness quoteBusiness = new QuoteBusiness();
        Quote quote = quoteBusiness.getRandomQuote();
        req.setAttribute(ATT_QUOTE, quote);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

}
