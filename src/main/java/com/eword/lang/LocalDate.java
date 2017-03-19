package com.eword.lang;

import com.eword.lang.Lang.Language;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This class enables to convert a standard Java Date into a specific format
 */
public class LocalDate {

    /**
     * Return the date converted into a specific format depending on the
     * language
     *
     * @param date The date to convert
     * @param language The language in which the date should be converted
     * @return A String representing the date converted into a specific format
     * depending on the language
     */
    public static String getLocalDate(Date date, Language language) {
        DateFormat dateFormat;

        if (language == Lang.Language.FRENCH) {
            dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.FRENCH);
        } else {
            dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
        }

        return dateFormat.format(date);
    }
}
