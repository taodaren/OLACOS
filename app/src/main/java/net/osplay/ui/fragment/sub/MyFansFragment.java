package net.osplay.ui.fragment.sub;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
import net.osplay.service.entity.MyFansBean;
import net.osplay.ui.adapter.MyFansAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 个人中心--我的粉丝(ok)
 */
public class MyFansFragment extends Fragment {
    @BindView(R.id.center_recycler)
    RecyclerView centerRecycler;
    @BindView(R.id.center_not_data_iv)
    ImageView centerNotDataIv;
    Unbinder unbinder;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    @BindView(R.id.center_not_data_tv)
    TextView centerNotDataTv;
    private View inflate;
    private Gson mGson = new Gson();
    private List<MyFansBean.RowsBean> rows;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.center_layout, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        centerRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        initData();
        return inflate;
    }

    private void initData() {
        String id = AppHelper.getInstance().getUser().getID();
        Log.e("JGB", "个人id" + id);
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.MY_FANS_PAGER, RequestMethod.POST);
        request.add("page", "1");
        request.add("rows", Integer.MAX_VALUE);
        request.add("memberId", AppHelper.getInstance().getUser().getID());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                avi.show();
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "我的粉丝请求结果" + json);
                if (json == null) {
                    return;
                } else {
                    avi.hide();
                    formatMyfans(json);

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

    private void formatMyfans(String json) {
        MyFansBean myFansBean = mGson.fromJson(json, MyFansBean.class);
        Log.e("JGB", "粉丝数量：" + myFansBean.getTotal());
        if (myFansBean.getTotal() == 0) {
            centerRecycler.setVisibility(View.GONE);
            centerNotDataIv.setVisibility(View.VISIBLE);
            centerNotDataTv.setVisibility(View.VISIBLE);
            centerNotDataTv.setText("你还没有粉丝，快去提高个人魅力吧！");
        } else {
            rows = myFansBean.getRows();
            centerRecycler.setAdapter(new MyFansAdapter(getActivity(), rows));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
