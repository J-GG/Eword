package com.eword.servlets;

import com.eword.beans.User;
import com.eword.business.UserBusiness;
import com.eword.lang.Lang;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Signin extends HttpServlet {

    /**
     * Name of the session attribute containing the translations
     */
    private static final String ATT_LANG = "lang";

    /**
     * Name of the session attribute containing the id of the user
     */
    private static final String ATT_USER_ID = "user_id";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //We verify that the user exists
        User user = UserBusiness.getUser(req);
        boolean authenticate = (user != null);
        String message = "\"This user doesn't exist\"";

        //If the user exists, we set information as session attributes
        if (authenticate) {
            HttpSession session = req.getSession();
            session.setAttribute(ATT_LANG, Lang.getInstance().getTranslations(user.getLanguage()));
            session.setAttribute(ATT_USER_ID, user.getId());
            message = "\"You successfully logged in !\"";
        }

        //The JSON file is formed and printed
        String json = "{\"authentication\":" + authenticate + ", \"message\":" + message + "}";
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(json);
    }
}
