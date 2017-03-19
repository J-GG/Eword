package com.eword.membership;

import com.eword.lang.Lang;
import com.eword.lang.Lang.Language;
import com.eword.lang.LocalDate;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * Represents a member of the application. Contains personal information as well
 * as information relevant to the application.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    /**
     * The user's birth date
     */
    @Column(name = "birth_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthDate;

    /**
     * The user's email address
     */
    @Column(name = "email_address")
    private String email;

    /**
     * The user's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The user's language in which the application is displayed
     */
    @Enumerated(EnumType.STRING)
    private Language language = Lang.getDefaultLang();

    /**
     * The user's password
     */
    private String password;

    /**
     * The user's picture
     */
    private String picture;

    /**
     * The user's token
     */
    private String token;

    /**
     * The user's username
     */
    private String username;

    /**
     * Return the user's birth date
     *
     * @return The user's birth date
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Set the user's birth date
     *
     * @param birthDate The user's birth date
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Return the user's email address
     *
     * @return The user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the user's email address
     *
     * @param email The user's email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Return the user's id
     *
     * @return The user's id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the user's id
     *
     * @param id The user's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return the user's language in which the application is displayed
     *
     * @return The user's language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Set the user's language in which the application is displayed
     *
     * @param language The user's language
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Return the user's birth date in the format of their language
     *
     * @return The user's birth date in the format of their language
     */
    public String getLocalBirthDate() {
        String localBirthDate = null;

        if (birthDate != null) {
            localBirthDate = LocalDate.getLocalDate(birthDate, language);
        }

        return localBirthDate;
    }

    /**
     * Return the user's password
     *
     * @return The user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user's password
     *
     * @param password The user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Return the user's picture
     *
     * @return The user's picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Set the user's picture
     *
     * @param picture The user's picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Return the user's token
     *
     * @return The user's token
     */
    public String getToken() {
        return token;
    }

    /**
     * Set the user's token
     *
     * @param token The user's token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Return the user's username
     *
     * @return The user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the user's username
     *
     * @param username The user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
