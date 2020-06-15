package kz.gamma.my.project.utils;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

public class Utils {

    private static final Logger log = Logger.getLogger(Utils.class);

    public static String toString(HttpServletRequest request) {
        try {
            return IOUtils.toString(request.getInputStream(), "utf-8");
        } catch (IOException e) {
            log.error("toString httprequest", e);
            e.printStackTrace();
        }
        return null;
    }

    public static String generatePassword() {
        StringBuilder password = new StringBuilder();

        String dict[] = {"0123456789", "abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                "[`[]{}\\<>!*,-.#$%&\"\'|/:;?^@_~]"};

        Random r = new Random();

        // use one char from each dictionary
        for (String aDict : dict) password.append(aDict.charAt(r.nextInt(aDict.length())));
        // add random char from dictionary
        for (int i = 0; i < Constants.loginLength - dict.length; i++) {
            int dictInd = r.nextInt(dict.length);
            int symbolInd = r.nextInt(dict[dictInd].length());

            password.append(dict[dictInd].charAt(symbolInd));
        }

        // mix chars in generated password
        for (int i = 0; i < password.length(); i++) {
            int indPrev = r.nextInt(password.length());
            int indCur = r.nextInt(password.length() - 1);

            char tmp = password.charAt(indPrev);
            password = new StringBuilder(password.substring(0, indPrev) + password.substring(indPrev + 1));
            password = new StringBuilder(password.substring(0, indCur) + tmp + password.substring(indCur));
        }

        return password.toString();
    }


    public static Boolean isEmpy(Object o) {
        if (o == null)
            return true;

        if (o instanceof String)
            return ((String) o).length() == 0;

        return o instanceof Collection && ((Collection) o).size() == 0;
    }

    public static String checkEmptyVal(String o) {
        return isEmpy(o) ? "" : o;
    }

    public static String localDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return localDate.format(formatter);
    }


    public static String toHexString(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException("byte array must not be null");
        }

        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(Character.forDigit((b & 0XF0) >> 4, 16));
            hex.append(Character.forDigit((b & 0X0F), 16));
        }
        return hex.toString();
    }


    public static Date minus(Date dt, int offsetTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(dt);
        date.add(Calendar.MINUTE, -offsetTime);
        return date.getTime();
    }

    public static Long parseIPAdress(String ipAddress) {
        String[] nums = ipAddress.split("\\.");
        return (long) Short.parseShort(nums[0]) * 256 * 256 * 256 + Short.parseShort(nums[1]) * 256 * 256 +
                Short.parseShort(nums[2]) * 256 + Short.parseShort(nums[3]);
    }

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
