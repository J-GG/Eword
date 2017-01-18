package com.eword.servlets;

import com.eword.beans.User;
import com.eword.business.SignupForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Signup extends HttpServlet {

    /**
     * Name of the request attribute containing a SignupForm
     */
    private static final String ATT_FORM = "form";

    /**
     * Name of the request attribute containing a User object
     */
    private static final String ATT_USER = "user";

    /**
     * JSP file to display
     */
    private static final String VIEW = "/WEB-INF/signup.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //If the form attribute exists in the session scope, it is removed and set as a request attribute
        HttpSession session = req.getSession();
        if (session.getAttribute(ATT_FORM) != null) {
            req.setAttribute(ATT_FORM, session.getAttribute(ATT_FORM));
            session.setAttribute(ATT_FORM, null);
        }

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //The form is checked by the SignupForm object
        SignupForm signupForm = new SignupForm();
        User user = signupForm.signupValidation(req);

        if (signupForm.getErrors().isEmpty()) {
            //If there is no error, the SignupForm object is set as a session attribute and the page is reloaded (to avoid the forward and the refresh problem)
            HttpSession session = req.getSession();
            session.setAttribute(ATT_FORM, signupForm);
            resp.sendRedirect(req.getRequestURI());
        } else {
            //If there are errors, the User and the SignupForm objects are added to the request attributes
            req.setAttribute(ATT_USER, user);
            req.setAttribute(ATT_FORM, signupForm);

            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        }
    }
}
