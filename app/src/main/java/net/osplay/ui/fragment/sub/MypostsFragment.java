package net.osplay.ui.fragment.sub;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.ui.adapter.MyPostsAdapter;
import net.osplay.ui.adapter.MyPostsBean;

import java.util.List;

/**
 * 个人中心——我的帖子
 */
public class MypostsFragment extends Fragment {

    private View inflate;
    private RecyclerView posts_recycler;
    private Gson mGson=new Gson();
    private List<MyPostsBean.RowsBean> rows;
    private int total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_mine_page_dynamic, container, false);
        initView();
        initData();
        return inflate;
    }

    private void initData() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.MY_POSTS, RequestMethod.POST);
        request.add("page","1");
        request.add("rows","25");
        request.add("memberId", AppHelper.getInstance().getUser().getID());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }
            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB","posts_____"+json);
                if(json!=null){
                    formatMyposts(json);
                }else{
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

    private void formatMyposts(String json) {
        MyPostsBean myPostsBean = mGson.fromJson(json, MyPostsBean.class);
        rows = myPostsBean.getRows();
        total = myPostsBean.getTotal();
        posts_recycler.setAdapter(new MyPostsAdapter(getContext(),rows));
    }

    private void initView() {
        posts_recycler= (RecyclerView) inflate.findViewById(R.id.posts_recycler);
        posts_recycler.setLayoutManager(new LinearLayoutManager(getContext()));

    }

}
