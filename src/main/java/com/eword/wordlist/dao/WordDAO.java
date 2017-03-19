package com.eword.wordlist.dao;

import com.eword.wordlist.bean.Word;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * A class which enables to interact with the data layer regarding the Word
 * entity.
 */
@Stateless
public class WordDAO {

    @PersistenceContext(unitName = "MySQL")
    private EntityManager em;

    /**
     * Find all the Words of the wordlist from its id
     *
     * @param wordlistId The id of the wordlist
     * @return a list of words or null if the id doesn't correspond to any
     * wordlist
     */
    public List<Word> findAll(int wordlistId) {
        List<Word> list = null;

        Query requete = em.createQuery("SELECT w FROM Word w WHERE w.id=:id");
        requete.setParameter("id", wordlistId);

        if (!requete.getResultList().isEmpty()) {
            list = requete.getResultList();
        }

        return list;
    }
}
