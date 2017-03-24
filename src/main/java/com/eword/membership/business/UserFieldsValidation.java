package com.eword.membership.business;

import com.eword.membership.dao.UserDAO;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;
import javax.servlet.http.Part;

/**
 * This class provides functions to check fields related to users.
 */
public class UserFieldsValidation {

    /**
     * The max length of the username
     */
    private static final int MAX_LENGTH_USERNAME = 30;

    /**
     * The min length of the username
     */
    private static final int MIN_LENGTH_USERNAME = 4;

    /**
     * The max size of the picture
     */
    private static final int MAX_SIZE_PICTURE = 1024 * 1024;

    /**
     * The min length of the password
     */
    private static final int MIN_LENGTH_PASSWORD = 4;

    /**
     * The mime types allowed
     */
    private static final String[] TYPES = {"image/jpeg", "image/png"};

    /**
     * Check whether the email is correct or not. Throw an exception if the
     * email is incorrect
     *
     * @param email The email to check
     * @throws Exception
     */
    static void emailValidation(String email) throws Exception {
        Pattern pattern_email = Pattern.compile("^[a-z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}$");
        if (email != null) {
            if (!pattern_email.matcher(email).find()) {
                throw new Exception("The value is not a valid email address");
            }
        } else {
            throw new Exception("The email is required");
        }
    }

    /**
     * Check whether the password is correct or not. Throw an exception if the
     * password is incorrect
     *
     * @param password The password to check
     */
    public static void passwordValidation(String password) throws Exception {
        if (password != null) {
            if (password.length() < MIN_LENGTH_PASSWORD) {
                throw new Exception("The password must be at least " + MIN_LENGTH_PASSWORD + " characters");
            }
        } else {
            throw new Exception("The password is required");
        }
    }

    /**
     * Check whether the username is correct or not. Throw an exception if the
     * username is incorrect
     *
     * @param username The username to check
     * @param userDAO Object enabling to communicate with the User data layer
     */
    public static void usernameValidation(String username, UserDAO userDAO) throws Exception {
        if (username != null) {
            if (username.length() < MIN_LENGTH_USERNAME || username.length() > MAX_LENGTH_USERNAME) {
                throw new Exception("The username must be at least " + MIN_LENGTH_USERNAME + " and no more than " + MAX_LENGTH_USERNAME + " characters long");
            }

            if (userDAO.exist(username)) {
                throw new Exception("The username already exists");
            }
        } else {
            throw new Exception("The username is required");
        }
    }

    /**
     * Check whether the picture is correct or not. Throw an exception if the
     * picture is incorrect
     *
     * @param picture The picture to check
     */
    public static void pictureValidation(Part picture) throws Exception {
        if (picture != null) {
            if (!Arrays.asList(TYPES).contains(picture.getHeader("Content-Type"))) {
                throw new Exception("The file must be an image (jpeg, jpg, png)");
            }

            if (picture.getSize() > MAX_SIZE_PICTURE) {
                throw new Exception("The file exceeds the allowed size");
            }
        } else {
            throw new Exception("The picture is required");
        }
    }

    /**
     * Check whether the birth date is correct or not. Throw an exception if the
     * birth date is incorrect
     *
     * @param birthdate The birth date to check
     */
    public static void birthdateValidation(Date birthdate) throws Exception {
        if (birthdate.after(new Date())) {
            throw new Exception("Your birth date cannot be in the future");
        }
    }
}
