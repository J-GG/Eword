package com.eword.users;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * A class which enables to interact with the data layer regarding the User
 * entity.
 */
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

        try {
            Query requete = em.createQuery("SELECT u FROM User u WHERE u.token=:token");
            requete.setParameter("token", token);
            user = (User) requete.getSingleResult();
        } catch (Exception ex) {
        }

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

        Query requete = em.createQuery("SELECT u.id FROM User u WHERE u.username=:username");
        requete.setParameter("username", username);
        if (!requete.getResultList().isEmpty()) {
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

        try {
            Query requete = em.createQuery("SELECT u FROM User u WHERE u.username=:username AND u.password=:password");
            requete.setParameter("username", username);
            requete.setParameter("password", password);
            user = (User) requete.getSingleResult();
        } catch (Exception ex) {

        }

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
     * @return the update User
     */
    public User update(User user) {
        return em.merge(user);
    }
}
