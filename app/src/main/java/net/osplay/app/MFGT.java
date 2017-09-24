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
    public static void isLogin(Activity activity, Class<?> clz, String strLogin) {

        //查看本地是否有用户的登录信息
        SharedPreferences sp = activity.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");

        if (TextUtils.isEmpty(name)) {//本地没有保存过用户信息
            activity.startActivity(new Intent(activity, LoginActivity.class).putExtra("loginId",strLogin));
        } else {
            activity.startActivity(new Intent(activity, clz));
        }
    }

}
