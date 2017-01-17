package com.eword.dao.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoUtilitarian {

    /**
     * Generate an auto-incrementing number for the defined counter
     *
     * @param mongoDatabase The database where data is stored
     * @param counterName the counter name
     * @return the value of the counter
     */
    public static Integer getNextSequence(final MongoDatabase MONGO_DATABASE, String counterName) {

        MongoCollection collection = MONGO_DATABASE.getCollection("counter");

        //We search for the counterName
        Document find = new Document();
        find.put("_id", counterName);
        //We increment the counter by 1
        Document update = new Document();
        update.put("$inc", new Document("seq", 1));
        //We get the previous value of the counter which is then updated
        Document obj = (Document) collection.findOneAndUpdate(find, update);

        return obj.getInteger("seq");
    }
}
