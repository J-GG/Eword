package com.eword.lang.listener;

import com.eword.lang.business.Lang;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * This class initializes the display language at the creation of the session
 */
@WebListener
public class InitialisationLang implements HttpSessionListener {

    /**
     * Name of the request attribute containing the translations for the display
     * language chosen by the user
     */
    private final static String ATT_LANG = "lang";

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        //The default translations are set into a session attribute until the user changes
        HttpSession session = se.getSession();
        session.setAttribute(ATT_LANG, Lang.getInstance().getDefaultTranslations());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }

}
