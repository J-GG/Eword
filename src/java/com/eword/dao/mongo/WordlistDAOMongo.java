package com.eword.dao.mongo;

import com.eword.beans.Wordlist;
import com.eword.dao.interfaces.WordlistDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import org.bson.Document;

public class WordlistDAOMongo implements WordlistDAO {

    private final DAOFactoryMongo daoFactory;

    WordlistDAOMongo(DAOFactoryMongo daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Wordlist find(int wordlistId) {
        MongoCollection wordlistCollection = daoFactory.getConnection().getCollection("wordlist");
        Document wordlistDocument = (Document) wordlistCollection.find(Filters.eq("wordlist_id", wordlistId)).first();

        Wordlist wordlist = null;
        if (wordlistDocument != null) {
            wordlist = new Wordlist();
            wordlist.setId(wordlistDocument.getInteger("wordlist_id"));
            wordlist.setTitle(wordlistDocument.getString("title"));
            wordlist.setSourceLanguage(wordlistDocument.getString("source"));
            wordlist.setDestinationLanguage(wordlistDocument.getString("destination"));
        }

        return wordlist;
    }

    @Override
    public ArrayList<Wordlist> findAll() {
        MongoCollection wordlistCollection = daoFactory.getConnection().getCollection("wordlist");
        MongoCursor cursor = wordlistCollection.find().iterator();

        ArrayList<Wordlist> wordlists = new ArrayList<>();

        while (cursor.hasNext()) {
            Document wordlistDocument = (Document) cursor.next();
            Wordlist wordlist = new Wordlist();
            wordlist.setId(wordlistDocument.getInteger("wordlist_id"));
            wordlist.setTitle(wordlistDocument.getString("title"));
            wordlist.setSourceLanguage(wordlistDocument.getString("source"));
            wordlist.setDestinationLanguage(wordlistDocument.getString("destination"));
            wordlists.add(wordlist);
        }

        cursor.close();

        return wordlists;
    }

}
