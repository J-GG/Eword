package com.eword.servlets;

import com.eword.beans.User;
import com.eword.business.UserBusiness;
import com.eword.lang.Lang;
import com.eword.lang.Lang.Language;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/lang/*")
public class LanguageSelection extends HttpServlet {

    /**
     * Name of the request attribute containing the translations
     */
    private final static String ATT_LANG = "lang";

    /**
     * Name of the session attribute containing the id of the user
     */
    private static final String ATT_USER_ID = "user_id";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Creation of the path for the redirection. It is either the previous page or the home page
        String address = req.getHeader("referer");
        String context = req.getContextPath();
        if (address == null) {
            address = context;
        }

        //Verification that the URL is correct. if not, the user is redirected
        Matcher matcher = Pattern.compile(context + "/lang/([a-z]{2})$").matcher(req.getRequestURI());

        if (!matcher.find()) {
            resp.sendRedirect(address);
            return;
        }

        //The language code is extracted from the URL and checked if it really exists
        String langCode = matcher.group(1);
        Language lang = Language.getLanguageFromCode(langCode);

        //If the extracted language doesn't belong to the list, the user is redirected
        if (lang == null) {
            resp.sendRedirect(address);
            return;
        }

        //The translations of the selected language are stored in a session attribute
        HttpSession session = req.getSession();
        session.setAttribute(ATT_LANG, Lang.getInstance().getTranslations(lang));

        //If the user is logged in, we update their language
        Integer userId = (Integer) session.getAttribute(ATT_USER_ID);
        if (userId != null) {
            UserBusiness userBusiness = new UserBusiness();
            User user = userBusiness.getUserFromId(userId);
            user.setLanguage(lang);
            userBusiness.updateUser(user);
        }

        //The user is redirected
        resp.sendRedirect(address);
    }
}
