package net.osplay.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;

public class MyApplication extends Application {
    private static Context instance;

    public static Context context;
    public static Handler handler;
    public static Thread mainThread;
    public static int mainThreadID;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppHelper.getInstance().init(instance);
        NoHttp.initialize(this);
        Logger.setDebug(true);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。上线后改为false   不然影响性能
        Logger.setTag("NoHttpSample");// 设置NoHttp打印Log的tag。
        context=this.getApplicationContext();
        handler=new Handler();
        mainThread=Thread.currentThread();
        mainThreadID= android.os.Process.myTid();
        Logger.setDebug(true);//开启 NoHttp 的调试模式, 配置后可看到请求过程、日志和错误信息。上线后改为 false 不然影响性能
        Logger.setTag("NoHttpSample");//设置 NoHttp 打印 Log 的 tag
    }

    public static Context getContext() {
        return instance;
    }

}
