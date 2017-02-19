package com.eword.business;

import com.eword.beans.User;
import com.eword.dao.UserDAO;
import javax.servlet.http.HttpServletRequest;

public class UserBusiness {

    /**
     * Return the User corresponding to the username and the password if they
     * exist.
     *
     * @param req The request
     * @return The User if they exist. Null otherwise
     */
    public User getUserFromLogin(UserDAO userDAO, HttpServletRequest req) {
        //We retrieve the parameters of the request to identify the user
        String username = req.getParameter("username");
        String password = StringUtils.sha256(req.getParameter("password"));

        User user = userDAO.find(username, password);

        return user;
    }

    /**
     * Return the User corresponding to the id
     *
     * @param userId The user's id
     * @return The User if they exist. Null otherwise
     */
    public User getUserFromId(Integer userId, UserDAO userDAO) {

        User user = userDAO.find(userId);

        return user;
    }

    /**
     * Update the User in the data layer
     *
     * @param user The User to be updated
     */
    public void updateUser(User user, UserDAO userDAO) {
        userDAO.update(user);
    }
}
