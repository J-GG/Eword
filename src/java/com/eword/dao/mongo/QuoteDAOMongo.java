package com.eword.dao.mongo;

import com.eword.beans.Quote;
import com.eword.dao.interfaces.QuoteDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.concurrent.ThreadLocalRandom;
import org.bson.Document;

public class QuoteDAOMongo implements QuoteDAO {

    /**
     * Name of the quote collection
     */
    private static final String COLLECTION_QUOTE = "quote";
    /**
     * Name of the field containing the author of the quote
     */
    private static final String COLLECTION_QUOTE_AUTHOR = "author";

    /**
     * Name of the field containing the content of the quote
     */
    private static final String COLLECTION_QUOTE_CONTENT = "content";
    /**
     * Name of the field containing the quote id
     */
    private static final String COLLECTION_QUOTE_ID = "quote_id";

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
        MongoCollection quoteCollection = MONGO_DATABASE.getCollection(COLLECTION_QUOTE);

        //A random number is generated from the number of quotes
        long nbQuotes = quoteCollection.count();
        int quoteRandom = ThreadLocalRandom.current().nextInt(0, (int) (nbQuotes));

        //We search for the random-number-th quote
        Document quoteDocument = (Document) quoteCollection.find().limit(-1).skip(quoteRandom).first();

        //If the document exists a Quote object is created and populated with its information
        if (quoteDocument != null) {
            quote = new Quote();
            quote.setId(quoteDocument.getInteger(COLLECTION_QUOTE_ID));
            quote.setContent(quoteDocument.getString(COLLECTION_QUOTE_CONTENT));
            quote.setAuthor(quoteDocument.getString(COLLECTION_QUOTE_AUTHOR));
        }

        return quote;
    }

}
