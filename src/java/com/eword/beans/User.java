package com.eword.beans;

public class User {

    /**
     * Id of the user
     */
    private int id;

    /**
     * Language of the user
     */
    private String language = "us";

    /**
     * Password of the user
     */
    private String password;

    /**
     * Username of the user
     */
    private String username;

    /**
     * Return the id of the user
     *
     * @return The id of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the user
     *
     * @param id The id of the user
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return the language of the user
     *
     * @return The language of the user
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Set the language of the user
     *
     * @param language The language of the user
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Return the password of the of user
     *
     * @return The password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the user
     *
     * @param password The password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Return the username of the user
     *
     * @return The username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the user
     *
     * @param username The username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
