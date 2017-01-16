package com.eword.servlets;

import com.eword.beans.User;
import com.eword.business.SignupForm;
import com.eword.dao.DAOFactory;
import com.eword.dao.interfaces.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Signin extends HttpServlet {

    private static final String ATT_DAOFACTORY = "daofactory";
    private static final String ATT_LANG = "language";
    private static final String ATT_USER_ID = "user_id";
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = ((DAOFactory) getServletContext().getAttribute(ATT_DAOFACTORY)).getUserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = SignupForm.sha256(req.getParameter("password"));

        User user = userDAO.find(username, password);
        boolean authenticate = (user != null ? true : false);
        String message = "\"This user doesn't exist\"";

        if (authenticate) {
            HttpSession session = req.getSession();
            session.setAttribute(ATT_LANG, user.getLanguage());
            session.setAttribute(ATT_USER_ID, user.getId());
            message = "\"You successfully logged in !\"";
        }

        String json = "{\"authentication\":" + authenticate + ", \"message\":" + message + "}";
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(json);
    }
}
