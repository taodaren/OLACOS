package net.osplay.service;

import android.content.Context;


import net.osplay.app.I;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


/**
 * 主要用于 Retrofit 的初始化
 */

public class RetrofitHelper {
    private Context mContext;
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;

    OkHttpClient client = new OkHttpClient();
    //GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());

    public RetrofitHelper(Context context) {
        mContext = context;
        init();
    }

    public static RetrofitHelper getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitHelper(context);
        }
        return instance;
    }

    private void init() {
        resetApp();
    }

    /**
     * 创建 Retrofit
     */
    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(I.BASE_URL)
                .client(client)
               // .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    /**
     * 获取自身 RetrofitHelper 的实例化
     */
    public RetrofitService getServer() {
        return mRetrofit.create(RetrofitService.class);
    }

}
