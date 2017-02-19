package com.eword.dao;

import com.eword.beans.Quote;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * A class which enables to interact with the data layer regarding the Quote
 * entity.
 */
@Stateless
public class QuoteDAO {

    @PersistenceContext(unitName = "MySQL")
    private EntityManager em;

    /**
     * Return all the Quotes
     *
     * @return a List of all the Quotes
     */
    public List<Quote> findAll() {
        List list;

        Query request = em.createQuery("SELECT q FROM Quote q");
        list = request.getResultList();

        return list;
    }

    /**
     * Return a random Quote
     *
     * @return a Quote object selected randomly or null if there is no Quote
     */
    public Quote findRandom() {
        Quote quote = null;

        //If the list is not empty, a random quote is picked from the list
        List<Quote> list = findAll();
        if (!list.isEmpty()) {
            //A random number is generated from the number of quotes
            long nbQuotes = list.size();
            int quoteRandom = ThreadLocalRandom.current().nextInt(0, (int) (nbQuotes));

            //We get the random-number-th quote
            quote = (Quote) list.get(quoteRandom);
        }

        return quote;
    }
}
