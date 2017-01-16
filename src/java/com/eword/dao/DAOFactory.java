package com.eword.dao;

import com.eword.dao.interfaces.QuoteDAO;
import com.eword.dao.interfaces.UserDAO;
import com.eword.dao.interfaces.WordDAO;
import com.eword.dao.interfaces.WordlistDAO;
import com.eword.dao.mongo.DAOFactoryMongo;

public abstract class DAOFactory {

    private static final String DAO_FACTORY = "mongo";

    private static DAOFactory instance;

    public static DAOFactory getInstance() {
        if (instance == null) {
            switch (DAO_FACTORY) {
                case "mongo":
                    instance = DAOFactoryMongo.getInstance();
                    break;
            }
        }

        return instance;
    }

    public abstract QuoteDAO getQuoteDAO();

    public abstract WordlistDAO getWordlistDAO();

    public abstract WordDAO getWordDAO();

    public abstract UserDAO getUserDAO();

}
