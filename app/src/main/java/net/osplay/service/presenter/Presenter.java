package net.osplay.service.presenter;

import android.content.Intent;

import net.osplay.service.view.View;


/**
 * 基础 Presenter
 */

public interface Presenter {
    void onCreate();

    //暂时没用到
    void onStart();

    void onStop();

    //暂时没用到
    void pause();

    /**
     * 绑定定义的 View
     * 也就是，你想把请求下来的数据实体类给哪个 View 就传入哪个 View
     */
    void attachView(View view);

    //暂时没用到
    void attachIncomingIntent(Intent intent);
}
