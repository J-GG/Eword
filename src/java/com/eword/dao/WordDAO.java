package com.eword.dao;

import com.eword.beans.Word;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class WordDAO {

    @PersistenceContext(unitName = "MySQL")
    private EntityManager em;

    /**
     * Find all the Words of the wordlist from its id
     *
     * @param wordlistId The id of the wordlist
     * @return a list of words or null if the id doesn't correpond to any
     * wordlist
     */
    List<Word> findAll(int wordlistId) {
        List<Word> list = null;

        Query requete = em.createNamedQuery("SELECT w FROM Word w");
        list = requete.getResultList();

        return list;
    }
}
