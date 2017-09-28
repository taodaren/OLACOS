package net.osplay.ui.fragment.sub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.WordTopicAllBean;
import net.osplay.ui.adapter.sub.WordTopicAllAdapter;
import net.osplay.ui.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 专区详情 → 全部帖子
 */

public class TopicInfoAllFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {
    private static final String TAG = "TopicInfoAllFragment";
    private static final int ACTION_REFRESH = 0;
    private static final int ACTION_LOADMORE = 1;
    private static final int DATA_COUNT = 10;
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipe_fm_topic_info_all)
    SwipeToLoadLayout mSwipe;
    Unbinder unbinder;

    private int page = 1;
    private int count;
    private Gson gson = new Gson();
    private RequestQueue requestQueue = NoHttp.newRequestQueue();
    private String parentId;
    private List<WordTopicAllBean.RowsBean> data = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private WordTopicAllAdapter aAdapter;

    public static TopicInfoAllFragment newInstance(String parentId) {
        TopicInfoAllFragment fragment = new TopicInfoAllFragment();
        Bundle bundle = new Bundle();
        bundle.putString(I.Organization.PARENT_ID, parentId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.parentId = getArguments().getString(I.Organization.PARENT_ID, "0");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        swipeTarget.setHasFixedSize(true);
        swipeTarget.setLayoutManager(mLayoutManager);

        mSwipe.setOnRefreshListener(this);
        mSwipe.setOnLoadMoreListener(this);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_topic_info_all, null);

        return inflate;
    }

    @Override
    public void initData() {
        super.initData();
        getData(ACTION_REFRESH);
    }

    private void getData(final int action) {
        Request<String> request = NoHttp.createStringRequest(I.POSTS_LIST, RequestMethod.POST);
        request.add("twoPartId", parentId);
        request.add("page", page);
        request.add("size", DATA_COUNT);
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                if (json != null) {
                    // 处理total为0的情况
                    String s = json.substring(9, 10);
                    if (!s.equals("0")) {
                        WordTopicAllBean wordTopicAllBean = gson.fromJson(json, WordTopicAllBean.class);
                        count = wordTopicAllBean.getTotal();
                        List<WordTopicAllBean.RowsBean> tempList = wordTopicAllBean.getRows();
                        resultData(tempList, action);
                    }
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

    private void resultData(List<WordTopicAllBean.RowsBean> tempList, int action) {
        if (tempList != null && !tempList.isEmpty()) {
            switch (action) {
                case ACTION_REFRESH:
                    data.clear();
                    data.addAll(tempList);
                    // close refresh
                    this.showRefreshing(false);
                    break;
                case ACTION_LOADMORE:
                    data.addAll(tempList);
                    this.showLoadMoreing(false);
                    break;
            }
        }
        showListView();
    }

    private void showListView() {
        if (aAdapter == null) {
            aAdapter = new WordTopicAllAdapter(getContext(), data);
        } else {
            aAdapter.setData(data);
        }
        if (swipeTarget != null & swipeTarget.getAdapter() == null) {
            swipeTarget.setAdapter(aAdapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void showRefreshing(boolean enable) {
        if (enable) {
            mSwipe.post(new Runnable() {
                @Override
                public void run() {
                    mSwipe.setRefreshing(true);
                }
            });
        } else {
            mSwipe.setRefreshing(false);
        }
    }

    private void showLoadMoreing(boolean enable) {
        if (!mSwipe.isLoadMoreEnabled()) {
            return;
        }
        if (enable) {
            mSwipe.post(new Runnable() {
                @Override
                public void run() {
                    mSwipe.setLoadingMore(true);
                }
            });
        } else {
            mSwipe.setLoadingMore(false);
            showToastMsgByStrID(R.string.attention_data_is_not_more);
        }
    }

    private void showToastMsgByStrID(int strID) {
        String msg = getString(strID);
        this.showToastMsgByStr(msg);
    }

    private void showToastMsgByStr(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore() {
        if (checkoutLoadMoreState()) {
            page++;
            getData(ACTION_LOADMORE);
        } else {
            showLoadMoreing(false);
            mSwipe.setLoadMoreEnabled(false);
        }
    }

    /**
     *
     * @return true:can load more; false: no load more
     */
    private boolean checkoutLoadMoreState() {
        return data.size() < count;
    }

    @Override
    public void onRefresh() {
        // 重置状态
        resetState();
        getData(ACTION_REFRESH);
    }

    private void resetState() {
        mSwipe.setLoadMoreEnabled(true);
        page = 1;
        data.clear();
    }
}
