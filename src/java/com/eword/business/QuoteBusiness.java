package com.eword.business;

import com.eword.beans.Quote;
import com.eword.dao.DAOFactory;
import com.eword.dao.interfaces.QuoteDAO;

public class QuoteBusiness {

    private static final QuoteDAO QUOTE_DAO = DAOFactory.getInstance().getQuoteDAO();

    public static Quote getRandomQuote() {
        Quote quote = QUOTE_DAO.findRandom();

        return quote;
    }
}
