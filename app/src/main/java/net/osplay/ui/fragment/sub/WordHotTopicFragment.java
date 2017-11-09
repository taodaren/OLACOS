package net.osplay.ui.fragment.sub;

import android.support.v7.widget.GridLayoutManager;
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
import net.osplay.service.entity.WordTopicBean;
import net.osplay.ui.adapter.WordHotTopicAdapter;
import net.osplay.ui.fragment.base.BaseFragment;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 社区：热区 → 专区
 */

public class WordHotTopicFragment extends BaseFragment {
    private static final String TAG = "WordHotTopicFragment";
    private RecyclerView mRvTopic;
    private List<WordTopicBean> mTopicList;
    private Gson gson = new Gson();

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_word_hot_topic, null);
        mRvTopic = (RecyclerView) inflate.findViewById(R.id.recycler_hot_topic);
        return inflate;
    }

    @Override
    public void initData() {
        super.initData();
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.AREA, RequestMethod.POST);
        getTopicData(requestQueue, request);
    }

    private void getTopicData(RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: 专区请求 --> " + json);

                //数据解析
                Type type = new TypeToken<List<WordTopicBean>>() {}.getType();
                mTopicList = gson.fromJson(json, type);
                Log.d(TAG, "专区 data 解析成功" + mTopicList.size());
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
        if (mTopicList != null) {
            GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
            mRvTopic.setLayoutManager(mLayoutManager);
            mRvTopic.setHasFixedSize(true);
            WordHotTopicAdapter mAdapter = new WordHotTopicAdapter(getActivity(), mTopicList);
            mRvTopic.setAdapter(mAdapter);
        }
    }

}
