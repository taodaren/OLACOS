package net.osplay.app;

import android.app.Application;
import android.content.Context;

import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;

public class MyApplication extends Application {
    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        NoHttp.initialize(this);
        Logger.setDebug(true);//开启 NoHttp 的调试模式, 配置后可看到请求过程、日志和错误信息。上线后改为 false 不然影响性能
        Logger.setTag("NoHttpSample");//设置 NoHttp 打印 Log 的 tag
    }

    public static Context getContext() {
        return instance;
    }

}
