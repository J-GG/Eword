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

    private static final String ATT_FORM = "form";
    private static final String ATT_USER = "user";
    private static final String VIEW = "/WEB-INF/signup.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute(ATT_FORM) != null) {
            req.setAttribute(ATT_FORM, session.getAttribute(ATT_FORM));
            session.setAttribute(ATT_FORM, null);
        }

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignupForm signupForm = new SignupForm();
        User user = signupForm.signupValidation(req);

        if (signupForm.getErreurs().isEmpty()) {
            HttpSession session = req.getSession();
            session.setAttribute(ATT_FORM, signupForm);
            resp.sendRedirect(req.getRequestURI());
        } else {

            req.setAttribute(ATT_USER, user);
            req.setAttribute(ATT_FORM, signupForm);

            this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        }
    }
}
