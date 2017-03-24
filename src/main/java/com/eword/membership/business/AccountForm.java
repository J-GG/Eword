package com.eword.membership.business;

import com.eword.general.util.StringUtils;
import com.eword.lang.business.Lang;
import com.eword.lang.business.LocalDate;
import com.eword.membership.bean.User;
import com.eword.membership.dao.UserDAO;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

public class AccountForm {

    /**
     * Name of the session attribute containing the id of the user
     */
    private static final String ATT_USER_ID = "user_id";

    /**
     * Name of the context parameter containing the pictures path
     */
    private static final String CONTEXT_PARAM_PICTURES_PATH = "PicturesPath";

    /**
     * Name of the field of the form containing the picture
     */
    private static final String PARAM_PICTURE = "picture";

    /**
     * Name of the field of the form containing the email address
     */
    private static final String PARAM_EMAIL = "email";

    /**
     * Name of the field of the form containing the old password
     */
    private static final String PARAM_OLD_PASSWORD = "old_password";

    /**
     * Name of the field of the form containing the new password
     */
    private static final String PARAM_NEW_PASSWORD = "new_password";

    /**
     * Name of the field of the form containing the language
     */
    private static final String PARAM_LANGUAGE = "language";

    /**
     * Name of the field of the form containing the birth date
     */
    private static final String PARAM_BIRTHDATE = "birthdate";

    /**
     * Name of the request attribute containing the translations for the display
     * language chosen by the user
     */
    private final static String ATT_LANG = "lang";

    /**
     * Map of all the input errors
     */
    private HashMap<String, String> errors = new HashMap<>();

    private String json;

    /**
     * Return the extension of the part
     *
     * @param part Part whose the extension is returned
     * @return The extension of the part
     */
    private static String getExtension(Part part) {
        String extension = null;

        //Loop on each of the element of the "content-disposition" header
        for (String contentDisposition : part.getHeader("content-disposition").split(";")) {

            //When it is the filename, we get the extension
            if (contentDisposition.trim().startsWith("filename")) {
                extension = contentDisposition.substring(contentDisposition.lastIndexOf('.')).replace("\"", "");
                extension = extension.toLowerCase();
            }
        }

        return extension;
    }

    /**
     * Check whether the form is correct or not. If it is correct, the user is
     * updated and returned
     *
     * @param userDAO Object enabling to communicate with the User data layer
     * @param req The request
     * @return A User with the information
     */
    public User accountValidation(UserDAO userDAO, HttpServletRequest req) {
        //The user is retrieved from the id and added to the request attributes
        HttpSession session = req.getSession();

        int id = (int) session.getAttribute(ATT_USER_ID);
        User user = UserBusiness.getUserFromId(id, userDAO);

        //We deal with each of the inputs
        //Picture
        Part picture = null;
        try {
            picture = req.getPart(PARAM_PICTURE);
        } catch (IllegalStateException ex) {
            errors.put(PARAM_PICTURE, "The file exceeds the allowed size");
        } catch (IOException ex) {
            errors.put(PARAM_PICTURE, "Server configuration error");
        } catch (ServletException ex) {
            errors.put(PARAM_PICTURE, "The file exceeds the allowed size");
        }

        if (picture != null) {
            pictureTreatment(picture, user, req);
        }

        //Password
        String oldPassword = req.getParameter("old_password");
        String newPassword = req.getParameter("new_password");

        if (oldPassword != null && newPassword != null) {
            passwordTreatment(oldPassword, newPassword, user);
        }

        //Email
        String email = req.getParameter(PARAM_EMAIL);

        if (email != null) {
            emailTreatment(email, user);
        }

        //Language
        String languageCode = req.getParameter("language");
        if (languageCode != null) {
            languageTreatment(languageCode, user, req);
        }

        //Birthdate
        String birthdate = req.getParameter("birthdate");
        if (birthdate != null) {
            birthdateTreatment(birthdate, user);
        }

        //If there is no error, the user is updated
        if (errors.isEmpty()) {
            userDAO.update(user);
        }

        return user;
    }

