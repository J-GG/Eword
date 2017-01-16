package com.eword.dao.interfaces;

import com.eword.beans.Word;
import java.util.ArrayList;

public interface WordDAO {

    /**
     * Find all the words associated with the id of the wordlist
     *
     * @param wordlistId The id of the wordlist
     * @return a list of words
     */
    ArrayList<Word> findAll(int wordlistId);
}
