package com.eword.business;

import com.eword.beans.User;
import com.eword.dao.DAOFactory;
import com.eword.dao.interfaces.UserDAO;
import javax.servlet.http.HttpServletRequest;

public class UserBusiness {

    /**
     * Object enabling to communicate with the User data layer
     */
    private static final UserDAO USER_DAO = DAOFactory.getInstance().getUserDAO();

    /**
     * Return the User corresponding to the username and the password if they
     * exist.
     *
     * @param req The request
     * @return The User if they exist. Null otherwise
     */
    public static User getUser(HttpServletRequest req) {
        //We retrieve the parameters of the request to identify the user
        String username = req.getParameter("username");
        String password = StringUtils.sha256(req.getParameter("password"));

        User user = USER_DAO.find(username, password);

        return user;
    }
}
