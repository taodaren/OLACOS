package net.osplay.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;

import net.osplay.data.db.GreenDaoHelper;
import net.osplay.utils.UILKit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context instance;
    @SuppressLint("StaticFieldLeak")
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
        Logger.setDebug(true);//开启 NoHttp 的调试模式, 配置后可看到请求过程、日志和错误信息。上线后改为false 不然影响性能
        Logger.setTag("NoHttpSample");//设置 NoHttp 打印 Log 的 tag
        context = this.getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadID = android.os.Process.myTid();
        Logger.setDebug(true);//开启 NoHttp 的调试模式, 配置后可看到请求过程、日志和错误信息。上线后改为 false 不然影响性能
        Logger.setTag("NoHttpSample");//设置 NoHttp 打印 Log 的 tag
        UILKit.init(getApplicationContext());        //初始化UIL
        initDatabase();

    }

//    //运用list来保存们每一个activity是关键
//    private List<Activity> mList = new LinkedList<Activity>();
//    //为了实现每次使用该类时不创建新的对象而创建的静态对象
//    private static MyApplication instance;
//    //构造方法
//    private MyApplication(){}
//    //实例化一次
//    public synchronized static MyApplication getInstance(){
//        if (null == instance) {
//            instance = new MyApplication();
//        }
//        return instance;
//    }
//    // add Activity
//    public void addActivity(Activity activity) {
//        mList.add(activity);
//    }
//    //关闭每一个list内的activity
//    public void exit() {
//        try {
//            for (Activity activity:mList) {
//                if (activity != null)
//                    activity.finish();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            System.exit(0);
//        }
//    }
//    //杀进程
//    public void onLowMemory() {
//        super.onLowMemory();
//        System.gc();
//    }


    private void initDatabase() {
        GreenDaoHelper.initDatabase();
    }

    public static Context getContext() {
        return instance;
    }




}
