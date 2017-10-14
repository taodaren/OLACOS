package net.osplay.ui.fragment.sub;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.I;
import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.RecommendBean;
import net.osplay.ui.activity.sub.MinePageSelfActivity;
import net.osplay.ui.adapter.RecommendAdapter;
import net.osplay.ui.fragment.base.BaseFragment;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewestFragment extends BaseFragment {

    private RecyclerView community_recy;
    private ImageView not_data;
    private RecommendAdapter adapter;
    private Gson mGson = new Gson();
    private View inflate;


    @Override
    public View initView() {
        inflate = View.inflate(getContext(), R.layout.fragment_newest, null);
        setView();
       getRecommendHttp();
        return inflate;
    }

    //加载控件
    private void setView() {
        community_recy = (RecyclerView) inflate.findViewById(R.id.community_recy);
        not_data = (ImageView) inflate.findViewById(R.id.not_data);
        community_recy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }
    //推荐数据请求
    public void getRecommendHttp() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.RECOMMEND, RequestMethod.POST);
        request.add("rows",10);
        request.add("page",1);
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                if(json==null){
                    return;
                }else{
                    gsonFormat(json);
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
   //推荐数据解析
    private void gsonFormat(String json) {
        RecommendBean recommendBean = mGson.fromJson(json, RecommendBean.class);
        List<RecommendBean.RowsBean> rows = recommendBean.getRows();
        Log.e("JGB","推荐数据："+rows);
        if(rows.size()==0){
            community_recy.setVisibility(View.GONE);
            not_data.setVisibility(View.VISIBLE);
        }else{
            adapter = new RecommendAdapter(getActivity(), rows);
            community_recy.setAdapter(adapter);
        }

    }
}
