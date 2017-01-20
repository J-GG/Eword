package com.eword.dao.mongo;

import com.eword.beans.Wordlist;
import com.eword.dao.interfaces.WordlistDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import org.bson.Document;

public class WordlistDAOMongo implements WordlistDAO {

    /**
     * Name of the wordlist collection
     */
    private static final String COLLECTION_WORDLIST = "wordlist";

    /**
     * Name of the field containing the destination language of the wordlist
     */
    private static final String COLLECTION_WORDLIST_DESTINATION = "destination";

    /**
     * Name of the field containing the id of the wordlist
     */
    private static final String COLLECTION_WORDLIST_ID = "wordlist_id";

    /**
     * Name of the field containing the source language of the wordlist
     */
    private static final String COLLECTION_WORDLIST_SOURCE = "source";

    /**
     * Name of the field containing the title of the wordlist
     */
    private static final String COLLECTION_WORDLIST_TITLE = "title";

    /**
     * Name of the field containing the owner of the wordlist
     */
    private static final String COLLECTION_WORDLIST_USER = "user";

    /**
     * Interface to access the MongoDB database
     */
    private final MongoDatabase MONGO_DATABASE;

    /**
     * Create an interface to access data regarding the wordlists
     *
     * @param database The database where data is stored
     */
    WordlistDAOMongo(MongoDatabase database) {
        this.MONGO_DATABASE = database;
    }

    @Override
    public Wordlist find(int wordlistId) {

        Wordlist wordlist = null;
        MongoCollection wordlistCollection = MONGO_DATABASE.getCollection(COLLECTION_WORDLIST);

        //We search for the wordlist with the given id
        Document wordlistDocument = (Document) wordlistCollection.find(Filters.eq(COLLECTION_WORDLIST_ID, wordlistId)).first();

        //If the document exists, a Wordlist object is created and populated with their information
        if (wordlistDocument != null) {
            wordlist = new Wordlist();
            wordlist.setId(wordlistDocument.getInteger(COLLECTION_WORDLIST_ID));
            wordlist.setUserId(wordlistDocument.getInteger(COLLECTION_WORDLIST_USER));
            wordlist.setTitle(wordlistDocument.getString(COLLECTION_WORDLIST_TITLE));
            wordlist.setSourceLanguage(wordlistDocument.getString(COLLECTION_WORDLIST_SOURCE));
            wordlist.setDestinationLanguage(wordlistDocument.getString(COLLECTION_WORDLIST_DESTINATION));
        }

        return wordlist;
    }

    @Override
    public ArrayList<Wordlist> findAll(int userId) {

        ArrayList<Wordlist> wordlists = new ArrayList<>();
        MongoCollection wordlistCollection = MONGO_DATABASE.getCollection(COLLECTION_WORDLIST);

        //We search for all the wordlists
        MongoCursor cursor = wordlistCollection.find(Filters.eq(COLLECTION_WORDLIST_USER, userId)).iterator();

        //For each document, a Wordlist object is created with its information and added to the list
        while (cursor.hasNext()) {
            Document wordlistDocument = (Document) cursor.next();
            Wordlist wordlist = new Wordlist();
            wordlist.setId(wordlistDocument.getInteger(COLLECTION_WORDLIST_ID));
            wordlist.setUserId(wordlistDocument.getInteger(COLLECTION_WORDLIST_USER));
            wordlist.setTitle(wordlistDocument.getString(COLLECTION_WORDLIST_TITLE));
            wordlist.setSourceLanguage(wordlistDocument.getString(COLLECTION_WORDLIST_SOURCE));
            wordlist.setDestinationLanguage(wordlistDocument.getString(COLLECTION_WORDLIST_DESTINATION));
            wordlists.add(wordlist);
        }

        cursor.close();

        return wordlists;
    }

}
