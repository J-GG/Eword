package com.eword.dao.interfaces;

import com.eword.beans.User;

public interface UserDAO {

    /**
     * Find a user from their token
     *
     * @param token The user's token
     * @return A User if a user with this token could be found. Null otherwise
     */
    User findIdFromToken(String token);

    /**
     * Add a User
     *
     * @param user The User to add
     */
    void create(User user);

    /**
     * Find if a User with the username exists
     *
     * @param username The username of the User to look for
     * @return True if a User with this username exists
     */
    boolean exist(String username);

    /**
     * Find a User from their username and password
     *
     * @param username The username of the User to retrieve
     * @param password The password of the User to retrieve
     * @return A User if a user with these parameters could be found. Null
     * otherwise
     */
    User find(String username, String password);

    /**
     * Find a User from their id
     *
     * @param userId The user's id
     * @return A User if a user with this id could be found. Null otherwise
     */
    User find(Integer userId);

    /**
     * Update the User
     *
     * @param user The user to be updated
     */
    void update(User user);
}
