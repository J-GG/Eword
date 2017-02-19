package com.eword.dao;

import com.eword.beans.Quote;
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
     * Returns a random Quote
     *
     * @return a Quote object selected randomly
     */
    public Quote findRandom() {
        Quote quote = null;

        Query requete = em.createQuery("SELECT q FROM Quote q");
        if (!requete.getResultList().isEmpty()) {
            //A random number is generated from the number of quotes
            long nbQuotes = requete.getResultList().size();
            int quoteRandom = ThreadLocalRandom.current().nextInt(0, (int) (nbQuotes));

            //We search for the random-number-th quote
            quote = (Quote) requete.getResultList().get(quoteRandom);
        }

        return quote;
    }
}
