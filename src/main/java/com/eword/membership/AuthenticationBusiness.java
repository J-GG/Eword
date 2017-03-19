package com.eword.membership;

import com.eword.general.StringUtils;
import com.eword.lang.Lang;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationBusiness {

    /**
     * Name of the session attribute containing the translations
     */
    private static final String ATT_LANG = "lang";

    /**
     * Name of the session attribute containing the id of the user
     */
    private static final String ATT_USER_ID = "user_id";

    /**
     * Name of the session attribute storing the user's username
     */
    private static final String ATT_USER_USERNAME = "user_username";

    /**
     * Name of the cookie storing the id
     */
    private static final String COOKIE_ID_NAME = "id";

    /**
     * Max age of the cookie set to 2 weeks
     */
    private static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 14;

    /**
     * Name of the cookie storing the token
     */
    private static final String COOKIE_TOKEN_NAME = "token";

    /**
     * Update the session and the cookies according to the user's information
     *
     * @param user The authenticated User
     * @param rememberMe True to enable a long-term persistent authentication
     * @param userDAO Object enabling to communicate with the User data layer
     * @param req The request
     * @param resp The response
     */
    public static void authenticatedUser(User user, boolean rememberMe, UserDAO userDAO, HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();

        //Information about the user are set
        session.setAttribute(ATT_LANG, Lang.getInstance().getTranslations(user.getLanguage()));
        session.setAttribute(ATT_USER_ID, user.getId());
        session.setAttribute(ATT_USER_USERNAME, user.getUsername());

        if (rememberMe) {
            //The user id is hashed and stored in a cookie
            String hashedId = StringUtils.sha256(String.valueOf(user.getId()));
            Cookie cookieId = new Cookie(COOKIE_ID_NAME, hashedId);
            cookieId.setPath(req.getContextPath());
            resp.addCookie(cookieId);

            //A random token is generated
            String token = StringUtils.randomString(32);
            Cookie cookieToken = new Cookie(COOKIE_TOKEN_NAME, token);
            cookieToken.setMaxAge(COOKIE_MAX_AGE);
            cookieToken.setPath(req.getContextPath());
            resp.addCookie(cookieToken);

            //The user's token is updated
            user.setToken(StringUtils.sha256(token));
            userDAO.update(user);
        }
    }

    /**
     * Check whether the id and the token match a user or not
     *
     * @param hashedId The user's hashed id
     * @param token The user's token
     * @param userDAO Object enabling to communicate with the User data layer
     * @return A User if the parameters match a user or null if not
     */
    public static User checkRememberMe(String hashedId, String token, UserDAO userDAO) {

        User user = null;
        String hashedToken = StringUtils.sha256(token);

        //We search for a user from the token
        User userToken = userDAO.findIdFromToken(hashedToken);

        //If both ids match, the User is returned
        if (userToken != null && hashedId.equals(StringUtils.sha256(String.valueOf(userToken.getId())))) {
            user = userToken;
        }

        return user;
    }
}
