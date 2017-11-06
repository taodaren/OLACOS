package net.osplay.ui.fragment.sub;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.MyAreaBean;
import net.osplay.ui.adapter.MyAreaAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 个人中心------我的关注列表（ok）
 */
public class MyareaFragment extends Fragment {
    @BindView(R.id.center_recycler)
    RecyclerView centerRecycler;
    @BindView(R.id.center_not_data_iv)
    ImageView centerNotDataIv;
    Unbinder unbinder;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    private View inflate;
    private Gson mGson = new Gson();
    private  List<MyAreaBean.RowsBean> rows;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.center_layout, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        centerRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        initData();
        return inflate;
    }


    private void initData() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.MY_AREA, RequestMethod.POST);
        request.add("page", "1");
        request.add("rows", "20");
        request.add("memberId", AppHelper.getInstance().getUser().getID());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                avi.show();
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                avi.hide();
                String json = response.get();
                Log.e("JJJ", "个人中心专区列表：" + json);
                if (json == null) {
                    return;
                } else {
                    avi.hide();
                    formatMyarea(json);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    private void formatMyarea(String json) {
        MyAreaBean myAreaBean = mGson.fromJson(json, MyAreaBean.class);
        if (myAreaBean.getTotal() == 0) {
            centerRecycler.setVisibility(View.GONE);
            centerNotDataIv.setVisibility(View.VISIBLE);
        } else {
            rows = myAreaBean.getRows();
            centerRecycler.setAdapter(new MyAreaAdapter(getActivity(), rows));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        initData();
        super.onStart();
    }
}
