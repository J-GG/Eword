package com.eword.lang;

import com.eword.lang.Lang.Language;

/**
 * Represents a translation which is displayed to users. It is defined by a
 * language and a key to access the translation.
 */
public class Translation {

    /**
     * The key used to access the translation
     */
    private String key;

    /**
     * The language of the translation
     */
    private Language language;

    /**
     * The translation displayed
     */
    private String translation;

    /**
     * Return the key to access the translation
     *
     * @return the key to access the translation
     */
    public String getKey() {
        return key;
    }

    /**
     * Set the key to access the translation
     *
     * @param key the key to access the translation
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * return the language of the translation
     *
     * @return the language of the translation
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Set the language of the translation
     *
     * @param language the language of the translation
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Return the translation displayed
     *
     * @return the translation displayed
     */
    public String getTranslation() {
        return translation;
    }

    /**
     * Set the translation displayed
     *
     * @param translation the translation displayed
     */
    public void setTranslation(String translation) {
        this.translation = translation;
    }

}
