package com.eword.dao.interfaces;

import com.eword.beans.Word;
import java.util.ArrayList;

public interface WordDAO {

    /**
     * Finds all the words of a wordlist.
     *
     * @param wordlistId ID of the wordlist
     * @return a list of words
     */
    ArrayList<Word> findAll(int wordlistId);
}
