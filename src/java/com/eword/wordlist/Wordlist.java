package com.eword.wordlist;

import com.eword.users.User;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Represents a user's list of Words with a title, destination and source
 * languages.
 */
@Entity
public class Wordlist implements Serializable {

    /**
     * Destination language of the wordlist
     */
    @Column(name = "destination_language")
    private String destinationLanguage;

    /**
     * Id of the wordlist
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Source language of the wordlist
     */
    @Column(name = "source_language")
    private String sourceLanguage;

    /**
     * Title of the wordlist
     */
    private String title;

    /**
     * The owner of the wordlist
     */
    @ManyToOne
    private User user;

    /**
     * List of Words
     */
    @OneToMany(mappedBy = "wordlist", cascade = CascadeType.ALL)
    private List<Word> words;

    /**
     * Appends the Word to the end of the list
     *
     * @param word The Word to be appended
     */
    public void addWord(Word word) {
        words.add(word);
        if (word.getWordlist() != this) {
            word.setWordlist(this);
        }
    }

    /**
     * Return the destination language of the wordlist
     *
     * @return The destination language of the wordlist
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
     * Return the owner of the wordlist
     *
     * @return the owner of the wordlist
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the owner of the wordlist
     *
     * @param user The owner of the wordlist
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Return the list of Word of the wordlist
     *
     * @return The list of Word of the wordlist
     */
    public List<Word> getWords() {
        return words;
    }

    /**
     * Set the list of Word of the wordlist
     *
     * @param words The list of Word of the wordlist
     */
    public void setWords(List<Word> words) {
        this.words = words;
    }

    /**
     * Removes the first occurrence of the Word from the list
     *
     * @param word Word to be removed from the list
     */
    public void removeWord(Word word) {
        words.remove(word);
    }

}
