package com.eword.dao.interfaces;

import com.eword.beans.Wordlist;
import java.util.ArrayList;

public interface WordlistDAO {

    /**
     * Find the wordlist associated with the id
     *
     * @param wordlistId The id of the wordlist
     * @return The wordlist associated with the id
     */
    Wordlist find(int wordlistId);

    /**
     * List all the wordlists
     *
     * @return A list of all the wordlists
     */
    ArrayList<Wordlist> findAll();
}
