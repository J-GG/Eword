package com.eword.dao.mongo;

import com.eword.beans.User;
import com.eword.dao.interfaces.UserDAO;
import com.eword.lang.Lang.Language;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import org.bson.Document;

public class UserDAOMongo implements UserDAO {

    /**
     * Name of the counter collection
     */
    private static final String COLLECTION_USER = "user";

    /**
     * Name of the field containing the user's id
     */
    private static final String COLLECTION_USER_ID = "user_id";

    /**
     * Name of the field containing the user's language
     */
    private static final String COLLECTION_USER_LANGUAGE = "language";
    /**
     * Name of the field containing the user's password
     */
    private static final String COLLECTION_USER_PASSWORD = "password";

    /**
     * Name of the field containing the user's username
     */
    private static final String COLLECTION_USER_USERNAME = "username";

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
        userDocument.put(COLLECTION_USER_ID, MongoUtilitarian.getNextSequence(MONGO_DATABASE, COLLECTION_USER_ID));
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

        User user = null;
        MongoCollection userCollection = MONGO_DATABASE.getCollection(COLLECTION_USER);

        //We search for a document in the collection from the username and the password
        Document userDocument = (Document) userCollection.find(and(Filters.eq(COLLECTION_USER_USERNAME, username), Filters.eq(COLLECTION_USER_PASSWORD, password))).first();

        //If the document exists, a User object is created and populated with their information
        if (userDocument != null) {
            user = new User();
            user.setId(userDocument.getInteger(COLLECTION_USER_ID));
            user.setUsername(username);
            user.setPassword(password);
            user.setLanguage(Language.getLanguageFromCode(userDocument.getString(COLLECTION_USER_LANGUAGE)));
        }

        return user;
    }

}
