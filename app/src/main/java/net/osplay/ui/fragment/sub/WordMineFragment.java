package net.osplay.ui.fragment.sub;


import android.annotation.SuppressLint;
import android.content.Context;
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
import net.osplay.service.entity.base.HomeData;
import net.osplay.ui.adapter.WordMineRecommendAdapter;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.utils.HomeDataMapper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 社区 → 我的
 */

public class WordMineFragment extends BaseFragment {
    private static final String TAG = "WordMineFragment";

    private RecyclerView mRvAddWord, mRvRecommendWord;
    private Gson gson = new Gson();

    private List<VideoBean> mAddWorList;
    private List<VideoBean> mRecommendWorList;

    @SuppressLint("ValidFragment")
    public WordMineFragment() {
    }

    @SuppressLint("ValidFragment")
    public WordMineFragment(Context mContext, int resId) {
        super(mContext, resId);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_word_mine, null);
        mRvAddWord = (RecyclerView) inflate.findViewById(R.id.recycler_add_word);
        mRvRecommendWord = (RecyclerView) inflate.findViewById(R.id.recycler_recommend_word);
        return inflate;
    }

    @Override
    public void initData() {
        super.initData();
        RequestQueue requestQueue = NoHttp.newRequestQueue();

        //创建一个字符串类型请求，自定义请求方法。
        Request<String> requestAddWord = NoHttp.createStringRequest(I.HOME_DETAIL, RequestMethod.GET);
        Request<String> requestRecommendWord = NoHttp.createStringRequest(I.HOME_DETAIL, RequestMethod.GET);

        //获取数据请求并解析
        getAddWordData(requestQueue, requestAddWord);
        getRecommendWordData(requestQueue, requestRecommendWord);
    }

    private void getAddWordData(RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: AddWordRequest====================" + json);

                //数据解析（集合）
                Type type = new TypeToken<VideoMapperBean>() {
                }.getType();
                VideoMapperBean bean = gson.fromJson(json, type);
                List<VideoBean> temp = bean.getTrailers();
                mAddWorList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    mAddWorList.add(temp.get(i));
                }

//                initRecyclerView();
            }

            @Override
            public void onFailed(int what, Response<String> response) {
            }

            @Override
            public void onFinish(int what) {
            }
        });
    }

    private void getRecommendWordData(RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: RecommendWordRequest====================" + json);

                //数据解析（集合）
                Type type = new TypeToken<VideoMapperBean>() {
                }.getType();
                VideoMapperBean bean = gson.fromJson(json, type);
                List<VideoBean> temp = bean.getTrailers();
                mRecommendWorList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    mRecommendWorList.add(temp.get(i));
                }

                initRecyclerRecommend();
            }

            @Override
            public void onFailed(int what, Response<String> response) {
            }

            @Override
            public void onFinish(int what) {
            }
        });
    }

    private void initRecyclerRecommend() {
        if (mRecommendWorList != null) {
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mRvRecommendWord.setLayoutManager(mLayoutManager);
            mRvRecommendWord.setHasFixedSize(true);

            List<HomeData> list = new ArrayList<>();
            list.add(HomeDataMapper.transformRecommendWordData(mRecommendWorList, WordMineRecommendAdapter.TYPE_RECOMMEND_WORD, false));

            WordMineRecommendAdapter adapter = new WordMineRecommendAdapter(getActivity(), list);
            mRvRecommendWord.setAdapter(adapter);
        }
    }

}
