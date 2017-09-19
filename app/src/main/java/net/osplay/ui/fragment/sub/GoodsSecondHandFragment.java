package net.osplay.ui.fragment.sub;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import net.osplay.service.entity.goods.ResultBeanData;
import net.osplay.ui.adapter.sub.goods.SecondHandAdapter;
import net.osplay.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsSecondHandFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private Gson mGson = new Gson();
    private SecondHandAdapter sAdapter;
    private ResultBeanData.ResultBean resultBean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.fragment_goods_second_hand, null);
        mRecyclerView = (RecyclerView) inflate.findViewById(R.id.goods_recy);
        getJsonData();
        return inflate;
    }

    private void getJsonData() {
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
            sAdapter = new SecondHandAdapter(getActivity(), resultBean);
            mRecyclerView.setAdapter(sAdapter);
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        } else {

        }
    }
}
