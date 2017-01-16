package com.eword.business;

import com.eword.beans.Word;
import com.eword.beans.Wordlist;
import com.eword.dao.interfaces.WordDAO;
import com.eword.dao.interfaces.WordlistDAO;
import java.util.ArrayList;
import java.util.HashMap;

public class WordlistBusiness {

    public static HashMap<ArrayList<String>, ArrayList<Wordlist>> mapByLanguages(WordlistDAO wordlistDAO) {
        ArrayList<Wordlist> wordlists = wordlistDAO.findAll();

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

    public static Wordlist getPopulatedWordlist(WordlistDAO wordlistDAO, WordDAO wordDAO, int wordlistId) {
        Wordlist wordlist = wordlistDAO.find(wordlistId);
        if (wordlist != null) {
            ArrayList<Word> words = wordDAO.findAll(wordlistId);
            wordlist.setWords(words);
        }

        return wordlist;
    }
}
