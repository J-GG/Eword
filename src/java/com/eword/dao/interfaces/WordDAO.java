package com.eword.dao.interfaces;

import com.eword.beans.Word;
import java.util.ArrayList;

public interface WordDAO {

    /**
     * Find all the Words of the wordlist from its id
     *
     * @param wordlistId The id of the wordlist
     * @return a list of words or null if the id doesn't correpond to any
     * wordlist
     */
    ArrayList<Word> findAll(int wordlistId);
}
