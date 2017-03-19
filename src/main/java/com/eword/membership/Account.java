package com.eword.membership;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/membership/account")
@MultipartConfig(location = "/img",
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024,
        maxRequestSize = 1024 * 1024 * 2)
public class Account extends HttpServlet {

    /**
     * Name of the request attribute containing a User object
     */
    private static final String ATT_USER = "user";

    /**
     * Name of the session attribute containing the id of the user
     */
    private static final String ATT_USER_ID = "user_id";

    /**
     * JSP file to display
     */
    private static final String VIEW = "/WEB-INF/account.jsp";

    /**
     * Object enabling to communicate with the User data layer
     */
    @EJB
    private UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //The user is retrieved from the id and added to the request attributes
        HttpSession session = req.getSession();

        int id = (int) session.getAttribute(ATT_USER_ID);
        User user = UserBusiness.getUserFromId(id, userDAO);

        req.setAttribute(ATT_USER, user);

        this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //The form is checked by the AccountForm object
        AccountForm accountForm = new AccountForm();
        User user = accountForm.accountValidation(userDAO, req);

        //The JSON file is formed and printed
        String json = "{\"error\": \"" + accountForm.getErrors().get("picture") + "\", \"picture\" : \"" + user.getPicture() + "\"}";
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(json);
    }

}
