package com.eword.dao;

import com.eword.beans.Quote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

        Query requete = em.createQuery("SELECT q FROM Quote q WHERE q.id=:id");
        requete.setParameter("id", 1);
        quote = (Quote) requete.getSingleResult();

        return quote;
    }
}
