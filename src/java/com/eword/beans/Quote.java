package com.eword.beans;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents a quote including the name of the author.
 */
@Entity
public class Quote implements Serializable {

    /**
     * Author's name of the quote
     */
    private String author;

    /**
     * Content of the quote
     */
    private String content;

    /**
     * Id of the quote
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Return the author's name of the quote
     *
     * @return The author's name of the quote
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the author's name of the quote
     *
     * @param author The author's name of the quote
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Return the content of the quote
     *
     * @return The content of the quote
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the content of the quote
     *
     * @param content The content of the quote
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Return the id of the quote
     *
     * @return The id of the quote
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the quote
     *
     * @param id The id of the quote
     */
    public void setId(int id) {
        this.id = id;
    }
}
