package com.ymwang.park.utils;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateFormatUtils;
/**
 * @Author: wym
 * @Date: 2018/4/26
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    private static String[] parsePatterns = new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public DateUtils() {
    }

    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }

        return formatDate;
    }

    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }
    public static String formatYearMonth(Date date) {
        return formatDate(date, "yyyy-MM");
    }

    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        } else {
            try {
                return parseDate(str.toString(), parsePatterns);
            } catch (ParseException var2) {
                return null;
            }
        }
    }

    public static long pastDays(Date date) {
        long t = (new Date()).getTime() - date.getTime();
        return t / 86400000L;
    }

    public static long pastHour(Date date) {
        long t = (new Date()).getTime() - date.getTime();
        return t / 3600000L;
    }

    public static long pastMinutes(Date date) {
        long t = (new Date()).getTime() - date.getTime();
        return t / 60000L;
    }

    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / 86400000L;
        long hour = timeMillis / 3600000L - day * 24L;
        long min = timeMillis / 60000L - day * 24L * 60L - hour * 60L;
        long s = timeMillis / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
        long sss = timeMillis - day * 24L * 60L * 60L * 1000L - hour * 60L * 60L * 1000L - min * 60L * 1000L - s * 1000L;
        return (day > 0L ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    public static long getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / 3600000L;
    }

    public static boolean validate(String dateString, String pattern) {
        try {
            parseDateStrictly(dateString, new String[]{pattern});
            return true;
        } catch (ParseException var3) {
            return false;
        }
    }

    /** @deprecated */
    @Deprecated
    public static String standardize(String dateString) {
        String[] arr$ = parsePatterns;
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String pattern = arr$[i$];
            if (validate(dateString, pattern)) {
                try {
                    return formatDateTime(parseDateStrictly(dateString, new String[]{pattern}));
                } catch (ParseException var6) {
                    return null;
                }
            }
        }

        return null;
    }

    public static List<String> getBetweenToNow(Date date) {
        List<String> betweenList = new ArrayList();
        Date now = new Date();
        int between_days = (int)((parseDate(formatDateTime(now)).getTime() - parseDate(formatDateTime(date)).getTime()) / 86400000L);

        for(int i = 1; i <= between_days; ++i) {
            betweenList.add(formatDate(getDateBefore(now, i)));
        }

        return betweenList;
    }

    public static Date getDateBefore(Date date, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(5, now.get(5) - day);
        return now.getTime();
    }

    public static int getDays(Date from, Date to) {
        long sl = from.getTime();
        long el = to.getTime();
        long ei = el - sl;
        return (int)(ei / 86400000L) + 1;
    }

    public static Calendar getCalendar(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month - 1, day);
        return calendar;
    }

    public static Calendar getCalendar(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        }
    }

    public static String getChsFormatDate(Calendar calendar) {
        return formatDate(calendar.getTime(), "M月d日");
    }

    public static String formatDateRange(Calendar from, Calendar to) {
        return formatDate(from.getTime(), "MM.dd") + "~" + formatDate(to.getTime(), "MM.dd");
    }
}

