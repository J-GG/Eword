package com.eword.lang.business;

import com.eword.lang.business.Lang.Language;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class enables to convert a standard Java Date into a specific format
 */
public class LocalDate {

    /**
     * The French format for dates
     */
    private static String DATE_FORMAT_FRENCH = "dd/MM/yyyy";

    /**
     * The English format for dates
     */
    private static String DATE_FORMAT_ENGLISH = "MM/dd/yyyy";

    /**
     * Convert a Date into a specific format depending on the language
     *
     * @param date The date to convert
     * @param language The language in which the date should be converted
     * @return A String representing the date converted into a specific format
     * depending on the language
     */
    public static String getLocalDate(Date date, Language language) {
        SimpleDateFormat formater = null;

        if (language == Lang.Language.FRENCH) {
            formater = new SimpleDateFormat(DATE_FORMAT_FRENCH);
        } else {
            formater = new SimpleDateFormat(DATE_FORMAT_ENGLISH);
        }

        return formater.format(date);
    }

    /**
     * Convert a date into a specific format depending on the language into a
     * standard Date
     *
     * @param date the date to convert
     * @param language the language in which the date is
     * @return A Date representing the date converted into a standard Date
     * @throws ParseException
     */
    public static Date getStandardDate(String date, Language language) throws ParseException {
        SimpleDateFormat formater = null;

        if (language == Lang.Language.FRENCH) {
            formater = new SimpleDateFormat(DATE_FORMAT_FRENCH);
        } else {
            formater = new SimpleDateFormat(DATE_FORMAT_ENGLISH);
        }

        return formater.parse(date);
    }
}
