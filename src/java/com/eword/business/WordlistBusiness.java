package com.eword.business;

import com.eword.beans.Word;
import com.eword.beans.Wordlist;
import com.eword.dao.DAOFactory;
import com.eword.dao.interfaces.WordDAO;
import com.eword.dao.interfaces.WordlistDAO;
import java.util.ArrayList;
import java.util.HashMap;

public class WordlistBusiness {

    private static final WordlistDAO WORDLIST_DAO = DAOFactory.getInstance().getWordlistDAO();
    private static final WordDAO WORD_DAO = DAOFactory.getInstance().getWordDAO();

    public static HashMap<ArrayList<String>, ArrayList<Wordlist>> mapByLanguages() {
        ArrayList<Wordlist> wordlists = WORDLIST_DAO.findAll();

        HashMap<ArrayList<String>, ArrayList<Wordlist>> mapWordlists = new HashMap<>();

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

    public static Wordlist getPopulatedWordlist(int wordlistId) {

        Wordlist wordlist = WORDLIST_DAO.find(wordlistId);
        if (wordlist != null) {
            ArrayList<Word> words = WORD_DAO.findAll(wordlistId);
            wordlist.setWords(words);
        }

        return wordlist;
    }
}
