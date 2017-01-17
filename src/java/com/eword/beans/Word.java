package com.eword.beans;

public class Word {

    /**
     * Examples of the Word
     */
    private String examples;

    /**
     * Id of the Word
     */
    private int id;

    /**
     * Part of speech of the Word
     */
    private String partOfSpeech;

    /**
     * Translation of the Word
     */
    private String translation;

    /**
     * Word
     */
    private String word;

    /**
     * Return the examples of the word
     *
     * @return The examples of the word
     */
    public String getExamples() {
        return examples;
    }

    /**
     * Set the examples of the word
     *
     * @param examples The examples of the word
     */
    public void setExamples(String examples) {
        this.examples = examples;
    }

    /**
     * Return the id of the word
     *
     * @return The id of the word
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the word
     *
     * @param id The id of the word
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return the part of speech of the word
     *
     * @return The part of speech of the word
     */
    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    /**
     * Set the part of speech of the word
     *
     * @param partOfSpeech The part of speech of the word
     */
    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    /**
     * Return the translation of the word
     *
     * @return The translation of the word
     */
    public String getTranslation() {
        return translation;
    }

    /**
     * Set the translation of the word
     *
     * @param translation The translation of the word
     */
    public void setTranslation(String translation) {
        this.translation = translation;
    }

    /**
     * Return the word
     *
     * @return The word
     */
    public String getWord() {
        return word;
    }

    /**
     * Set the word
     *
     * @param word The word
     */
    public void setWord(String word) {
        this.word = word;
    }
}
