package am.pm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class Utils {

    private static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    private static SimpleDateFormat dateInStringFormatter = new SimpleDateFormat("dd-MMM-yyyy");

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isEmpty(String[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.size() == 0;
    }

    public static Date stringToDate(String dateInString) {
        try {
            return formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateToString(Date date) {
        try {
            return dateInStringFormatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
