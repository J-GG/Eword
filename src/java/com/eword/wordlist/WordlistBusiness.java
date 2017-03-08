package com.eword.wordlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WordlistBusiness {

    /**
     * Name of the session attribute containing the id of the user
     */
    private static final String ATT_USER_ID = "user_id";

    /**
     * Return a Wordlist if there is one matching the id
     *
     * @param req The request
     * @param wordlistDAO Object enabling to communicate with the Wordlist data
     * layer
     * @param wordDAO Object enabling to communicate with the Word data layer
     * @param wordlistId The id of the wordlist
     * @return A full Wordlist
     */
    public static Wordlist getWordlist(int wordlistId, WordlistDAO wordlistDAO, WordDAO wordDAO, HttpServletRequest req) {

        //We first retrieve the information of the wordlist without the list of words
        Wordlist wordlist = wordlistDAO.find(wordlistId);

        if (wordlist != null) {
            //We check the wordlist belongs to the user
            HttpSession session = req.getSession();
            Integer userId = (Integer) session.getAttribute(ATT_USER_ID);
            if (!userId.equals(wordlist.getUser().getId())) {
                wordlist = null;
            }
        }

        return wordlist;
    }

    /**
     * Return a Map of the user's Wordlists sorted by language. The map contains
     * lists of languages (the first is the source and the second is the
     * destination language) as keys and Wordlists as values.
     *
     * @param wordlistDAO Object enabling to communicate with the Wordlist data
     * layer
     * @param req The request
     * @return A Map of Wordlists sorted by language
     */
    public static HashMap<ArrayList<String>, ArrayList<Wordlist>> mapByLanguages(WordlistDAO wordlistDAO, HttpServletRequest req) {

        HashMap<ArrayList<String>, ArrayList<Wordlist>> mapWordlists = new HashMap<>();

        //We get the user id from the session
        HttpSession session = req.getSession();
        Integer userId = null;
        userId = (Integer) session.getAttribute(ATT_USER_ID);

        //If there is a user id
        if (userId != null) {
            //We get all the Wordlists
            List<Wordlist> wordlists = wordlistDAO.findAll(userId);

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
        } else {
            throw new RuntimeException("There is no user id");
        }

        return mapWordlists;
    }
}
