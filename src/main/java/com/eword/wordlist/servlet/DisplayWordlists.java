package com.eword.wordlist.servlet;

import com.eword.wordlist.bean.Wordlist;
import com.eword.wordlist.business.WordlistBusiness;
import com.eword.wordlist.dao.WordlistDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lists")
public class DisplayWordlists extends HttpServlet {

    /**
     * Name of the request attribute containing the Map
     */
    private static final String ATT_MAP_BY_LANGUAGES = "mapByLanguages";

    /**
     * JSP file to display
     */
    private static final String VIEW = "/WEB-INF/wordlists.jsp";

    /**
     * Object enabling to communicate with the Wordlist data layer
     */
    @EJB
    private WordlistDAO wordlistDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //a Map of Wordlist objects sorted by languages is retrieved and set into a request attribute
        Map<ArrayList<String>, ArrayList<Wordlist>> mapByLanguages = WordlistBusiness.mapByLanguages(wordlistDAO, req);
        req.setAttribute(ATT_MAP_BY_LANGUAGES, mapByLanguages);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

}
