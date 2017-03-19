package com.eword.membership;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
     * Map of all the input errors
     */
    private HashMap<String, String> errors = new HashMap<>();

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

        Part part = null;
        try {
            part = req.getPart("user_picture");
        } catch (IllegalStateException ex) {
            errors.put(PARAM_PICTURE, "The file exceeds the allowed size");
        } catch (IOException ex) {
            errors.put(PARAM_PICTURE, "Server configuration error");
        } catch (ServletException ex) {
            errors.put(PARAM_PICTURE, "The file exceeds the allowed size");
        }

        if (errors.isEmpty()) {
            try {
                UserFieldsValidation.pictureValidation(part);
            } catch (Exception ex) {
                errors.put(PARAM_PICTURE, ex.getMessage());
            }
        }

        String filename = null;
        if (errors.isEmpty()) {
            try {
                String userID = String.valueOf(req.getSession().getAttribute(ATT_USER_ID));
                filename = userID + getExtension(part);
                String path = req.getServletContext().getInitParameter(CONTEXT_PARAM_PICTURES_PATH);
                writeFile(part, filename, path);
                deletePicture(path, user.getPicture(), filename, userID);
            } catch (IOException e) {
                errors.put(PARAM_PICTURE, "Cannot save the file");
            }
        }
        if (errors.isEmpty()) {
            user.setPicture(filename);
        }

        //If there is no error, the user is updated
        if (errors.isEmpty()) {
            userDAO.update(user);
        }

        return user;
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
