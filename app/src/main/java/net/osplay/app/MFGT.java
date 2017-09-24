package net.osplay.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;

import net.osplay.ui.activity.sub.LoginActivity;

/**
 * 跳转类
 */

public class MFGT {

    /**
     * 判断用户是否已经登录
     */
    public static void isLogin(Activity activity, Class<?> clz) {
        //查看本地是否有用户的登录信息
        SharedPreferences sp = activity.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");

        if (TextUtils.isEmpty(name)) {
            //本地没有保存过用户信息
            activity.startActivity(new Intent(activity, LoginActivity.class));
        } else {
            //已经登录过，则直接加载用户的信息并显示
            activity.startActivity(new Intent(activity, clz));
        }
    }

}
