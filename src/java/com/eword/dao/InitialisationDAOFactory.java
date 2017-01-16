package com.eword.dao;

import com.eword.dao.mongo.DAOFactoryMongo;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitialisationDAOFactory implements ServletContextListener {

    private static final String ATT_DAO_FACTORY = "daofactory";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        DAOFactoryMongo daofactory = DAOFactoryMongo.getInstance();
        servletContext.setAttribute(ATT_DAO_FACTORY, daofactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
