package com.quotemedia.interview.quoteservice.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * The type Date utils.
 */
public class DateUtils {

    /**
     * Parse string date to local date.
     *
     * @param dayStr the day str
     * @return the optional
     */
    public static Optional<LocalDate> parseLocalDate(String dayStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDate day = LocalDate.parse(dayStr, formatter);
            return Optional.of(day);
        }catch ( DateTimeParseException e)  {
            Logger.getLogger(DateUtils.class.getName()).warning(e.getLocalizedMessage());
        }
        return Optional.empty();
    }
}
