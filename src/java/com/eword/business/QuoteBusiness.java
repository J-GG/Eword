package com.eword.business;

import com.eword.beans.Quote;
import com.eword.dao.DAOFactory;
import com.eword.dao.interfaces.QuoteDAO;

public class QuoteBusiness {

    /**
     * Object enabling to communicate with the Quote data layer
     */
    private static final QuoteDAO QUOTE_DAO = DAOFactory.getInstance().getQuoteDAO();

    /**
     * Return a random Quote
     *
     * @return A random Quote
     */
    public static Quote getRandomQuote() {

        Quote quote = QUOTE_DAO.findRandom();

        return quote;
    }
}
