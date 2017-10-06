package net.osplay.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
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

public class WordHotPostsFragment extends BaseFragment {
    private static final String TAG = "WordHotPostsFragment";

    private RecyclerView mRvHotPosts;
    private Gson gson = new Gson();
    private WordHotPostsBean mWordHotPostsBean;
    private List<WordHotPostsBean.PartBean> mPartList;
    private List<WordHotPostsBean.DataBean> mDataList;

    @SuppressLint("ValidFragment")
    public WordHotPostsFragment() {
    }

    @SuppressLint("ValidFragment")
    public WordHotPostsFragment(Context context, int resId) {
        super(context, resId);
    }

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

        //创建一个字符串类型请求，自定义请求方法。
        Request<String> requestHotPosts = NoHttp.createStringRequest(I.POSTS_HOT_LIST, RequestMethod.POST);

        //获取数据请求并解析
        getHotPostsData(requestQueue, requestHotPosts);
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
                mDataList = mWordHotPostsBean.getData();
                Log.d(TAG, "onSucceed: 热帖主界面 part 解析结果 --> " + mPartList);
                Log.d(TAG, "onSucceed: 热帖主界面 data 解析结果 --> " + mDataList);
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
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            WordHotPostsAdapter adapter = new WordHotPostsAdapter(getActivity(), mPartList, mDataList);
            mRvHotPosts.setLayoutManager(layoutManager);
            mRvHotPosts.setHasFixedSize(true);
            mRvHotPosts.setAdapter(adapter);
        }
    }

}
