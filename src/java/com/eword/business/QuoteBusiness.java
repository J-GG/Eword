package com.eword.business;

import com.eword.beans.Quote;
import com.eword.dao.QuoteDAO;
import javax.ejb.EJB;

public class QuoteBusiness {

    /**
     * Object enabling to communicate with the Quote data layer
     */
    @EJB
    private QuoteDAO quoteDAO;

    /**
     * Return a random Quote
     *
     * @return A random Quote
     */
    public Quote getRandomQuote() {

        if (quoteDAO == null) {
            System.out.println("h");
        } else {
            System.out.println("oui");
        }
        Quote quote = quoteDAO.findRandom();

        return quote;
    }
}
