package net.osplay.service.presenter;

import android.content.Context;
import android.content.Intent;

import net.osplay.service.entity.TestSecondBean;
import net.osplay.service.manager.DataManager;
import net.osplay.service.view.TextSecondView;
import net.osplay.service.view.View;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 测试的Presenter
 */

public class TestSecondPresenter implements Presenter {
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private TextSecondView mTextSecondView;
    private List<TestSecondBean> mTestSecondList;

    public TestSecondPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        mTextSecondView = (TextSecondView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }

    public void getTestSecond(int catId, int pageId, int pageSize) {
        mCompositeSubscription.add(manager.getTextSecond(catId, pageId, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TestSecondBean>>() {
                               @Override
                               public void onCompleted() {
                                   if (mTestSecondList != null) {
                                       mTextSecondView.onSuccess(mTestSecondList);
                                   }
                               }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                                   mTextSecondView.onError("请求失败");
                               }

                               @Override
                               public void onNext(List<TestSecondBean> testBeen) {
                                   mTestSecondList = testBeen;
                               }
                           }
                ));
    }

}
