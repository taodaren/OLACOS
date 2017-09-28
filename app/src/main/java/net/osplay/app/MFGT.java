package net.osplay.app;

import android.app.Activity;
import android.content.Intent;

import net.osplay.ui.activity.sub.LoginActivity;

/**
 * 跳转类
 */

public class MFGT {

    /**
     * 判断用户是否已经登录，未登录跳转登录，登录跳转到目标类
     *
     * @param activity 上下文
     * @param clz      目标类
     * @param strLogin 携带值
     */
    public static void gotoLogin(Activity activity, Class<?> clz, String strLogin) {
        if (AppHelper.getInstance().isLogined()) {// 已登录状态
            activity.startActivity(new Intent(activity, clz));
        } else { // 未登录时跳转登录界面
            activity.startActivity(new Intent(activity, LoginActivity.class).putExtra("loginId", strLogin));
        }
    }

    /**
     * 携带跳转到登录界面
     *
     * @param activity 上下文
     * @param strLogin 携带值
     */
    public static void gotoLogin(Activity activity, String strLogin) {
        activity.startActivity(new Intent(activity, LoginActivity.class).putExtra("loginId", strLogin));
    }

}
