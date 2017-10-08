package net.osplay.ui.fragment.sub;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.LeagueBean;
import net.osplay.service.entity.RecommendBean;
import net.osplay.ui.activity.sub.MinePageSelfActivity;
import net.osplay.ui.adapter.LeagueAdapter;
import net.osplay.ui.fragment.base.BaseBussFragment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewestFragment extends BaseBussFragment {

    private RecyclerView community_recy;
    private LeagueAdapter adapter;
    private Gson mGson = new Gson();
    private CircleImageView cir;


    @SuppressLint("ValidFragment")
    public NewestFragment() {
    }

    @SuppressLint("ValidFragment")
    public NewestFragment(Context mContext, int resId) {
        super(mContext, resId);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        community_recy = (RecyclerView) view.findViewById(R.id.community_recy);
        community_recy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://api.m.mtime.cn/PageSubArea/TrailerList.api", RequestMethod.GET);
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.e("TAG", json);
                gsonFormat(json);
                setOnclick();

            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    private void setOnclick() {
        SetOnClickListen setOnClickListen = new SetOnClickListen() {
            @Override
            public void setOnClick(int position) {

            }

            @Override
            public void setOnClick(int position, TextView zanTv, TextView collecTv, TextView commentTv, ImageView zanIv, ImageView cllecIv) {
                startActivity(new Intent(getActivity(), MinePageSelfActivity.class));
                getActivity().finish();
            }

        };
        adapter.onClick(setOnClickListen);
    }

    private void gsonFormat(String json) {
        RecommendBean recommendBean = mGson.fromJson(json, RecommendBean.class);
        List<LeagueBean.TrailersBean> trailers = recommendBean.getTrailers();
        adapter = new LeagueAdapter(getActivity(), trailers);
        community_recy.setAdapter(adapter);
    }

    @Override

    protected void bindEvent() {

    }

    @Override
    protected void initData() {

    }


}
