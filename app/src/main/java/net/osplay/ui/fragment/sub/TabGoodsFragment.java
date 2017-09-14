package net.osplay.ui.fragment.sub;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.olacos.R;

import net.osplay.service.entity.goods.ResultBeanData;
import net.osplay.service.entity.goods.TypeListBean;
import net.osplay.ui.adapter.sub.MallAdapter;
import net.osplay.ui.adapter.sub.goods.SecondHandAdapter;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.utils.Constants;

import java.util.List;


/**
 * 商品模块
 */

public class TabGoodsFragment extends BaseFragment {

    //------公共属性--------------
    private FrameLayout mFlCardBack;
    private FrameLayout mFlContainer;
    private FrameLayout mFlCardFront;
    private AnimatorSet mRightOutSet; // 右出动画
    private AnimatorSet mLeftInSet; // 左入动画
    private Button btn;
    private boolean mIsShowBack;
    //------商城属性---------------
    private RecyclerView rv_mall;
    private Gson mGson = new Gson();
    private ResultBeanData.ResultBean resultBean;
    private MallAdapter adapter;

    //------二手属性----------------
    private RecyclerView mRecyclerView;
    private SecondHandAdapter sAdapter;
    private List<TypeListBean.ResultBean.PageDataBean> page_data;

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_tab_goods, null);
        overAll(inflate);
        initMall(inflate);
        initHandMall(inflate);
        return inflate;
    }

    private void initHandMall(View inflate) {
        mRecyclerView = (RecyclerView) inflate.findViewById(R.id.lalagoods_recy);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        getJsonData();
    }
    private void getJsonData() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        final Request<String> request = NoHttp.createStringRequest(Constants.COSPLAY_STORE, RequestMethod.GET);//服饰数据
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.e("AAA", json);
                TypeListBean secondHandMallBean = mGson.fromJson(json, TypeListBean.class);
                page_data = secondHandMallBean.getResult().getPage_data();
                sAdapter = new SecondHandAdapter(getActivity(), page_data);
                mRecyclerView.setAdapter(sAdapter);
            }

            @Override
            public void onFailed(int what, Response<String> response) {
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }



    private void initMall(View inflate) {
        rv_mall = (RecyclerView) inflate.findViewById(R.id.rv_mall);
        getDataFromNet();
    }
    private void getDataFromNet() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        final Request<String> request = NoHttp.createStringRequest(Constants.HOME_URL, RequestMethod.GET);//服饰数据
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.e("TAG",json);
                processedData(json);//解析数据

            }

            @Override
            public void onFailed(int what, Response<String> response) {
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    private void processedData(String json) {
        ResultBeanData resultBeanData = mGson.fromJson(json, ResultBeanData.class);
        resultBean = resultBeanData.getResult();
        if (resultBean != null) {//有数据
            adapter = new MallAdapter(getActivity(), resultBean);
            rv_mall.setAdapter(adapter);
            rv_mall.setLayoutManager(new GridLayoutManager(getActivity(),1));
        } else {

        }
    }


    private void overAll(View inflate) {
        mFlCardBack = (FrameLayout)inflate.findViewById(R.id.main_fl_card_back);
        mFlCardFront = (FrameLayout)inflate.findViewById(R.id.main_fl_card_front);
        mFlContainer = (FrameLayout) inflate.findViewById(R.id.main_fl_container);
        btn= (Button) inflate.findViewById(R.id.onclickbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 正面朝上
                if (!mIsShowBack) {
                    mRightOutSet.setTarget(mFlCardFront);
                    mLeftInSet.setTarget(mFlCardBack);
                    mRightOutSet.start();
                    mLeftInSet.start();
                    mIsShowBack = true;
                } else { // 背面朝上
                    mRightOutSet.setTarget(mFlCardBack);
                    mLeftInSet.setTarget(mFlCardFront);
                    mRightOutSet.start();
                    mLeftInSet.start();
                    mIsShowBack = false;
                }
            }
        });
        setAnimators();
        setCameraDistance(); // 设置镜头距离
    }
    private void setAnimators() {
        mRightOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.anim_out);
        mLeftInSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.anim_in);

        // 设置点击事件
        mRightOutSet.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                btn.setClickable(false);
            }
        });
        mLeftInSet.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                btn.setClickable(true);
            }
        });
    }
    // 改变视角距离, 贴近屏幕
    private void setCameraDistance() {
        int distance = 16000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mFlCardFront.setCameraDistance(scale);
        mFlCardBack.setCameraDistance(scale);
    }


}
