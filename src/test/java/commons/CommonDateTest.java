package commons;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class CommonDateTest {
    public static Date getStartOfDay(Date date) {
        return DateUtils.truncate(date, Calendar.DATE); // Đặt thời gian về 00:00:00
    }

    public static Date getEndOfDay(Date date) {
        Date endOfDay = DateUtils.ceiling(date, Calendar.DATE);
        return DateUtils.addMilliseconds(endOfDay, -1);
    }

    public static Date getStartOfMonth(Date date) {
        return DateUtils.truncate(date, Calendar.MONTH);
    }

    public static Date getEndOfMonth(Date date) {
        Date endOfDay = DateUtils.ceiling(date, Calendar.MONTH);
        return DateUtils.addMilliseconds(endOfDay, -1);
    }

    public static Date getStartOfYear(Date date) {
        return DateUtils.truncate(date, Calendar.YEAR);
    }

    public static Date getEndOfYear(Date date) {
        Date endOfDay = DateUtils.ceiling(date, Calendar.YEAR);
        return DateUtils.addMilliseconds(endOfDay, -1);
    }

    public static void main(String[] args) {
        Date now = new Date();

        System.out.println("Start of day: " + getStartOfDay(now));
        System.out.println("End of day: " + getEndOfDay(now));
        System.out.println("Start of month: " + getStartOfMonth(now));
        System.out.println("End of month: " + getEndOfMonth(now));
        System.out.println("Start of year: " + getStartOfYear(now));
        System.out.println("End of year: " + getEndOfYear(now));
    }
}
