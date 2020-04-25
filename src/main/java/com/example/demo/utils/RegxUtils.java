package com.example.demo.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Agus
 * @Description:
 * @Date: Created in 2020/01/21 16:57
 */
public class RegxUtils {
    public RegxUtils() {
    }

    public static final String replace(String regx, String str, boolean caseInsensitive) {
        Pattern pattern = null;
        if (caseInsensitive) {
            pattern = Pattern.compile(regx, 2);
        } else {
            pattern = Pattern.compile(regx);
        }

        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll("");
    }

    public static final boolean isEmail(String email) {
        Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        if (StringUtils.isBlank(email)) {
            return false;
        } else {
            email = email.toLowerCase();
            if (email.endsWith(".con")) {
                return false;
            } else if (email.endsWith(".cm")) {
                return false;
            } else if (email.endsWith("@gmial.com")) {
                return false;
            } else if (email.endsWith("@gamil.com")) {
                return false;
            } else {
                return email.endsWith("@gmai.com") ? false : emailer.matcher(email).matches();
            }
        }
    }

    public static final String replace(String regx, String str, String newStr, boolean caseInsensitive) {
        if (StringUtils.isBlank(newStr)) {
            newStr = "";
        }

        Pattern pattern = null;
        if (caseInsensitive) {
            pattern = Pattern.compile(regx, 2);
        } else {
            pattern = Pattern.compile(regx);
        }

        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll(newStr);
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }

        return dest;
    }

    public static boolean isNumber(String number) {
        Pattern p = Pattern.compile("^-?(?:[0-9]+|[0-9]{1,3}(?:,[0-9]{3})+)?(?:\\.[0-9]+)?$");
        Matcher matcher = p.matcher(number);
        return matcher.matches();
    }

    public static boolean isDouble(String number) {
        Pattern p = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$");
        Matcher matcher = p.matcher(number);
        return matcher.matches();
    }

    public static boolean isMobile(String mobile) {
        Pattern p = Pattern.compile("^(1[3-9])\\d{9}|((5[1-69]|9[0-8])\\d{6}|6\\d{7})$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    public static boolean isTelephone(String telephone) {
        Pattern p = Pattern.compile("(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,8})+(\\-[0-9]{1,4})?)|(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,8})+(\\-[0-9]{1,4})?)");
        Matcher m = p.matcher(telephone);
        return m.matches();
    }

    public static boolean isLoginName(String loginName) {
        Pattern p = Pattern.compile("^(?![0-9]+$)[0-9A-Za-z]{4,32}$");
        Matcher m = p.matcher(loginName);
        return m.matches();
    }

    public static boolean isChinaMobile(String mobile) {
        Pattern p = Pattern.compile("^(\\+86)?(1[3-9])\\d{9}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /** @deprecated */
    @Deprecated
    public static boolean isIDCard(String idCard) {
        Pattern p = Pattern.compile("^(\\d{6})(18|19|20)?(\\d{2})([01]\\d)([0123]\\d)(\\d{3})(\\d|X)?$");
        Matcher m = p.matcher(idCard);
        return m.matches();
    }

    public static boolean testMatch(String regStr, String testStr) {
        Pattern p = Pattern.compile(regStr);
        Matcher m = p.matcher(testStr);
        return m.matches();
    }

    public static void main(String[] args) {
        System.out.println(isTelephone("0206545214"));
    }
}
