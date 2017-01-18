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
        MongoCollection wordlistCollection = MONGO_DATABASE.getCollection("wordlist");

        //We search for the wordlist with the given id
        Document wordlistDocument = (Document) wordlistCollection.find(Filters.eq("wordlist_id", wordlistId)).first();

        //If the document exists, a Wordlist object is created and populated with their information
        if (wordlistDocument != null) {
            wordlist = new Wordlist();
            wordlist.setId(wordlistDocument.getInteger("wordlist_id"));
            wordlist.setUserId(wordlistDocument.getInteger("user"));
            wordlist.setTitle(wordlistDocument.getString("title"));
            wordlist.setSourceLanguage(wordlistDocument.getString("source"));
            wordlist.setDestinationLanguage(wordlistDocument.getString("destination"));
        }

        return wordlist;
    }

    @Override
    public ArrayList<Wordlist> findAll(int userId) {

        ArrayList<Wordlist> wordlists = new ArrayList<>();
        MongoCollection wordlistCollection = MONGO_DATABASE.getCollection("wordlist");
        System.out.println(userId);
        //We search for all the wordlists
        MongoCursor cursor = wordlistCollection.find(Filters.eq("user", userId)).iterator();

        //For each document, a Wordlist object is created with its information and added to the list
        while (cursor.hasNext()) {
            Document wordlistDocument = (Document) cursor.next();
            Wordlist wordlist = new Wordlist();
            wordlist.setId(wordlistDocument.getInteger("wordlist_id"));
            wordlist.setUserId(wordlistDocument.getInteger("user"));
            wordlist.setTitle(wordlistDocument.getString("title"));
            wordlist.setSourceLanguage(wordlistDocument.getString("source"));
            wordlist.setDestinationLanguage(wordlistDocument.getString("destination"));
            wordlists.add(wordlist);
        }

        cursor.close();

        return wordlists;
    }

}
