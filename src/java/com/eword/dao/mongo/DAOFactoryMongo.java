package com.eword.dao.mongo;

import com.eword.dao.DAOFactory;
import com.eword.dao.interfaces.QuoteDAO;
import com.eword.dao.interfaces.UserDAO;
import com.eword.dao.interfaces.WordDAO;
import com.eword.dao.interfaces.WordlistDAO;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DAOFactoryMongo extends DAOFactory {

    private static final String PROPERTIES_FILE = "com/eword/dao/mongo/mongo.properties";
    private static final String PROPERTY_HOST = "host";
    private static final String PROPERTY_PORT = "port";
    private static final String PROPERTY_DATABASE = "database";
    private static final String PROPERTY_ADMINISTRATORS_DATABASE = "administrators_database";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";

    private final MongoDatabase database;

    private static DAOFactoryMongo instance;

    private DAOFactoryMongo(String host, int port, String database, String administratorsDatabase, String username, String password) {
        MongoCredential credential = MongoCredential.createCredential(username, administratorsDatabase, password.toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));
        this.database = mongoClient.getDatabase(database);
    }

    public static DAOFactoryMongo getInstance() {

        Properties properties = new Properties();
        String host = null;
        int port = 0;
        String database = null;
        String administratorsDatabase = null;
        String username = null;
        String password = null;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        try {
            properties.load(propertiesFile);
            host = properties.getProperty(PROPERTY_HOST);
            port = Integer.parseInt(properties.getProperty(PROPERTY_PORT));
            database = properties.getProperty(PROPERTY_DATABASE);
            administratorsDatabase = properties.getProperty(PROPERTY_ADMINISTRATORS_DATABASE);
            username = properties.getProperty(PROPERTY_USERNAME);
            password = properties.getProperty(PROPERTY_PASSWORD);
        } catch (IOException ex) {
            Logger.getLogger(DAOFactoryMongo.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (instance == null) {
            instance = new DAOFactoryMongo(host, port, database, administratorsDatabase, username, password);
        }

        return instance;
    }

    MongoDatabase getConnection() {

        return database;
    }

    @Override
    public QuoteDAO getQuoteDAO() {
        return new QuoteDAOMongo(this);
    }

    @Override
    public WordDAO getWordDAO() {
        return new WordDAOMongo(this);
    }

    @Override
    public WordlistDAO getWordlistDAO() {
        return new WordlistDAOMongo(this);
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOMongo(this);
    }

}
