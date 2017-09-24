package net.osplay.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.MeiZiBean;
import net.osplay.ui.adapter.PostsAllAdapter;
import net.osplay.ui.fragment.base.BaseFragment;

import java.util.List;

/**
 * 热帖 → 全部
 */

public class DetailsPostsAllFragment extends BaseFragment {
    private static final String TAG = "DetailsPostsAllFragment";
    private RecyclerView mRvPostsAll;
    private Gson gson = new Gson();

    @SuppressLint("ValidFragment")
    public DetailsPostsAllFragment() {
    }

    @SuppressLint("ValidFragment")
    public DetailsPostsAllFragment(Activity activity, int resId) {
        super(activity, resId);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.layout_recycler_view, null);
        mRvPostsAll = (RecyclerView) inflate.findViewById(R.id.rv_layout_public);
        return inflate;
    }

    @Override
    public void initData() {
        super.initData();
        RequestQueue requestQueue = NoHttp.newRequestQueue();

        //创建一个字符串类型请求，自定义请求方法。
        Request<String> requestAddWord = NoHttp.createStringRequest(I.MSG_FULI, RequestMethod.GET);

        //获取数据请求并解析
        getDetailsPostsAllData(requestQueue, requestAddWord);
    }

    private void getDetailsPostsAllData(RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: getDetailsPostsAllData====================" + json);

                //数据解析（集合）
                MeiZiBean bean = gson.fromJson(json, MeiZiBean.class);
                List<MeiZiBean.ResultsBean> postsAllList = bean.getResults();
                Log.d(TAG, "onSucceed: getDetailsPostsAllData====================" + postsAllList);

                initRecyclerView(postsAllList);
            }

            @Override
            public void onFailed(int what, Response<String> response) {
            }

            @Override
            public void onFinish(int what) {
            }
        });
    }

    private void initRecyclerView(List<MeiZiBean.ResultsBean> postsAllList) {
        if (postsAllList != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mRvPostsAll.setLayoutManager(layoutManager);
            mRvPostsAll.setHasFixedSize(true);

            PostsAllAdapter adapter = new PostsAllAdapter(getActivity(), postsAllList);
            mRvPostsAll.setAdapter(adapter);
        }
    }

}
