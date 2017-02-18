package com.eword.business;

import com.eword.beans.Word;
import com.eword.beans.Wordlist;
import com.eword.dao.WordDAO;
import com.eword.dao.WordlistDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WordlistBusiness {

    /**
     * Name of the session attribute containing the id of the user
     */
    private static final String ATT_USER_ID = "user_id";

    /**
     * Object enabling to communicate with the Wordlist data layer
     */
    @EJB
    private WordlistDAO WordlistDAO;

    /**
     * Object enabling to communicate with the Word data layer
     */
    @EJB
    private WordDAO WordDAO;

    /**
     * Return a Wordlist with all its information, including the list of words
     *
     * @param req The request
     * @param wordlistId The id of the wordlist
     * @return A full Wordlist
     */
    public Wordlist getPopulatedWordlist(HttpServletRequest req, int wordlistId) {

        //We first retrieve the information of the wordlist without the list of words
        Wordlist wordlist = WordlistDAO.find(wordlistId);

        if (wordlist != null) {
            //We check the wordlist belongs to the user
            HttpSession session = req.getSession();
            Integer userId = (Integer) session.getAttribute(ATT_USER_ID);
            if (!userId.equals(wordlist.getUser().getId())) {
                throw new RuntimeException("The wordlist doesn't belong to the user");
            }

            //We add the list of words
            List<Word> words = WordDAO.findAll(wordlistId);
            wordlist.setWords(words);
        }

        return wordlist;
    }

    /**
     * Return a Map of the user's Wordlists sorted by language. The map contains
     * lists of languages (the first is the source and the second is the
     * destination language) as keys and Wordlists as values.
     *
     * @param req The request
     * @return A Map of Wordlists sorted by language
     */
    public HashMap<ArrayList<String>, ArrayList<Wordlist>> mapByLanguages(HttpServletRequest req) {

        HashMap<ArrayList<String>, ArrayList<Wordlist>> mapWordlists = new HashMap<>();

        //We get the user id from the session
        HttpSession session = req.getSession();
        Integer userId = null;
        userId = (Integer) session.getAttribute(ATT_USER_ID);

        //If there is a user id
        if (userId != null) {
            //We get all the Wordlists
            List<Wordlist> wordlists = WordlistDAO.findAll(userId);

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
