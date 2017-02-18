package com.eword.dao;

import com.eword.beans.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserDAO {

    @PersistenceContext(unitName = "MySQL")
    private EntityManager em;

    /**
     * Find a user from their token
     *
     * @param token The user's token
     * @return A User if a user with this token could be found. Null otherwise
     */
    public User findIdFromToken(String token) {
        User user = null;

        Query requete = em.createQuery("SELECT u FROM User u WHERE u.token=:token");
        requete.setParameter("token", token);
        user = (User) requete.getSingleResult();

        return user;
    }

    /**
     * Add a User
     *
     * @param user The User to add
     */
    public void create(User user) {
        em.persist(user);
    }

    /**
     * Find if a User with the username exists
     *
     * @param username The username of the User to look for
     * @return True if a User with this username exists
     */
    public boolean exist(String username) {
        boolean exist = false;

        Query requete = em.createQuery("SELECT u FROM User u WHERE u.username=:username");
        requete.setParameter("username", username);
        if (requete.getResultList().isEmpty()) {
            exist = true;
        }

        return exist;
    }

    /**
     * Find a User from their username and password
     *
     * @param username The username of the User to retrieve
     * @param password The password of the User to retrieve
     * @return A User if a user with these parameters could be found. Null
     * otherwise
     */
    public User find(String username, String password) {
        User user = null;

        Query requete = em.createQuery("SELECT u FROM User u WHERE u.username=:username AND u.password=:password");
        requete.setParameter("username", username);
        requete.setParameter("password", password);
        user = (User) requete.getSingleResult();

        return user;
    }

    /**
     * Find a User from their id
     *
     * @param userId The user's id
     * @return A User if a user with this id could be found. Null otherwise
     */
    public User find(Integer userId) {
        User user;
        user = em.find(User.class, userId);

        return user;
    }

    /**
     * Update the User
     *
     * @param user The user to be updated
     */
    public User update(User user) {
        return em.merge(user);
    }
}
