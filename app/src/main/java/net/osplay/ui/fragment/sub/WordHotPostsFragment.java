package net.osplay.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.VideoBean;
import net.osplay.service.entity.VideoMapperBean;
import net.osplay.ui.adapter.WordHotPostsAdapter;
import net.osplay.ui.fragment.base.BaseFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 社区：热区 → 热帖
 */

public class WordHotPostsFragment extends BaseFragment {
    private static final String TAG = "WordHotPostsFragment";

    private RecyclerView mRvHotPosts;
    private List<VideoBean> mHotPostsList;
    private Gson gson = new Gson();

    @SuppressLint("ValidFragment")
    public WordHotPostsFragment() {
    }

    @SuppressLint("ValidFragment")
    public WordHotPostsFragment(Context context, int resId) {
        super(context, resId);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_word_hot_posts, null);
        mRvHotPosts = (RecyclerView) inflate.findViewById(R.id.recycler_hot_posts);
        return inflate;
    }

    @Override
    public void initData() {
        super.initData();
        RequestQueue requestQueue = NoHttp.newRequestQueue();

        //创建一个字符串类型请求，自定义请求方法。
        Request<String> requestHotPosts = NoHttp.createStringRequest(I.HOME_DETAIL, RequestMethod.GET);

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
                Log.d(TAG, "onSucceed: HotPostsRequest====================" + json);

                //数据解析（集合）
                Type type = new TypeToken<VideoMapperBean>() {
                }.getType();
                VideoMapperBean bean = gson.fromJson(json, type);
                List<VideoBean> temp = bean.getTrailers();
                mHotPostsList = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    mHotPostsList.add(temp.get(i));
                }

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
        if (mHotPostsList != null) {
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
            WordHotPostsAdapter adapter = new WordHotPostsAdapter(getActivity(), mHotPostsList);
            mRvHotPosts.setLayoutManager(layoutManager);
            mRvHotPosts.setHasFixedSize(true);
            mRvHotPosts.setAdapter(adapter);
        }
    }

}
