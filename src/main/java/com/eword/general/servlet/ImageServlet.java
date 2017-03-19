package com.eword.general.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/images/pictures/*")
public class ImageServlet extends HttpServlet {

    /**
     * Name of the context parameter containing the pictures path
     */
    private static final String CONTEXT_PARAM_PICTURES_PATH = "PicturesPath";

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String context = req.getContextPath();
        String path = null;

        //The filename is retrieved from the request
        String filename = req.getPathInfo().substring(1);

        //We determine what type of image we try to display
        Pattern pattern_picture = Pattern.compile(context + "/images/pictures/");

        if (pattern_picture.matcher(req.getRequestURI()).find()) {
            path = req.getServletContext().getInitParameter(CONTEXT_PARAM_PICTURES_PATH);
        }

        //The response is prepared and sent
        File file = new File(path, filename);
        res.setHeader("Content-Type", getServletContext().getMimeType(filename));
        res.setHeader("Content-Length", String.valueOf(file.length()));
        res.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        Files.copy(file.toPath(), res.getOutputStream());
    }

}
