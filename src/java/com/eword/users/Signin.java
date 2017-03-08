package com.eword.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/authenticate")
public class Signin extends HttpServlet {

    /**
     * Name of the remember me checkbox
     */
    private static final String PARAM_REMEMBER_ME = "remember_me";

    /**
     * Name of the request attribute containing the translations for the display
     * language chosen by the user
     */
    private final static String ATT_LANG = "lang";

    /**
     * Key of the translation map corresponding to the message displayed when
     * the user can't be found
     */
    private final static String TR_USER_DOESNT_EXIST = "USER_DOESNT_EXIST";

    /**
     * Key of the translation map corresponding to the message displayed when
     * the connection is successful
     */
    private final static String TR_LOGGED_IN_SUCCESSFULLY = "LOGGED_IN_SUCCESSFULLY";

    /**
     * Object enabling to communicate with the User data layer
     */
    @EJB
    private UserDAO userDAO;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<String, String> translations = (Map<String, String>) session.getAttribute(ATT_LANG);

        //We verify that the user exists
        UserBusiness userBusiness = new UserBusiness();
        User user = userBusiness.getUserFromLogin(userDAO, req);
        boolean authenticate = (user != null);
        String message = "\"" + translations.get(TR_USER_DOESNT_EXIST) + "\"";

        //If the user exists, we set information as session attributes
        if (authenticate) {
            boolean rememberMeChecked = Boolean.valueOf(req.getParameter(PARAM_REMEMBER_ME));
            AuthenticationBusiness authenticationBusiness = new AuthenticationBusiness();
            authenticationBusiness.authenticatedUser(user, rememberMeChecked, userDAO, req, resp);

            message = "\"" + translations.get(TR_LOGGED_IN_SUCCESSFULLY) + "\"";
        }

        //The JSON file is formed and printed
        String json = "{\"authentication\":" + authenticate + ", \"message\":" + message + "}";
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(json);
    }
}
