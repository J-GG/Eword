package com.eword.dao.mongo;

import com.eword.beans.User;
import com.eword.dao.interfaces.UserDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import org.bson.Document;

public class UserDAOMongo implements UserDAO {

    /**
     * Interface to access the MongoDB database
     */
    private final MongoDatabase MONGO_DATABASE;

    /**
     * Create an interface to access data regarding the users
     *
     * @param database The database where data is stored
     */
    UserDAOMongo(MongoDatabase database) {
        this.MONGO_DATABASE = database;
    }

    @Override
    public void create(User user) {

        MongoCollection userCollection = MONGO_DATABASE.getCollection("user");

        //Creation of a document with the user's values
        Document userDocument = new Document();
        userDocument.put("user_id", MongoUtilitarian.getNextSequence(MONGO_DATABASE, "user_id"));
        userDocument.put("username", user.getUsername());
        userDocument.put("password", user.getPassword());

        //The document is inserted in the collection
        userCollection.insertOne(userDocument);
    }

    @Override
    public boolean exist(String username) {

        boolean exists = true;
        MongoCollection userCollection = MONGO_DATABASE.getCollection("user");

        //We search for a document in the collection from the username
        Document userDocument = (Document) userCollection.find(Filters.eq("username", username)).first();

        if (userDocument == null) {
            exists = false;
        }

        return exists;
    }

    @Override
    public User find(String username, String password) {

        User user = null;
        MongoCollection userCollection = MONGO_DATABASE.getCollection("user");

        //We search for a document in the collection from the username and the password
        Document userDocument = (Document) userCollection.find(and(Filters.eq("username", username), Filters.eq("password", password))).first();

        //If the document exists, a User object is created and populated with their information
        if (userDocument != null) {
            user = new User();
            user.setId(userDocument.getInteger("user_id"));
            user.setUsername(username);
            user.setPassword(password);
        }

        return user;
    }

}
