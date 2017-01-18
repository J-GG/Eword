package com.eword.business;

import com.eword.beans.Word;
import com.eword.beans.Wordlist;
import com.eword.dao.DAOFactory;
import com.eword.dao.interfaces.WordDAO;
import com.eword.dao.interfaces.WordlistDAO;
import java.util.ArrayList;
import java.util.HashMap;

public class WordlistBusiness {

    /**
     * Object enabling to communicate with the Wordlist data layer
     */
    private static final WordlistDAO WORDLIST_DAO = DAOFactory.getInstance().getWordlistDAO();

    /**
     * Oobject enabling to communicate with the Word data layer
     */
    private static final WordDAO WORD_DAO = DAOFactory.getInstance().getWordDAO();

    /**
     * Return a Wordlist with all its information, including the list of words
     *
     * @param wordlistId The id of the wordlist
     * @return A full Wordlist
     */
    public static Wordlist getPopulatedWordlist(int wordlistId) {

        //We first retrieve the information of the wordlist without the list of words
        Wordlist wordlist = WORDLIST_DAO.find(wordlistId);

        //If the wordlist exists, we add the list of words
        if (wordlist != null) {
            ArrayList<Word> words = WORD_DAO.findAll(wordlistId);
            wordlist.setWords(words);
        }

        return wordlist;
    }

    /**
     * Return a Map of Wordlists sorted by language. The map contains lists of
     * languages (the first is the source and the second is the destination
     * language) as keys and Wordlists as values.
     *
     * @return A Map of Wordlists sorted by language
     */
    public static HashMap<ArrayList<String>, ArrayList<Wordlist>> mapByLanguages() {

        HashMap<ArrayList<String>, ArrayList<Wordlist>> mapWordlists = new HashMap<>();

        //We get all the Wordlists
        ArrayList<Wordlist> wordlists = WORDLIST_DAO.findAll();

        //Wordlists are sorted by language
        for (Wordlist wordlist : wordlists) {
            ArrayList<String> languages = new ArrayList<>();
            languages.add(wordlist.getSourceLanguage());
            languages.add(wordlist.getDestinationLanguage());

            if (!mapWordlists.containsKey(languages)) {
                mapWordlists.put(languages, new ArrayList<>());
            }
            mapWordlists.get(languages).add(wordlist);
        }

        return mapWordlists;
    }
}
