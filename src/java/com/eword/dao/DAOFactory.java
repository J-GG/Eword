package com.eword.dao;

import com.eword.dao.interfaces.QuoteDAO;
import com.eword.dao.interfaces.UserDAO;
import com.eword.dao.interfaces.WordDAO;
import com.eword.dao.interfaces.WordlistDAO;
import com.eword.dao.mongo.DAOFactoryMongo;

public abstract class DAOFactory {

    /**
     * The name of the DAOFactory implementation
     */
    private static final String DAO_FACTORY = "mongo";

    /**
     * The only instance of the DAOFactory
     */
    private static DAOFactory instance;

    /**
     * Return the instance of the DAOFactory
     *
     * @return The instance of the DAOFactory
     */
    public static DAOFactory getInstance() {

        //If no instance exists, it is created
        if (instance == null) {
            switch (DAO_FACTORY) {
                case "mongo":
                    instance = DAOFactoryMongo.getInstance();
                    break;
            }
        }

        return instance;
    }

    /**
     * Return a QuoteDAO object enabling to communicate with the Quote data
     * layer
     *
     * @return A QuoteDAO object
     */
    public abstract QuoteDAO getQuoteDAO();

    /**
     * Return a WordlistDAO object enabling to communicate with the Wordlist
     * data layer
     *
     * @return A WordlistDAO object
     */
    public abstract WordlistDAO getWordlistDAO();

    /**
     * Return a WordDAO object enabling to communicate with the Word data layer
     *
     * @return A WordDAO object
     */
    public abstract WordDAO getWordDAO();

    /**
     * Return a UserDAO object enabling to communicate with the User data layer
     *
     * @return A UserDAO object
     */
    public abstract UserDAO getUserDAO();

}
