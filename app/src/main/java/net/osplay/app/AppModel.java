package net.osplay.app;

import android.content.Context;

import net.osplay.utils.Constants;
import net.osplay.utils.SharedPfUtil;

/**
 * App 数据读取
 */

public class AppModel {
    private Context mContext;

    public AppModel(Context context) {
        mContext = context;
    }

    public String getCurrentUserName() {
        return SharedPfUtil.getParam(mContext, I.Account.USER_NAME, "String");
    }

    public void setCurrentUserName(String userName) {
        SharedPfUtil.setParam(mContext, I.Account.USER_NAME, userName);
    }

    public String getCurrentToken() {
        return SharedPfUtil.getParam(mContext, I.Account.TOKEN, "String");
    }

    public void setCurrentToken(String token) {
        SharedPfUtil.setParam(mContext, I.Account.TOKEN, token);
    }

    public String getCurrentPW() {
        return SharedPfUtil.getParam(mContext, I.Account.PASS_WORD, "String");
    }

    public void setCurrentPW(String pwd) {
        SharedPfUtil.setParam(mContext, I.Account.PASS_WORD, pwd);
    }

}
