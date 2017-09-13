package net.osplay.ui.fragment.sub;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.olacos.R;
import net.osplay.service.entity.goods.ResultBeanData;
import net.osplay.ui.adapter.sub.MallAdapter;
import net.osplay.ui.fragment.base.BaseBussFragment;
import net.osplay.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsMallFragment extends BaseBussFragment {
    private RecyclerView rv_mall;
    private Gson mGson = new Gson();
    private ResultBeanData.ResultBean resultBean;
    private MallAdapter adapter;

    @SuppressLint("ValidFragment")
    public GoodsMallFragment() {
    }

    @SuppressLint("ValidFragment")
    public GoodsMallFragment(Context mContext, int resId) {
        super(mContext, resId);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        rv_mall = (RecyclerView) view.findViewById(R.id.rv_mall);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void initData() {
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


}
