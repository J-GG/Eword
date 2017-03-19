package com.eword.lang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class manages the translations of the languages of the application
 */
public class Lang {

    /**
     * Default Language used everywhere in the application
     */
    private static final Language DEFAULT_LANG = Language.ENGLISH;

    /**
     * Unique instance of Lang
     */
    private static Lang INSTANCE;

    /**
     * Key of the translation Map to retrieve the code of the language
     */
    private final static String KEY_LANG_CODE = "LANG_CODE";

    /**
     * Key of the translation Map giving the display name of the language
     */
    private final static String KEY_LANG_NAME = "DISPLAY_NAME";

    /**
     * Map of translations sorted by Language
     */
    private static final Map<Language, Map<String, String>> TRANSLATIONS = new HashMap<>();

    /**
     * Object enabling to communicate with the Translation data layer
     */
    private static final TranslationDAO TRANSLATION_DAO = new TranslationDAO();

    /**
     * Create an instance of Lang
     */
    private Lang() {
        List<Translation> translationsList = TRANSLATION_DAO.findAll();

        //Creation of the Map of Languages
        for (Language lang : Language.values()) {
            Map<String, String> languageInformation = new HashMap<>();
            languageInformation.put(KEY_LANG_CODE, lang.getCode());
            languageInformation.put(KEY_LANG_NAME, lang.getName());
            TRANSLATIONS.put(lang, languageInformation);
        }

        //Each translation is added to the Map of its Language
        for (Translation translation : translationsList) {
            TRANSLATIONS.get(translation.getLanguage()).put(translation.getKey(), translation.getTranslation());
        }

        //For missing translations, the translation of the default language is added
        for (Language keyLang : TRANSLATIONS.keySet()) {
            Map<String, String> tr = TRANSLATIONS.get(DEFAULT_LANG);
            for (String key : tr.keySet()) {
                String defaultValue = TRANSLATIONS.get(DEFAULT_LANG).get(key);
                TRANSLATIONS.get(keyLang).putIfAbsent(key, defaultValue);
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
    public Map<String, String> getDefaultTranslations() {
        return getTranslations(DEFAULT_LANG);
    }

    /**
     * Return a Map of the translations for this Language
     *
     * @param lang Language of the translations
     * @return A Map of the translations for this Language
     */
    public Map<String, String> getTranslations(Language lang) {
        return TRANSLATIONS.get(lang);
    }

    /**
     * Enumeration representing all the languages supported by the application
     */
    public enum Language {
        ENGLISH("us", "English"),
        FRENCH("fr", "Français");

        /**
         * Code of the language
         */
        private final String code;

        /**
         * Display name of the language
         */
        private final String name;

        /**
         * Create a language with its code
         *
         * @param code The code of the language
         * @param name The display name of the language
         */
        Language(String code, String name) {
            this.code = code;
            this.name = name;
        }

        /**
         * Return a Language from its code if it exists
         *
         * @param code The code of the Language
         * @return The Language associated to this code or null if it doesn't
         * exist
         */
        public static Language getLanguageFromCode(String code) {
            Language language = null;

            for (Language lang : Language.values()) {
                if (lang.getCode().equals(code)) {
                    language = lang;
                }
            }

            return language;
        }

        /**
         * Return the code of the language
         *
         * @return the code of the language
         */
        public String getCode() {
            return code;
        }

        /**
         * return the display name of the language
         *
         * @return the display name of the language
         */
        public String getName() {
            return name;
        }
    }
}
