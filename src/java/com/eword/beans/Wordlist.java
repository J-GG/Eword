package com.eword.beans;

import java.util.ArrayList;

public class Wordlist {

    /**
     * Destination language of the wordlist
     */
    private String destinationLanguage;

    /**
     * If of the wordlist
     */
    private int id;

    /**
     * Source language of the wordlist
     */
    private String sourceLanguage;

    /**
     * Title of the wordlist
     */
    private String title;

    /**
     * List of words
     */
    private ArrayList<Word> words;

    /**
     * Appends the Word to the end of the list
     *
     * @param word The Word to be appended
     */
    public void addWord(Word word) {
        words.add(word);
    }

    /**
     * Return the destination language of the wordlist
     *
     * @return The desitnation language of the wordlist
     */
    public String getDestinationLanguage() {
        return destinationLanguage;
    }

    /**
     * Set the destination language of the wordlist
     *
     * @param languageDestination The destination language of the wordlist
     */
    public void setDestinationLanguage(String languageDestination) {
        this.destinationLanguage = languageDestination;
    }

    /**
     * Return the id of the wordlist
     *
     * @return The id of the wordlist
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the wordlist
     *
     * @param id The id of the wordlist
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return the source language of the wordlist
     *
     * @return The source language of the wordlist
     */
    public String getSourceLanguage() {
        return sourceLanguage;
    }

    /**
     * Set the source language of the wordlist
     *
     * @param languageSource The source language of the wordlist
     */
    public void setSourceLanguage(String languageSource) {
        this.sourceLanguage = languageSource;
    }

    /**
     * Return the title of the wordlist
     *
     * @return The title of the wordlist
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the wordlist
     *
     * @param title The title of the wordlist
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Return the list of Word of the wordlist
     *
     * @return The list of Word of the wordlist
     */
    public ArrayList<Word> getWords() {
        return words;
    }

    /**
     * Set the list of Word of the wordlist
     *
     * @param words The list of Word of the wordlist
     */
    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    /**
     * Removes the first occurence of the Word from the list
     *
     * @param word Word to be removed from the list
     */
    public void removeWord(Word word) {
        words.remove(word);
    }
}
