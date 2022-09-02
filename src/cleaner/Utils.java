package cleaner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Utils {

    public static final String COMMA = ",";
    public static String DATE_PATTERN = "YYYY/MM/DD hh:mm";
    public static String DATE_PATTERN_SEC = "YYYY-MM-DD hh:mm:ss";
    private static Pattern BANKTO_PATTERN = Pattern.compile("[A-Z]{2}");

    public static boolean validateDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
        try {
            Date d = format.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static boolean validateDateSec(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN_SEC);
        try {
            Date d = format.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static Integer validatePositiveInt(String field) {
        try {
            int val = Integer.parseInt(field);
            if (val < 0)
                return null;
            else
                return val;
        } catch (Exception e) {
            return null;
        }
    }

    public static Float validatePercentage(String field) {
        try {
            float val = Float.parseFloat(field);
            if (!(0.0f <= val && val <= 100000.0))
                return null;
            else
                return val;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean validateBankTo(String field) {
        return  BANKTO_PATTERN.matcher(field).matches() || "".equals(field) || "\" \"".equals(field);
    }
}