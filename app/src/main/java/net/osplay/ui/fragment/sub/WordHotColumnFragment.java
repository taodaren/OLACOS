package net.osplay.ui.fragment.sub;

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
import net.osplay.ui.adapter.WordColumnAdapter;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.utils.HomeDataMapper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 社区：热区 → 专栏
 */

public class WordHotColumnFragment extends BaseFragment {
    private static final String TAG = "WordHotColumnFragment";

    private Gson gson = new Gson();
    private RecyclerView mRvWordColumn;

    private List<VideoBean> mSubList;//已订阅
    private List<VideoBean> mUnSubList;//未订阅

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_word_hot_column, null);
        mRvWordColumn = (RecyclerView) inflate.findViewById(R.id.recycler_word_column);
        return inflate;
    }

    @Override
    public void initData() {
        super.initData();
        RequestQueue requestQueue = NoHttp.newRequestQueue();

        //创建一个字符串类型请求，自定义请求方法。
        Request<String> requestSubscribe = NoHttp.createStringRequest(I.HOME_DETAIL, RequestMethod.GET);
        Request<String> requestUnsubscribe = NoHttp.createStringRequest(I.HOME_DETAIL, RequestMethod.GET);

        //获取数据请求并解析
        getSubData(requestQueue, requestSubscribe);
        getUnSubData(requestQueue, requestUnsubscribe);
    }

    private void getSubData(RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: mSubListRequest====================" + json);

                //数据解析（集合）
                Type type = new TypeToken<VideoMapperBean>() {
                }.getType();
                VideoMapperBean bean = gson.fromJson(json, type);
                List<VideoBean> temp = bean.getTrailers();
                mSubList = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    mSubList.add(temp.get(i));
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

    private void getUnSubData(RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: mUnSubListRequest====================" + json);

                //数据解析（集合）
                Type type = new TypeToken<VideoMapperBean>() {
                }.getType();
                VideoMapperBean bean = gson.fromJson(json, type);
                List<VideoBean> temp = bean.getTrailers();
                mUnSubList = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    mUnSubList.add(temp.get(i));
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
        if (mSubList != null && mUnSubList != null) {
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mRvWordColumn.setLayoutManager(mLayoutManager);
            mRvWordColumn.setHasFixedSize(true);

            List<HomeData> list = new ArrayList<>();
            list.add(HomeDataMapper.transformWordMineData(mSubList, WordColumnAdapter.TYPE_SUBSCRIBE, false));
            list.add(HomeDataMapper.transformWordMineData(mUnSubList, WordColumnAdapter.TYPE_UN_SUBSCRIBE, false));

            WordColumnAdapter adapter = new WordColumnAdapter(getActivity(), list, mSubList, mUnSubList);
            mRvWordColumn.setAdapter(adapter);
        }
    }

}
