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

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.ui.adapter.MyPostsAdapter;
import net.osplay.ui.adapter.MyPostsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 他人主页————足迹（帖子）(ok)
 */
public class OtherPostsFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.center_recycler)
    RecyclerView centerRecycler;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    @BindView(R.id.center_not_data_tv)
    TextView centerNotDataTv;
    private View inflate;
    private Gson mGson = new Gson();
    private List<MyPostsBean.RowsBean> rows;
    private String memberId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.center_layout, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        centerRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if (bundle != null) {
            memberId = bundle.getString("otherMemberId");
        }
        initData();
        return inflate;
    }

    private void initData() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.MY_POSTS, RequestMethod.POST);
        request.add("page", "1");
        request.add("rows", Integer.MAX_VALUE);
        request.add("memberId", memberId);
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "Otherposts_____" + json);
                if (json != null) {
                    avi.hide();
                    formatOposts(json);
                } else {
                    return;
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

    private void formatOposts(String json) {
        MyPostsBean myPostsBean = mGson.fromJson(json, MyPostsBean.class);
        if (myPostsBean.getTotal() == 0) {
            centerRecycler.setVisibility(View.GONE);
            centerNotDataTv.setVisibility(View.VISIBLE);
            centerNotDataTv.setText("这只acos还没有发布过作品！");
        } else {
            rows = myPostsBean.getRows();
            centerRecycler.setAdapter(new MyPostsAdapter(getContext(), rows));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
