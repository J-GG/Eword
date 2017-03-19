package com.eword.quote.business;

import com.eword.quote.dao.QuoteDAO;
import com.eword.quote.bean.Quote;

/**
 * A class which gathers the business functions about Quotes.
 */
public class QuoteBusiness {

    /**
     * Return a random Quote
     *
     * @param quoteDAO Object enabling to communicate with the data layer
     * @return A random Quote
     */
    public static Quote getRandomQuote(QuoteDAO quoteDAO) {

        return quoteDAO.findRandom();
    }
}
