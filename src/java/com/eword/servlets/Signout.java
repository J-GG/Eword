package com.eword.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Signout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //The session is destroyed
        HttpSession session = req.getSession();
        session.invalidate();

        //The user is redirected to the last page visited or to the home page
        String address = req.getHeader("referer");
        if (address == null) {
            address = req.getContextPath();
        }

        resp.sendRedirect(address);
    }
}
