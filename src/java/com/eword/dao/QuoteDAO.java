package com.eword.dao;

import com.eword.beans.Quote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        return new Quote();
    }
}
