package com.eword.business;

import com.eword.beans.User;
import com.eword.dao.UserDAO;
import com.eword.lang.Lang.Language;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignupForm {

    /**
     * Name of the session attribute containing the translations
     */
    private static final String ATT_LANG = "lang";

    /**
     * Key of the translation Map to retrieve the code of the language
     */
    private final static String KEY_LANG_CODE = "langCode";

    /**
     * Name of the field of the form containing the password
     */
    private static final String PARAM_PASSWORD = "password";

    /**
     * Name of the field of the form containing the username
     */
    private static final String PARAM_USERNAME = "username";

    /**
     * Map of all the input errors
     */
    private HashMap<String, String> errors = new HashMap<>();

    /**
     * Message to display after the validation of the form
     */
    private String result;

    /**
     * Return the map of errors
     *
     * @return The map of errors
     */
    public HashMap<String, String> getErrors() {
        return errors;
    }

    /**
     * Return the message to display after the validation of the form
     *
     * @return The message to display
     */
    public String getResult() {
        return result;
    }

    /**
     * Check whether the password is correct or not
     *
     * @param password The password to check. It can't be null and has a minimal
     * size
     */
    private void passwordValidation(String password) throws Exception {
        if (password != null) {
            if (password.length() < 4) {
                throw new Exception("The password must be more than 4 characters long");
            }
        } else {
            throw new Exception("A password is required");
        }
    }

    /**
     * Check whether the form is correct or not. If it is correct, the user is
     * added to the data and returned
     *
     * @param req The request
     * @return A User with the information from the form
     */
    public User signupValidation(UserDAO userDAO, HttpServletRequest req) {

        User user = new User();

        //Parameters are retrieved from the request
        String username = req.getParameter(PARAM_USERNAME).trim();
        String password = req.getParameter(PARAM_PASSWORD).trim();

        //The username is validated and set
        try {
            usernameValidation(username, userDAO);
        } catch (Exception ex) {
            errors.put(PARAM_USERNAME, ex.getMessage());
        }
        user.setUsername(username);

        //The password is validated and set
        try {
            passwordValidation(password);

        } catch (Exception ex) {
            errors.put(PARAM_PASSWORD, ex.getMessage());
        }
        user.setPassword(StringUtils.sha256(password));

        //The language is set
        HttpSession session = req.getSession();
        HashMap<String, String> translations = (HashMap<String, String>) session.getAttribute(ATT_LANG);
        String lang = translations.get(KEY_LANG_CODE);
        user.setLanguage(Language.getLanguageFromCode(lang));

        //The result message depends on whether there are errors or not
        if (errors.isEmpty()) {
            //The user is added to the data
            userDAO.create(user);

            result = "You successfully registered ! We're pleased that you have chosen to become part of the community.<br />\n"
                    + "            <ul>\n"
                    + "                <li><a href=" + req.getContextPath() + ">Return to the home page</a></li>\n"
                    + "            </ul>\n"
                    + "";
        } else {
            result = "Your registration failed.";
        }

        return user;
    }

    /**
     * Check whether the username is correct or not
     *
     * @param username The username to check
     */
    private void usernameValidation(String username, UserDAO userDAO) throws Exception {
        if (username != null) {
            if (username.length() < 4 || username.length() > 30) {
                throw new Exception("The username must be more than 4 and less than 30 characters long");
            } else if (!username.matches("^[a-zA-Z0-9_\\.]+$")) {
                throw new Exception("The username can only consist of alphabetical, number, dot and underscore");
            } else if (userDAO.exist(username)) {
                throw new Exception("The username already exists");
            }
        } else {
            throw new Exception("The username is required");
        }
    }
}
