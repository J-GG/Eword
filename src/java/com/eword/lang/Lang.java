package com.eword.lang;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lang {

    /**
     * Default Language used everywhere in the application
     */
    private final static Language DEFAULT_LANG = Language.ENGLISH;

    /**
     * Extension of all the properties files
     */
    private final static String EXTENSION = ".properties";

    /**
     * Unique instance of Lang
     */
    private static Lang INSTANCE;

    /**
     * Key of the translation Map to retrieve the code of the language
     */
    private final static String KEY_LANG_CODE = "langCode";

    /**
     * PATH to the the properties files containing the translations
     */
    private final static String TRANSLATIONS_PATH = "com/eword/lang/";

    /**
     * Map of translations sorted by Language
     */
    private static HashMap<Language, HashMap<String, String>> TRANSLATIONS = new HashMap<>();

    /**
     * Creates an instance of a utilitarian class to manage translations
     */
    private Lang() {
        //We load the default properties file
        Properties propertiesDefault = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesDefaultFile = classLoader.getResourceAsStream(TRANSLATIONS_PATH + DEFAULT_LANG + EXTENSION);

        try {
            propertiesDefault.load(propertiesDefaultFile);
        } catch (IOException ex) {
            Logger.getLogger(Lang.class.getName()).log(Level.SEVERE, null, ex);
        }

        //We load all the properties files
        for (Language lang : Language.values()) {
            Properties properties = new Properties();
            InputStream propertiesFile = classLoader.getResourceAsStream(TRANSLATIONS_PATH + lang + EXTENSION);

            try {
                properties.load(propertiesFile);

                Set<Object> keys = propertiesDefault.keySet();
                HashMap<String, String> tr = new HashMap<>();
                tr.put(KEY_LANG_CODE, lang.toString());
                for (Object k : keys) {
                    String key = (String) k;
                    String defaultValue = propertiesDefault.getProperty(key);
                    tr.put(key, properties.getProperty(key, defaultValue));
                }

                TRANSLATIONS.put(lang, tr);
            } catch (IOException ex) {
                Logger.getLogger(Lang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * return the default Language
     *
     * @return The default Language
     */
    public static Language getDefaultLang() {
        return DEFAULT_LANG;
    }

    /**
     * Return the unique instance of Lang
     *
     * @return The unique instance of Lang
     */
    public static Lang getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Lang();
        }

        return INSTANCE;
    }

    /**
     * Return a Map of the translations for the default Language
     *
     * @return A Map of the translations for the default Language
     */
    public HashMap<String, String> getTranslations() {
        return TRANSLATIONS.get(DEFAULT_LANG);
    }

    /**
     * Return a Map of the translations for this Language
     *
     * @param lang Language of the translations
     * @return A Map of the translations for this Language
     */
    public HashMap<String, String> getTranslations(Language lang) {
        return TRANSLATIONS.get(lang);
    }

    /**
     * Enumeration representing all the languages supported by the application
     */
    public enum Language {
        ENGLISH("us"),
        FRANCAIS("fr");

        /**
         * Code of the language
         */
        private final String code;

        /**
         * Create a language with its code
         *
         * @param code The code of the language
         */
        Language(String code) {
            this.code = code;
        }

        /**
         * Return a Language from its code if it exists.
         *
         * @param code The code of the Language
         * @return The Language associated to this code or null if it doesn't
         * exist
         */
        public static Language getLanguageFromCode(String code) {
            Language language = null;

            for (Language lang : Language.values()) {
                if (lang.toString().equals(code)) {
                    language = lang;
                }
            }

            return language;
        }

        @Override
        public String toString() {
            return code;
        }
    }
}