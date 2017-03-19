package com.eword.lang;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class which enables to interact with the data layer regarding the Lang
 * entity.
 */
public class TranslationDAO {

    /**
     * Extension of the properties files
     */
    private final static String EXTENSION = ".properties";

    /**
     * PATH to the properties files containing the translations
     */
    private final static String TRANSLATIONS_PATH = "";

    /**
     * Find all the translations
     *
     * @return a List of all the translation
     */
    public List<Translation> findAll() {
        List<Translation> translations = new ArrayList<>();

        //We load all the properties files
        for (Lang.Language lang : Lang.Language.values()) {
            Properties properties = new Properties();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream propertiesFile = classLoader.getResourceAsStream(TRANSLATIONS_PATH + lang.getCode() + EXTENSION);

            try {
                properties.load(propertiesFile);

                Set<Object> keys = properties.keySet();

                //For each key, we populate a Translation object
                for (Object k : keys) {
                    String key = (String) k;

                    Translation translation = new Translation();
                    translation.setKey(key);
                    translation.setTranslation(properties.getProperty(key));
                    translation.setLanguage(lang);

                    translations.add(translation);
                }

            } catch (IOException ex) {
                Logger.getLogger(Lang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return translations;
    }
}
