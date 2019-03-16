package com.book.store.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class BlogUtil {
    public static String ageOf(LocalDateTime shareDate) {
        LocalDateTime currentDate = LocalDateTime.now();
        long diffSeconds = ChronoUnit.SECONDS.between(shareDate, currentDate);
        long diffMinutes = ChronoUnit.MINUTES.between(shareDate, currentDate);
        long diffHours = ChronoUnit.HOURS.between(shareDate, currentDate);
        long diffDays = ChronoUnit.DAYS.between(shareDate, currentDate);
        long diffMonths = ChronoUnit.MONTHS.between(shareDate, currentDate);
        long diffYears = ChronoUnit.YEARS.between(shareDate, currentDate);

        if (diffYears != 0) {
            return diffYears + " yr";
        } else if (diffMonths != 0) {
            return diffMonths + " mon";
        } else if (diffDays != 0) {
            return diffDays + " day";
        } else if (diffHours != 0) {
            return diffHours + " hr";
        } else if (diffMinutes != 0) {
            return diffMinutes + " min";
        } else {
            return diffSeconds + " sec";
        }

    }
}
