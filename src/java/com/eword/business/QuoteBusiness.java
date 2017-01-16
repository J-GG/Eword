package com.eword.business;

import com.eword.beans.Quote;
import com.eword.dao.interfaces.QuoteDAO;

public class QuoteBusiness {

    public static Quote getRandomQuote(QuoteDAO quoteDAO) {
        Quote quote = quoteDAO.findRandom();

        return quote;
    }
}
