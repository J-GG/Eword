package com.eword.lang;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class InitialisationLanguage implements HttpSessionListener {

    /**
     * Name of the request attribute containing the translations
     */
    private final static String ATT_LANG = "lang";

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        //The default translations are set into a session attribute until the user changes
        HttpSession session = se.getSession();
        session.setAttribute(ATT_LANG, Lang.getInstance().getTranslations());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }

}