    private void birthdateTreatment(String birthdate, User user) {
        Date standardBirthdate = null;

        if (!birthdate.isEmpty()) {
            try {
                standardBirthdate = LocalDate.getStandardDate(birthdate, user.getLanguage());
            } catch (ParseException ex) {
                errors.put(PARAM_BIRTHDATE, "The value is not a valid date format");
            }

            if (errors.get(PARAM_BIRTHDATE) == null) {
                try {
                    UserFieldsValidation.birthdateValidation(standardBirthdate);
                } catch (Exception ex) {
                    errors.put(PARAM_BIRTHDATE, ex.getMessage());
                }
            }
        }

        if (errors.get(PARAM_BIRTHDATE) == null) {
            user.setBirthDate(standardBirthdate);
        }

        addJson(PARAM_BIRTHDATE, user.getLocalBirthDate());
    }

    private void emailTreatment(String email, User user) {
        try {
            if (!email.isEmpty()) {
                UserFieldsValidation.emailValidation(email);
            }
        } catch (Exception ex) {
            errors.put(PARAM_EMAIL, ex.getMessage());
        }

        if (errors.get(PARAM_EMAIL) == null) {
            user.setEmail(email);
        }

        addJson(PARAM_EMAIL, email);
    }

    private void languageTreatment(String languageCode, User user, HttpServletRequest req) {
        Lang.Language language = Lang.Language.getLanguageFromCode(languageCode);
        String value = null;

        if (language == null) {
            errors.put(PARAM_LANGUAGE, "No language is selected");
        } else {
            //The session attribute storing the language is updated
            HttpSession session = req.getSession();
            session.setAttribute(ATT_LANG, Lang.getInstance().getTranslations(language));

            value = language.getName();
            user.setLanguage(language);
        }

        addJson(PARAM_LANGUAGE, value);
    }

    private void passwordTreatment(String oldPassword, String newPassword, User user) {

        if (!StringUtils.sha256(oldPassword).equals(user.getPassword())) {
            errors.put(PARAM_OLD_PASSWORD, "The old password is incorrect");
        }

        try {
            UserFieldsValidation.passwordValidation(newPassword);
        } catch (Exception ex) {
            errors.put(PARAM_NEW_PASSWORD, ex.getMessage());
        }

        if (errors.get(PARAM_OLD_PASSWORD) == null && errors.get(PARAM_NEW_PASSWORD) == null) {
            user.setPassword(StringUtils.sha256(newPassword));
        }

        addJson(PARAM_OLD_PASSWORD, "**********");
        addJson(PARAM_NEW_PASSWORD, "**********");
    }

    private void pictureTreatment(Part picture, User user, HttpServletRequest req) {
        try {
            UserFieldsValidation.pictureValidation(picture);
        } catch (Exception ex) {
            errors.put(PARAM_PICTURE, ex.getMessage());
        }

        String filename = null;
        if (errors.get(PARAM_PICTURE) == null) {
            try {
                String userID = String.valueOf(req.getSession().getAttribute(ATT_USER_ID));
                filename = userID + getExtension(picture);
                String path = req.getServletContext().getInitParameter(CONTEXT_PARAM_PICTURES_PATH);
                writeFile(picture, filename, path);
                deletePicture(path, user.getPicture(), filename, userID);
            } catch (IOException e) {
                errors.put(PARAM_PICTURE, "Cannot save the file");
            }
        }

        if (errors.get(PARAM_PICTURE) == null) {
            user.setPicture(filename);
        }

        addJson(PARAM_PICTURE, filename);
    }

    private void addJson(String param, String value) {
        if (json == null) {
            json = "{";
        } else {
            json = json.substring(0, json.length() - 1) + ",";
        }
        json += "\"" + param + "\": {\"error\": \"" + errors.get(param) + "\", \"value\" : \"" + value + "\"}}";
    }

    public String getJson() {
        return json;
    }

    /**
     * Delete the user's picture from the disk if the filename contains the
     * user's id and is not the same as the new picture
     *
     * @param path The path where pictures are stored
     * @param oldFilename The filename of the user's old picture
     * @param newFilename The filename of the user's new picture
     * @param userID The user's id
     */
    private void deletePicture(String path, String oldFilename, String newFilename, String userID) {
        if (oldFilename.contains(userID) && !oldFilename.equals(newFilename)) {
            File file = new File(path, oldFilename);
            file.delete();
        }
    }

    /**
     * Return the map of errors
     *
     * @return The map of errors
     */
    public HashMap<String, String> getErrors() {
        return errors;
    }

    private void writeFile(Part part, String filename, String path) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), 10240);
            File f = new File(path + filename);
            sortie = new BufferedOutputStream(new FileOutputStream(f), 10240);

            byte[] tampon = new byte[10240];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            sortie.close();
            entree.close();

        }
    }

}
