package com.eword.beans;

import java.util.ArrayList;
import java.util.Collection;

public class Wordlist {

    private String destinationLanguage;
    private int id;
    private String sourceLanguage;
    private String title;
    private ArrayList<Word> words;

    public void addWord(Word word) {
        words.add(word);
    }

    public String getDestinationLanguage() {
        return destinationLanguage;
    }

    public void setDestinationLanguage(String languageDestination) {
        this.destinationLanguage = languageDestination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String languageSource) {
        this.sourceLanguage = languageSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public void removeWord(Word word) {
        words.remove(word);
    }

    public void removeWord(int index) {
        words.remove(index);
    }

    public boolean removeWords(Collection<Word> collection) {
        return words.remove(collection);
    }
}
