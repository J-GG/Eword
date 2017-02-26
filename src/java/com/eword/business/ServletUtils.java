package com.eword.business;

import javax.servlet.http.HttpServletRequest;

/**
 * This class contains various utilitarian functions used by servlets
 */
public class ServletUtils {

    /**
     * Return the path of the previous page or the home page if there is no
     *
     * @param req the request
     * @return the path
     */
    public static String getPreviousPage(HttpServletRequest req) {

        //We get the previous page from the "referer" attribute
        String address = req.getHeader("referer");
        if (address == null || address.contains(req.getRequestURL())) {
            //If there is no previous page or if it's the same as the current page
            address = req.getContextPath();
        }

        return address;
    }
}
