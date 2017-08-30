package net.osplay.ui.fragment.sub;


import android.support.v4.app.Fragment;
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

import net.osplay.olacos.R;
import net.osplay.service.entity.LeagueBean;
import net.osplay.ui.adapter.LeagueAdapter;
import net.osplay.ui.fragment.base.BaseFragment;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewestFragment extends BaseFragment {

    private RecyclerView community_recy;
    Gson mGson=new Gson();
    LeagueAdapter adapter;
    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_newest, null);
        community_recy = (RecyclerView)inflate.findViewById(R.id.community_recy);
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
                LeagueBean bean = mGson.fromJson(json, LeagueBean.class);
                List<LeagueBean.TrailersBean> trailers = bean.getTrailers();
                adapter=new LeagueAdapter(getActivity(),trailers);
                community_recy.setAdapter(adapter);
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
        return inflate;
    }



}
