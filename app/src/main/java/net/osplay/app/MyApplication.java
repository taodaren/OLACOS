package net.osplay.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by acer-PC on 2017/8/29.
 */

public class MyApplication extends Application{
    //在整个应用执行的过程中，需要提供的变量
    public static Context context;//应用中使用到的上下文
    public static Handler handler;//应用中使用到的handler对象声明
    public static Thread mainThread;//获取主线程
    public static int mainThreadId;//获取主线程的id
    @Override
    public void onCreate() {
        super.onCreate();
        context=this.getApplicationContext();
        handler=new Handler();
        mainThread=Thread.currentThread();//实例化当前Application的线程即为主线程
        mainThreadId=android.os.Process.myTid();//获取当前线程的id
    }
}
