package net.osplay.ui.fragment.sub;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import net.osplay.utils.Constants;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsSecondHandFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private Gson mGson = new Gson();
    private SecondHandAdapter sAdapter;
    private List<TypeListBean.ResultBean.PageDataBean> page_data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.fragment_goods_second_hand, null);
        mRecyclerView = (RecyclerView) inflate.findViewById(R.id.goods_recy);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        getJsonData();
        return inflate;
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


}
