package net.osplay.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具类
 */
public class VerificationUtil {
    // 判断内容是否由字母，数字，下划线组成
    public static boolean isValidContent(String content) {
        if (!TextUtils.isEmpty(content)) {
            String regex = "^[\\w\\u4e00-\\u9fa5]+$";
            return Pattern.matches(regex, content);
        }
        return false;
    }
    //验证手机号是否为正确手机号
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    public static  boolean isRightPwd(String pwd) {
        Pattern p = Pattern.compile("^(?![^a-zA-Z]+$)(?!\\D+$)[0-9a-zA-Z]{8,16}$");
        Matcher m = p.matcher(pwd);
        return m.matches();
    }

    //验证身份证是否合法
    public static boolean checkIdCard(String idCard) {
        String regex = "[1-9]\\d{16}[a-zA-Z0-9]{1}";
        return Pattern.matches(regex,idCard);
    }

}

