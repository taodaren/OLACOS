package net.osplay.ui.fragment.sub;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.data.bean.CommonTitleBean;
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
    private static final int ACTION_FOLLOW = 0;
    private static final int ACTION_RECO = 1;
    private RecyclerView mRvWordMine;

    private Gson gson = new Gson();
    private List<WordAddBean> mAddWorList = new ArrayList<>();
    private List<WordRecoBean> mRecoWordList = new ArrayList<>();
    private List<HomeData> mDatas = new ArrayList<>();
    private WordMineAdapter mAdapter;

    private String accountID;
    private HomeData addBean;
    private RequestQueue requestQueue;
    private Request<String> requestAddWord;
    private Request<String> requestRecoWord;

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_word_mine, null);
        mRvWordMine = (RecyclerView) inflate.findViewById(R.id.recycler_word_mine);
        return inflate;
    }

    @Override
    public void initData() {
        super.initData();

        accountID = AppHelper.getInstance().getUserID();
        requestQueue = NoHttp.newRequestQueue();

        //创建一个字符串类型请求，自定义请求方法。
        requestAddWord = NoHttp.createStringRequest(I.ADD_WORD, RequestMethod.POST);
        requestAddWord.add("memberId", accountID);

        requestRecoWord = NoHttp.createStringRequest(I.RECOM_WORD, RequestMethod.POST);
        requestRecoWord.add("memberId", accountID);
        requestRecoWord.add("rows", "3");

        addTitle("加入的专区", R.drawable.word_add, ACTION_FOLLOW);
    }

    private void addTitle(String title, int imgID, int action) {
        CommonTitleBean bean = new CommonTitleBean(title, imgID);
        mDatas.add(HomeDataMapper.transformCommonTitle(bean, WordMineAdapter.TYPE_TITLE, true));

        switch (action) {
            case ACTION_FOLLOW:
                getAddWordData(requestQueue, requestAddWord);
                break;
            case ACTION_RECO:
                getRecoWordData(requestQueue, requestRecoWord);
                break;
        }
    }

    private void getAddWordData(RequestQueue requestQueue, Request<String> request) {
        if (TextUtils.isEmpty(accountID)) {
            addEmptyItem();
            return;
        }
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: 加入的社区数据请求====================" + json);

                //数据解析（集合）
                if (json != null) {
                    Type type = new TypeToken<List<WordAddBean>>() {
                    }.getType();
                    mAddWorList = gson.fromJson(json, type);
                    transformData(ACTION_FOLLOW);
                    Log.d(TAG, "onSucceed: 加入的社区解析结果====================" + mAddWorList);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                addTitle("推荐的专区", R.drawable.word_add, ACTION_RECO);
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    private void addEmptyItem() {
        addBean = new HomeData();
        addBean.setSpan(false);
        addBean.setItemType(WordMineAdapter.TYPE_ADD_EMPTY);
        mDatas.add(addBean);
        addTitle("推荐的专区", R.drawable.word_add, ACTION_RECO);
    }

    private void transformData(int action) {
        switch (action) {
            case ACTION_FOLLOW:
                mDatas.addAll(HomeDataMapper.transformWordAddDatas(mAddWorList, WordMineAdapter.TYPE_ADD_WORD, false));
                addEmptyItem();
                break;
            case ACTION_RECO:
                mDatas.addAll(HomeDataMapper.transformWordRecoDatas(mRecoWordList, WordMineAdapter.TYPE_RECO_WORD, true));
                initRecyclerView();
                break;
        }
    }


    private void getRecoWordData(RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: 推荐的社区数据请求====================" + json);

                //数据解析（集合）
                if (json != null) {
                    Type type = new TypeToken<List<WordRecoBean>>() {
                    }.getType();
                    mRecoWordList = gson.fromJson(json, type);
                    transformData(ACTION_RECO);
                    Log.d(TAG, "onSucceed: 推荐的社区解析结果====================" + mRecoWordList);
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

    private void initRecyclerView() {
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        mRvWordMine.setLayoutManager(mLayoutManager);
        mRvWordMine.setHasFixedSize(true);

        mAdapter = new WordMineAdapter(getActivity(), mDatas);
        mRvWordMine.setAdapter(mAdapter);

    }

}
