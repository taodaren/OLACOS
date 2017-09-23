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
import net.osplay.service.entity.WordAddBean;
import net.osplay.service.entity.WordRecoBean;
import net.osplay.service.entity.base.HomeData;
import net.osplay.ui.adapter.WordMineAdapter;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.utils.HomeDataMapper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 社区：我的分模块
 */

public class WordMineFragment extends BaseFragment {
    private static final String TAG = "WordMineFragment";
    private RecyclerView mRvWordMine;

    private Gson gson = new Gson();
    private List<WordAddBean> mAddWorList;
    private List<WordRecoBean> mRecoWordList;

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
        mRvWordMine = (RecyclerView) inflate.findViewById(R.id.recycler_word_mine);
        return inflate;
    }

    @Override
    public void initData() {
        super.initData();
        RequestQueue requestQueue = NoHttp.newRequestQueue();

        //创建一个字符串类型请求，自定义请求方法。
        Request<String> requestAddWord = NoHttp.createStringRequest(I.ADD_WORD, RequestMethod.POST);
        requestAddWord.add("memberId", "667b6b89c10f41c5aba9980fa47c8b76");

        Request<String> requestRecoWord = NoHttp.createStringRequest(I.RECOM_WORD, RequestMethod.GET);
        requestRecoWord.add("memberId", "667b6b89c10f41c5aba9980fa47c8b76");
        requestRecoWord.add("rows", "5");

        //获取数据请求并解析
        getAddWordData(requestQueue, requestAddWord);
        getRecoWordData(requestQueue, requestRecoWord);
    }

    private void getAddWordData(RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: 加入的社区====================" + json);

                //数据解析（集合）
                Type type = new TypeToken<List<WordAddBean>>() {
                }.getType();
                mAddWorList = gson.fromJson(json, type);
                Log.d(TAG, "onSucceed: 加入的社区解析结果====================" + mAddWorList);

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

    private void getRecoWordData(RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: 推荐的社区====================" + json);

                //数据解析（集合）
                Type type = new TypeToken<List<WordRecoBean>>() {
                }.getType();
                mRecoWordList = gson.fromJson(json, type);
                Log.d(TAG, "onSucceed: 推荐的社区解析结果====================" + mRecoWordList);

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
        if (mRecoWordList != null && mAddWorList != null) {
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mRvWordMine.setLayoutManager(mLayoutManager);
            mRvWordMine.setHasFixedSize(true);

            List<HomeData> list = new ArrayList<>();
            list.add(HomeDataMapper.transformWordAddData(mAddWorList, WordMineAdapter.TYPE_ADD_WORD, false));
            list.add(HomeDataMapper.transformWordRecoData(mRecoWordList, WordMineAdapter.TYPE_RECOM_WORD, false));

            WordMineAdapter adapter = new WordMineAdapter(getActivity(), list, mAddWorList, mRecoWordList);
            mRvWordMine.setAdapter(adapter);
        }
    }

}
