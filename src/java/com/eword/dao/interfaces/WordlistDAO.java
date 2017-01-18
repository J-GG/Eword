package com.eword.dao.interfaces;

import com.eword.beans.Wordlist;
import java.util.ArrayList;

public interface WordlistDAO {

    /**
     * Find the wordlist associated with the id. The Wordlist doesn't contain
     * the list of words
     *
     * @param wordlistId The id of the wordlist
     * @return The wordlist associated with the id or null if the id doesn't
     * correpond to any wordlist
     */
    Wordlist find(int wordlistId);

    /**
     * List all the wordlists The Wordlists don't contain the list of words
     *
     * @return A list of all the wordlists
     */
    ArrayList<Wordlist> findAll();
}
