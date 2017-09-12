package net.osplay.ui.fragment.sub;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.olacos.R;
import net.osplay.service.entity.goods.TypeListBean;
import net.osplay.ui.adapter.sub.goods.SecondHandAdapter;
import net.osplay.ui.fragment.base.BaseBussFragment;
import net.osplay.utils.Constants;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsSecondHandFragment extends BaseBussFragment {
    private RecyclerView mRecyclerView;
    private Gson mGson = new Gson();
    private SecondHandAdapter sAdapter;
    private List<TypeListBean.ResultBean.PageDataBean> page_data;
    private Button goods_select;
    private DrawerLayout dl_left;

    @SuppressLint("ValidFragment")
    public GoodsSecondHandFragment() {
    }

    @SuppressLint("ValidFragment")
    public GoodsSecondHandFragment(Context mContext, int resId) {
        super(mContext, resId);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.goods_recy);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void initData() {
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
                Log.e("TAG", json);
                gsonFormat(json);
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

    private void gsonFormat(String json) {

    }


}
