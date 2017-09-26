package net.osplay.app;

import android.content.Context;

/**
 * Created by xns on 2017/7/4.
 * App的辅助类
 */

public class AppHelper {
    private static final String TAG = "AppHelper";
    private static AppHelper instance = null;
    private AppModel mModel;
    private String userName;
    private String pwd;
    private String token;
    private boolean isLogin;

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
        return isLogin;
    }

    /**
     * 设置登录状态
     * @param state
     *
     */
    public void setLogined(boolean state) {
        this.isLogin = state;
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



}

