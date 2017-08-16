package net.osplay.olacos.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号验证正则工具包
 */

public class Verification {
    //验证手机号是否为正确手机号
    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern
                .compile("^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

}
