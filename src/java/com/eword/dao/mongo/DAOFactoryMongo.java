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

    /**
     * Address of the property file containing the information to connect to the
     * MongoDB database
     */
    private static final String PROPERTIES_FILE = "com/eword/dao/mongo/mongo.properties";

    /**
     * Key to retrieve the name of the database containing the administrators of
     * the MongoDB database
     */
    private static final String PROPERTY_ADMINISTRATORS_DATABASE = "administrators_database";

    /**
     * Key to retrieve the name of the database
     */
    private static final String PROPERTY_DATABASE = "database";

    /**
     * Key to retrieve the host
     */
    private static final String PROPERTY_HOST = "host";

    /**
     * Key to retrieve the password to be granted the access to the database
     */
    private static final String PROPERTY_PASSWORD = "password";

    /**
     * Key to retrieve the port
     */
    private static final String PROPERTY_PORT = "port";

    /**
     * Key to retrieve the username to be granted the access to the database
     */
    private static final String PROPERTY_USERNAME = "username";

    /**
     * Unique instance of the class
     */
    private static DAOFactoryMongo INSTANCE;

    /**
     * Instance of the database
     */
    private static MongoDatabase DATABASE;

    /**
     * Creates a connection to the MongoDB database
     *
     * @param host The host of the database
     * @param port The port of the database
     * @param database The name of the database
     * @param administratorsDatabase The name of the database containing the
     * administrators of the databases
     * @param username The username to be granted the access to the database
     * @param password The password to be granted the access to the database
     */
    private DAOFactoryMongo(String host, int port, String database, String administratorsDatabase, String username, String password) {
        MongoCredential credential = MongoCredential.createCredential(username, administratorsDatabase, password.toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));
        this.DATABASE = mongoClient.getDatabase(database);
    }

    /**
     * Return a unique instance of the class
     *
     * @return The unique instance of the class
     */
    public static DAOFactoryMongo getInstance() {

        //Information to connect to the database is extracted from a property file
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

        //If the instance doesn't already exist, it is created
        if (INSTANCE == null) {
            INSTANCE = new DAOFactoryMongo(host, port, database, administratorsDatabase, username, password);
        }

        return INSTANCE;
    }

    @Override
    public QuoteDAO getQuoteDAO() {
        return new QuoteDAOMongo(DATABASE);
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOMongo(DATABASE);
    }

    @Override
    public WordDAO getWordDAO() {
        return new WordDAOMongo(DATABASE);
    }

    @Override
    public WordlistDAO getWordlistDAO() {
        return new WordlistDAOMongo(DATABASE);
    }

}
