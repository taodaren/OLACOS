package net.osplay.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;

/**
 * Created by acer-PC on 2017/8/29.
 */

public class MyApplication extends Application{

    public static Context context;
    public static Handler handler;
    public static Thread mainThread;
    public static int mainThreadID;

    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this);
        Logger.setDebug(true);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。上线后改为false   不然影响性能
        Logger.setTag("NoHttpSample");// 设置NoHttp打印Log的tag。
        context=this.getApplicationContext();
        handler=new Handler();
        mainThread=Thread.currentThread();
        mainThreadID= android.os.Process.myTid();
    }
}
