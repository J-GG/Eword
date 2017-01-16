package com.eword.dao.mongo;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoUtilitarian {

    /**
     * This function generates an auto-incrementing number for the defined
     * counter.
     *
     * @param daoFactory the DAOFactory managing the connection to the MongoDB
     * database
     * @param counterName the counter name
     * @return the value of the counter
     */
    public static Object getNextSequence(DAOFactoryMongo daoFactory, String counterName) {
        MongoCollection collection = daoFactory.getConnection().getCollection("counter");

        Document find = new Document();
        find.put("_id", counterName);
        Document update = new Document();
        update.put("$inc", new Document("seq", 1));
        Document obj = (Document) collection.findOneAndUpdate(find, update);

        return obj.get("seq");
    }
}
