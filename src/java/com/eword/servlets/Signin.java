package com.eword.servlets;

import com.eword.beans.User;
import com.eword.business.AuthenticationBusiness;
import com.eword.business.UserBusiness;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/authenticate")
public class Signin extends HttpServlet {

    /**
     * Name of the remember me checkbox
     */
    private static final String PARAM_REMEMBER_ME = "remember_me";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //We verify that the user exists
        UserBusiness userBusiness = new UserBusiness();
        User user = userBusiness.getUserFromLogin(req);
        boolean authenticate = (user != null);
        String message = "\"This user doesn't exist\"";

        //If the user exists, we set information as session attributes
        if (authenticate) {
            boolean rememberMeChecked = Boolean.valueOf(req.getParameter(PARAM_REMEMBER_ME));
            AuthenticationBusiness authenticationBusiness = new AuthenticationBusiness();
            authenticationBusiness.authenticatedUser(user, rememberMeChecked, req, resp);

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
