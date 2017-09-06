package net.osplay.ui.fragment.sub;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.olacos.R;
import net.osplay.service.entity.LeagueBean;
import net.osplay.service.entity.RecommendBean;
import net.osplay.ui.activity.sub.MineCenterActivity;
import net.osplay.ui.activity.sub.MinePageActivity;
import net.osplay.ui.adapter.LeagueAdapter;
import net.osplay.ui.adapter.base.BaseRecyclerViewAdapter;
import net.osplay.ui.adapter.sub.RecommendAdapter;
import net.osplay.ui.fragment.base.BaseBussFragment;
import net.osplay.ui.fragment.base.BaseFragment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewestFragment extends BaseBussFragment  {

    private RecyclerView community_recy;
    private Gson mGson=new Gson();
    private RecommendAdapter adapter;
    private CircleImageView cir;
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
                Log.e("TAG",json);
                RecommendBean recommendBean = mGson.fromJson(json, RecommendBean.class);
                List<LeagueBean.TrailersBean> trailers = recommendBean.getTrailers();
                adapter = new RecommendAdapter(getActivity(), trailers, R.layout.item_league);
                adapter.setOnItemClickListner(new BaseRecyclerViewAdapter.OnItemClickListner() {
                    @Override
                    public void onItemClickListner(View v, int position) {
                        cir= (CircleImageView) v.findViewById(R.id.league_avatar_img);
                        cir.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                               startActivity(new Intent(getActivity(), MinePageActivity.class));
                            }
                        });
                    }
                });
                community_recy.setAdapter(adapter);
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });



    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void initData() {

    }


}
