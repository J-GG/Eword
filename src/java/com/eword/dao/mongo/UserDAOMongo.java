package com.eword.dao.mongo;

import com.eword.beans.User;
import com.eword.dao.interfaces.UserDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import org.bson.Document;

public class UserDAOMongo implements UserDAO {

    private final DAOFactoryMongo daoFactory;

    UserDAOMongo(DAOFactoryMongo daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(User user) {
        MongoCollection userCollection = daoFactory.getConnection().getCollection("user");
        Document userDocument = new Document();
        userDocument.put("user_id", MongoUtilitarian.getNextSequence(daoFactory, "user_id"));
        userDocument.put("username", user.getUsername());
        userDocument.put("password", user.getPassword());

        userCollection.insertOne(userDocument);
    }

    @Override
    public boolean exist(String username) {
        MongoCollection userCollection = daoFactory.getConnection().getCollection("user");
        Document userDocument = (Document) userCollection.find(Filters.eq("username", username)).first();

        boolean exists = true;

        if (userDocument == null) {
            exists = false;
        }

        return exists;
    }

    @Override
    public User find(String username, String password) {
        MongoCollection userCollection = daoFactory.getConnection().getCollection("user");
        Document userDocument = (Document) userCollection.find(and(Filters.eq("username", username), Filters.eq("password", password))).first();

        User user = null;
        if (userDocument != null) {
            user = new User();
            user.setId(userDocument.getInteger("user_id"));
            user.setUsername(username);
            user.setPassword(password);
        }
        return user;
    }

}
