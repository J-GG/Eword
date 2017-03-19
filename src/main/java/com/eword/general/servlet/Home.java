package com.eword.general.servlet;

import com.eword.quote.bean.Quote;
import com.eword.quote.business.QuoteBusiness;
import com.eword.quote.dao.QuoteDAO;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet managing the home page.
 */
@WebServlet("/home")
public class Home extends HttpServlet {

    /**
     * Name of the request attribute containing a Quote object
     */
    private static final String ATT_QUOTE = "quote";

    /**
     * JSP file to be displayed
     */
    private static final String VIEW = "/WEB-INF/index.jsp";

    @EJB
    private QuoteDAO quoteDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //A random quote is selected and set as a request attribute
        Quote quote = QuoteBusiness.getRandomQuote(quoteDAO);
        req.setAttribute(ATT_QUOTE, quote);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

}
