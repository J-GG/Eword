package com.eword.dao.mongo;

import com.eword.beans.Quote;
import com.eword.dao.interfaces.QuoteDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.concurrent.ThreadLocalRandom;
import org.bson.Document;

public class QuoteDAOMongo implements QuoteDAO {

    /**
     * Interface to access the MongoDB database
     */
    private final MongoDatabase MONGO_DATABASE;

    /**
     * Create an interface to access data regarding the quotes
     *
     * @param database The database where data is stored
     */
    QuoteDAOMongo(MongoDatabase database) {
        this.MONGO_DATABASE = database;
    }

    @Override
    public Quote findRandom() {

        Quote quote = null;
        MongoCollection quoteCollection = MONGO_DATABASE.getCollection("quote");

        //A random number is generated from the number of quotes
        long nbQuotes = quoteCollection.count();
        int quoteRandom = ThreadLocalRandom.current().nextInt(0, (int) (nbQuotes));

        //We search for the random-number-th quote
        Document quoteDocument = (Document) quoteCollection.find().limit(-1).skip(quoteRandom).first();

        //If the document exists a Quote object is created and populated with its information
        if (quoteDocument != null) {
            quote = new Quote();
            quote.setId(quoteDocument.getInteger("quote_id"));
            quote.setContent(quoteDocument.getString("content"));
            quote.setAuthor(quoteDocument.getString("author"));
        }

        return quote;
    }

}
