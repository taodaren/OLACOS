package net.osplay.ui.fragment.sub;

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
import net.osplay.service.entity.WordTopicAllBean;
import net.osplay.ui.adapter.sub.WordTopicAllAdapter;
import net.osplay.ui.fragment.base.BaseFragment;

import java.util.List;

/**
 * 专区详情 → 全部帖子
 */

public class TopicInfoAllFragment extends BaseFragment {
    private static final String TAG = "TopicInfoAllFragment";
    private RecyclerView mRvTopicAll;
    private Gson gson = new Gson();
    private RequestQueue requestQueue = NoHttp.newRequestQueue();

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_topic_info_all, null);
        mRvTopicAll = (RecyclerView) inflate.findViewById(R.id.recycler_topic_all);

        return inflate;
    }

    @Override
    public void initData() {
        super.initData();
        Request<String> request = NoHttp.createStringRequest(I.POSTS_LIST, RequestMethod.POST);
        request.add("twoPartId", 10);
        request.add("page", 1);
        request.add("size", 2);
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.d(TAG, "onSucceed: 大区详情 → 全部帖子网络请求");
                if (json != null) {
                    formatJson(json);
                } else {
                    return;
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

    private void formatJson(String json) {
        WordTopicAllBean wordTopicAllBean = gson.fromJson(json, WordTopicAllBean.class);
        List<WordTopicAllBean.RowsBean> rows = wordTopicAllBean.getRows();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRvTopicAll.setLayoutManager(manager);
        WordTopicAllAdapter aAdapter = new WordTopicAllAdapter(getContext(), rows);
        mRvTopicAll.setAdapter(aAdapter);
    }

}
