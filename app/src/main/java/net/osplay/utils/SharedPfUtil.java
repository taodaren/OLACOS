package net.osplay.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Shared preference 工具类
 */

public class SharedPfUtil {
    private static final String FILE_NAME = "user_info";

    public static void setParam(Context context, String key, Object obj) {
        String type = obj.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        switch (type) {
            case "String":
                editor.putString(key, (String) obj);
                break;
            case "Integer":
                editor.putInt(key, (Integer) obj);
                break;
            case "Boolean":
                editor.putBoolean(key, (Boolean) obj);
                break;
            case "Float":
                editor.putFloat(key, (Float) obj);
                break;
            case "Long":
                editor.putLong(key, (Long) obj);
                break;
        }

        editor.apply();
    }

    public static <T> T getParam(Context context, String key, T defaultObject) {
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if ("String".equals(type)) {
            return (T) sp.getString(key, null);
        } else if ("Integer".equals(type)) {
            return (T) (Integer) sp.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return (T) (Boolean) sp.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return (T) (Float) sp.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return (T) (Long) sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }
}
