package com.eword.dao.mongo;

import com.eword.beans.User;
import com.eword.dao.interfaces.UserDAO;
import com.eword.lang.Lang.Language;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import org.bson.Document;

public class UserDAOMongo implements UserDAO, RequestAttribute {

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

        MongoCollection userCollection = MONGO_DATABASE.getCollection(COLLECTION_USER);

        //Creation of a document with the user's values
        Document userDocument = new Document();
        userDocument.put(COLLECTION_USER_ID, MongoUtils.getNextSequence(MONGO_DATABASE, COLLECTION_USER_ID));
        userDocument.put(COLLECTION_USER_USERNAME, user.getUsername());
        userDocument.put(COLLECTION_USER_PASSWORD, user.getPassword());
        userDocument.put(COLLECTION_USER_LANGUAGE, user.getLanguage().toString());

        //The document is inserted in the collection
        userCollection.insertOne(userDocument);
    }

    @Override
    public boolean exist(String username) {

        boolean exists = true;
        MongoCollection userCollection = MONGO_DATABASE.getCollection(COLLECTION_USER);

        //We search for a document in the collection from the username
        Document userDocument = (Document) userCollection.find(Filters.eq(COLLECTION_USER_USERNAME, username)).first();

        if (userDocument == null) {
            exists = false;
        }

        return exists;
    }

    @Override
    public User find(String username, String password) {

        MongoCollection userCollection = MONGO_DATABASE.getCollection(COLLECTION_USER);

        //We search for a document in the collection from the username and the password
        Document userDocument = (Document) userCollection.find(and(Filters.eq(COLLECTION_USER_USERNAME, username), Filters.eq(COLLECTION_USER_PASSWORD, password))).first();

        //If the document exists, a User object is created and populated with their information
        User user = populateUser(userDocument);

        return user;
    }

    @Override
    public User find(Integer userId) {

        MongoCollection userCollection = MONGO_DATABASE.getCollection(COLLECTION_USER);

        //We search for a document in the collection from the user's id
        Document userDocument = (Document) userCollection.find(Filters.eq(COLLECTION_USER_ID, userId)).first();

        //If the document exists, a User object is created and populated with their information
        User user = populateUser(userDocument);

        return user;
    }

    @Override
    public User findIdFromToken(String token) {

        MongoCollection userCollection = MONGO_DATABASE.getCollection(COLLECTION_USER);

        //We search for a document in the collection from the token
        Document userDocument = (Document) userCollection.find(Filters.eq(COLLECTION_USER_TOKEN, token)).first();

        //If the document exists, We retrieve the user
        User user = populateUser(userDocument);

        return user;
    }

    /**
     * Populate a User from a Document
     *
     * @param userDocument The document containing the information about the
     * user
     * @return A User or null if the userDocument is empty
     */
    private User populateUser(Document userDocument) {

        User user = null;
        if (userDocument != null) {
            user = new User();
            user.setId(userDocument.getInteger(COLLECTION_USER_ID));
            user.setUsername(userDocument.getString(COLLECTION_USER_PASSWORD));
            user.setPassword(userDocument.getString(COLLECTION_USER_PASSWORD));
            user.setLanguage(Language.getLanguageFromCode(userDocument.getString(COLLECTION_USER_LANGUAGE)));
            user.setToken(userDocument.getString(COLLECTION_USER_TOKEN));
        }

        return user;
    }

    @Override
    public void updateToken(User user) {

        MongoCollection userCollection = MONGO_DATABASE.getCollection(COLLECTION_USER);

        //Document we are looking for
        Document search = new Document();
        search.put(COLLECTION_USER_ID, user.getId());

        //Update of the document
        Document update = new Document();
        update.put("$set", new Document(COLLECTION_USER_TOKEN, user.getToken()));

        userCollection.updateOne(search, update);
    }

}
