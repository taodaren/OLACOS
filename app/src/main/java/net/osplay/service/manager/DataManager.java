package net.osplay.service.manager;

import android.content.Context;

import net.osplay.service.RetrofitHelper;
import net.osplay.service.RetrofitService;
import net.osplay.service.entity.TestBean;
import net.osplay.service.entity.TestSecondBean;

import java.util.List;

import rx.Observable;

/**
 * 该类为了更方便调用 RetrofitService 中定义的方法
 */

public class DataManager {
    private RetrofitService mRetrofitService;

    public DataManager(Context context) {
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    public Observable<List<TestBean>> getTestData(int catId, int pageId, int pageSize) {
        return mRetrofitService.getTextData(catId, pageId, pageSize);
    }

    public Observable<List<TestSecondBean>> getTextSecond(int catId, int pageId, int pageSize) {
        return mRetrofitService.getTextSecond(catId, pageId, pageSize);
    }
}
