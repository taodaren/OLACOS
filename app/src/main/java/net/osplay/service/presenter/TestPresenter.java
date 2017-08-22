package net.osplay.service.presenter;

import android.content.Context;
import android.content.Intent;

import net.osplay.service.entity.TestBean;
import net.osplay.service.manager.DataManager;
import net.osplay.service.view.TextDataView;
import net.osplay.service.view.View;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 测试的Presenter
 */

public class TestPresenter implements Presenter {
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private TextDataView mTextDataView;
    private List<TestBean> mTestList;

    public TestPresenter(Context mContext) {
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
        mTextDataView = (TextDataView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }

    public void getTextData(int catId, int pageId, int pageSize) {
        mCompositeSubscription.add(manager.getTestData(catId, pageId, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TestBean>>() {
                    @Override
                    public void onCompleted() {
                        if (mTestList != null) {
                            mTextDataView.onSuccess(mTestList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mTextDataView.onError("请求失败");
                    }

                    @Override
                    public void onNext(List<TestBean> testBean) {
                        mTestList = testBean;
                    }
                })
        );
    }
}
