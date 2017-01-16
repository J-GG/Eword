package com.eword.business;

import com.eword.beans.User;
import com.eword.dao.interfaces.UserDAO;
import java.security.MessageDigest;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public class SignupForm {

    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_USERNAME = "username";

    private HashMap<String, String> erreurs = new HashMap<>();
    private String result;

    public HashMap<String, String> getErreurs() {
        return erreurs;
    }

    public String getResult() {
        return result;
    }

    private void passwordValidation(String password) throws Exception {
        if (password != null) {
            if (password.length() < 4) {
                throw new Exception("The password must be more than 4 characters long");
            }
        } else {
            throw new Exception("A password is required");
        }
    }

    public static String sha256(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public User signupValidation(HttpServletRequest req, UserDAO userDAO) {
        String username = req.getParameter(PARAM_USERNAME).trim();
        String password = req.getParameter(PARAM_PASSWORD).trim();
        User user = new User();

        try {
            usernameValidation(username, userDAO);
        } catch (Exception ex) {
            erreurs.put(PARAM_USERNAME, ex.getMessage());
        }
        user.setUsername(username);

        try {
            passwordValidation(password);

        } catch (Exception ex) {
            erreurs.put(PARAM_PASSWORD, ex.getMessage());
        }

        user.setPassword(sha256(password));

        if (erreurs.isEmpty()) {
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
