package com.eword.membership.business;

import com.eword.general.util.StringUtils;
import com.eword.membership.bean.User;
import com.eword.membership.dao.UserDAO;
import javax.servlet.http.HttpServletRequest;

public class UserBusiness {

    /**
     * Return the User corresponding to the username and the password if they
     * exist.
     *
     * @param userDAO Object enabling to communicate with the User data layer
     * @param req The request
     * @return The User if they exist. Null otherwise
     */
    public static User getUserFromLogin(UserDAO userDAO, HttpServletRequest req) {
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
     * @param userDAO Object enabling to communicate with the User data layer
     * @return The User if they exist. Null otherwise
     */
    public static User getUserFromId(Integer userId, UserDAO userDAO) {

        User user = userDAO.find(userId);

        return user;
    }

    /**
     * Update the User in the data layer
     *
     * @param user The User to be updated
     * @param userDAO Object enabling to communicate with the User data layer
     */
    public static void updateUser(User user, UserDAO userDAO) {
        userDAO.update(user);
    }
}
