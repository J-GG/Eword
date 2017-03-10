package com.eword.lang;

import com.eword.general.RequestUtils;
import com.eword.lang.Lang.Language;
import com.eword.membership.User;
import com.eword.membership.UserBusiness;
import com.eword.membership.UserDAO;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/lang/*")
public class LanguageSelection extends HttpServlet {

    /**
     * Name of the request attribute containing the translations for the display
     * language chosen by the user
     */
    private final static String ATT_LANG = "lang";

    /**
     * Name of the session attribute containing the id of the user
     */
    private static final String ATT_USER_ID = "user_id";

    /**
     * Object enabling to communicate with the User data layer
     */
    @EJB
    private UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //We get the adress to which the user will be redirected
        String address = RequestUtils.getPreviousPage(req);

        //Verification that the URL is correct. if not, the user is redirected
        Matcher matcher = Pattern.compile(req.getContextPath() + "/lang/([a-z]{2})$").matcher(req.getRequestURI());

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
            User user = UserBusiness.getUserFromId(userId, userDAO);
            user.setLanguage(lang);
            UserBusiness.updateUser(user, userDAO);
        }

        //The user is redirected
        resp.sendRedirect(address);
    }
}
