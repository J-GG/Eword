package com.eword.membership.business;

import com.eword.general.util.StringUtils;
import com.eword.lang.business.Lang.Language;
import com.eword.membership.bean.User;
import com.eword.membership.dao.UserDAO;
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
    private final static String KEY_LANG_CODE = "LANG_CODE";

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
     * Return the map of errors
     *
     * @return The map of errors
     */
    public HashMap<String, String> getErrors() {
        return errors;
    }

    /**
     * Check whether the form is correct or not. If it is correct, the user is
     * added to the data and returned
     *
     * @param userDAO Object enabling to communicate with the User data layer
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
            UserFieldsValidation.usernameValidation(username, userDAO);
        } catch (Exception ex) {
            errors.put(PARAM_USERNAME, ex.getMessage());
        }
        user.setUsername(username);

        //The password is validated and set
        try {
            UserFieldsValidation.passwordValidation(password);
        } catch (Exception ex) {
            errors.put(PARAM_PASSWORD, ex.getMessage());
        }
        user.setPassword(StringUtils.sha256(password));

        //The language is set
        HttpSession session = req.getSession();
        HashMap<String, String> translations = (HashMap<String, String>) session.getAttribute(ATT_LANG);
        String lang = translations.get(KEY_LANG_CODE);
        user.setLanguage(Language.getLanguageFromCode(lang));

        //If there is no error, the user is stored
        if (errors.isEmpty()) {
            userDAO.create(user);
        }

        return user;
    }

}
