package com.eword.wordlist.servlet;

import com.eword.wordlist.dao.WordDAO;
import com.eword.wordlist.bean.Wordlist;
import com.eword.wordlist.business.WordlistBusiness;
import com.eword.wordlist.dao.WordlistDAO;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lists/*")
public class DisplayWordlist extends HttpServlet {

    /**
     * JSP file to display
     */
    private static final String VIEW = "/WEB-INF/wordlist.jsp";

    /**
     * Name of the request attribute containing a Wordlist object
     */
    private static final String ATT_WORDLIST = "wordlist";

    /**
     * Object enabling to communicate with the Wordlist data layer
     */
    @EJB
    private WordlistDAO wordlistDAO;

    /**
     * Object enabling to communicate with the Word data layer
     */
    @EJB
    private WordDAO wordDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Verification that the URL is correct
        String context = req.getContextPath();
        Matcher matcher = Pattern.compile(context + "/lists/([0-9]+)$").matcher(req.getRequestURI());

        if (!matcher.find()) {
            //If the URL is not correct, the user is redirected
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {

            //The id of the list is extracted from the URL
            int wordlistId = Integer.parseInt(matcher.group(1));

            //A wordlist object containing the list of words is retrieved from the id of the wordlist and set into a request attribute
            Wordlist wordlist = WordlistBusiness.getWordlist(wordlistId, wordlistDAO, wordDAO, req);
            req.setAttribute(ATT_WORDLIST, wordlist);

            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        }
    }
}
