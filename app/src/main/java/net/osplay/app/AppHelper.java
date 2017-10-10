package net.osplay.app;

import android.content.Context;

import net.osplay.data.bean.Account;

/**
 * App 的辅助类
 */

public class AppHelper {
    private static final String TAG = "AppHelper";
    private static AppHelper instance = null;
    private AppModel mModel;
    private Account user;
    private String userName;
    private String pwd;
    private String token;
    private String userID;
    private boolean isLogin;
    private String nickIcon;
    private AppHelper() {
    }

    public synchronized static AppHelper getInstance() {
        if (instance == null) {
            instance = new AppHelper();
        }
        return instance;
    }

    public void init(Context context) {
        mModel = new AppModel(context);
    }

    public String getCurrentUserName() {
        if (userName == null) {
            userName = mModel.getCurrentUserName();
        }
        return userName;
    }

    public void setCurrentUserName(String userName) {
        this.userName = userName;
        mModel.setCurrentUserName(userName);
    }

    public String getCurrentToken() {
        if (token == null) {
            token = mModel.getCurrentToken();
        }
        return token;
    }

    public void setCurrentToken(String token) {
        this.token = token;
        mModel.setCurrentToken(token);
    }

    public boolean isLogined() {
        if (!isLogin) {
            isLogin = mModel.getLoginState();
        }
        return isLogin;
    }

    /**
     * 设置登录状态
     */
    public void setLogined(boolean state) {
        this.isLogin = state;
        mModel.setLoginState(state);
    }

    public String getCurrentPW() {
        if (pwd == null) {
            pwd = mModel.getCurrentPW();
        }
        return pwd;
    }

    public void setCurrentPW(String pwd) {
        this.pwd = pwd;
        mModel.setCurrentPW(pwd);
    }

    public String getUserID() {
        if (userID == null) {
            userID = mModel.getUserID();
        }
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
        mModel.setUserID(userID);
    }

    public Account getUser() {
        if (user == null) {
            this.user = mModel.getCurrentUser(getCurrentUserName());
        }
        return this.user;
    }


    public void setUser(Account account) {
        if (account != null) {
            this.user = account;
            setCurrentUserName(account.getPHONE());
            setCurrentPW(account.getPASSWORD());
            setUserID(account.getID());
            mModel.setCurrentUser(account);
        }
    }

}

