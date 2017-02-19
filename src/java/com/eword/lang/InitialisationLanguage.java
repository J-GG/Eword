package com.eword.lang;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitialisationLanguage implements ServletContextListener {

    /**
     * Name of the application attribute containing the list of
     */
    private final static String ATT_LIST_LANGUAGES = "listLanguages";

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute(ATT_LIST_LANGUAGES, Lang.Language.values());
        System.out.println("test");
    }

}
