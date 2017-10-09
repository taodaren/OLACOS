package net.osplay.ui.fragment.sub;

import android.content.Context;
import android.content.SharedPreferences;
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
import net.osplay.service.entity.WordHotPostsBean;
import net.osplay.ui.adapter.WordHotPostsAdapter;
import net.osplay.ui.fragment.base.BaseFragment;

import java.util.List;

/**
 * 社区：热区 → 热帖
 */

public class WordHotPostsFragment extends BaseFragment implements WordHotPostsAdapter.HotPostingsOnClickListener {
    private static final String TAG = "WordHotPostsFragment";

    private RecyclerView mRvHotPosts;
    private Gson gson = new Gson();
    private WordHotPostsBean mWordHotPostsBean;
    private WordHotPostsBean mRefreshWordHotPostsBean;
    private List<WordHotPostsBean.PartBean> mPartList;//热帖列表所有大区的信息
    private List<WordHotPostsBean.DataBean> mDataList;//热帖列表各个大区的数据
    private List<WordHotPostsBean.PartBean> mRefreshPartList;//热帖刷新查询大区的信息
    private List<WordHotPostsBean.DataBean> mRefreshDataList;//热帖刷新查询大区的帖子信息
    private WordHotPostsAdapter adapter;

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.layout_recycler_view, null);
        mRvHotPosts = (RecyclerView) inflate.findViewById(R.id.rv_layout_public);
        return inflate;
    }

    @Override
    public void initData() {
        super.initData();
        RequestQueue requestQueue = NoHttp.newRequestQueue();

        Request<String> requestHotPosts = NoHttp.createStringRequest(I.POSTS_HOT_LIST, RequestMethod.POST);
        getHotPostsData(requestQueue, requestHotPosts);//获取数据请求并解析

    }

    private void getHotPostsData(RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: 热帖主界面请求 --> " + json);

                //数据解析
                mWordHotPostsBean = gson.fromJson(json, WordHotPostsBean.class);
                mPartList = mWordHotPostsBean.getPart();
                Log.d(TAG, "热帖主界面 part 解析 Succeed");
                mDataList = mWordHotPostsBean.getData();
                Log.d(TAG, "热帖主界面 data 解析 Succeed");
                initRecyclerView();
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    private void initRecyclerView() {
        if (mWordHotPostsBean != null) {
            for (int i = 0; i < mPartList.size(); i++) {
                String bigPartId = mPartList.get(i).getID();
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE).edit();
                editor.putString("bigPartId", bigPartId);
                editor.apply();
            }

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            adapter = new WordHotPostsAdapter(getActivity(), mPartList, mDataList);
            mRvHotPosts.setLayoutManager(layoutManager);
            mRvHotPosts.setHasFixedSize(true);
            mRvHotPosts.setAdapter(adapter);

            adapter.setListener(this);
        }
    }

    @Override
    public void subareaOnClickRefresh(int parentPosition) {
        String bigPartId = mPartList.get(parentPosition).getID();
        if (bigPartId != null) {
            RequestQueue requestQueue = NoHttp.newRequestQueue();
            Request<String> requestRefreshHotPosts = NoHttp.createStringRequest(I.POSTS_REFRESH, RequestMethod.POST);
            requestRefreshHotPosts.add("bigPartId", bigPartId);
            getRefreshHotPostsData(parentPosition, requestQueue, requestRefreshHotPosts);//获取数据请求并解析
        }
    }

    private void getRefreshHotPostsData(final int parentPosition, RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.d(TAG, "onSucceed: 热帖主界面刷新请求 --> " + json);
                //数据解析
                mRefreshWordHotPostsBean = gson.fromJson(json, WordHotPostsBean.class);
                mRefreshPartList = mRefreshWordHotPostsBean.getPart();
                mRefreshDataList = mRefreshWordHotPostsBean.getData();

                adapter.setSubareaData(mRefreshPartList, mRefreshDataList, parentPosition);
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
