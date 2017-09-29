package net.osplay.utils;

/**
 * 获取省、市、国家
 */

public class GetCity {

    /**
     * Ascii转换为字符串
     */
    public static String asciiToString(String value) {
        StringBuffer buffer = new StringBuffer();
        String[] chars = value.split(",");
        for (String aChar : chars) {
            buffer.append((char) Integer.parseInt(aChar));
        }
        return buffer.toString();
    }

}
