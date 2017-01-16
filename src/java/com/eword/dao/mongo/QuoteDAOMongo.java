package com.eword.dao.mongo;

import com.eword.beans.Quote;
import com.eword.dao.interfaces.QuoteDAO;
import com.mongodb.client.MongoCollection;
import java.util.concurrent.ThreadLocalRandom;
import org.bson.Document;

public class QuoteDAOMongo implements QuoteDAO {

    private final DAOFactoryMongo daoFactory;

    QuoteDAOMongo(DAOFactoryMongo daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Quote findRandom() {
        MongoCollection quoteCollection = daoFactory.getConnection().getCollection("quote");
        long nbQuotes = quoteCollection.count();
        int quoteRandom = ThreadLocalRandom.current().nextInt(0, (int) (nbQuotes));
        Document quoteDocument = (Document) quoteCollection.find().limit(-1).skip(quoteRandom).first();

        Quote quote = null;
        if (quoteDocument != null) {
            quote = new Quote();
            quote.setId(quoteDocument.getInteger("quote_id"));
            quote.setContent(quoteDocument.getString("content"));
            quote.setAuthor(quoteDocument.getString("author"));
        }

        return quote;
    }

}
