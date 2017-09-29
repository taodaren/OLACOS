package net.osplay.app;

import android.content.Context;

import net.osplay.data.bean.Account;
import net.osplay.data.db.GreenDaoHelper;
import net.osplay.utils.SharedPfUtil;

import java.util.List;

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

    public void setLoginState(boolean state) {
        SharedPfUtil.setParam(mContext, I.Account.IS_LOGIN, state);
    }

    public boolean getLoginState() {
        return SharedPfUtil.getParam(mContext, I.Account.IS_LOGIN, false);
    }

    public Account getCurrentUser(String phone) {
        List<Account> accountList = GreenDaoHelper.getDaoSession().getAccountDao().loadAll();
        Account user = null;
        for (Account bean : accountList) {
            if (bean.getPHONE().equals(phone)) {
                user = bean;
            }
        }
        return user;
    }

    public void setCurrentUser(Account account) {
        GreenDaoHelper.getDaoSession().insertOrReplace(account);
    }


}
