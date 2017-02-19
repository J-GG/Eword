package com.eword.dao;

import com.eword.beans.Wordlist;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * A class which enables to interact with the data layer regarding the Wordlist
 * entity.
 */
@Stateless
public class WordlistDAO {

    @PersistenceContext(unitName = "MySQL")
    private EntityManager em;

    /**
     * Find the wordlist associated with the id. The Wordlist doesn't contain
     * the list of words
     *
     * @param wordlistId The id of the wordlist
     * @return The wordlist associated with the id or null if the id doesn't
     * correpond to any wordlist
     */
    public Wordlist find(int wordlistId) {
        Wordlist wordlist;
        wordlist = em.find(Wordlist.class, wordlistId);

        return wordlist;
    }

    /**
     * List all the user's wordlists. The Wordlists don't contain the list of
     * words
     *
     * @param userId The user's id
     * @return The user's list of wordlist
     */
    public List<Wordlist> findAll(int userId) {
        List<Wordlist> list = null;

        Query requete = em.createQuery("SELECT w FROM Wordlist w");
        list = requete.getResultList();

        return list;
    }
}
