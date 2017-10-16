package net.osplay.ui.fragment.sub;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.HotRanKingBean;
import net.osplay.service.entity.goods.NewCreatedBean;
import net.osplay.ui.activity.sub.JoinCommunityDetailsActivity;
import net.osplay.ui.adapter.sub.NewlyCreatedAdapter;
import net.osplay.ui.fragment.base.BaseBussFragment;
import net.osplay.ui.fragment.base.BaseFragment;

import java.util.List;

/**
 * 热门排行
 */
public class NewlyCreatedFragment extends BaseFragment {
    private RecyclerView newly_recy;
    private NewlyCreatedAdapter nAdapter;
    private Gson mGson=new Gson();
    private View inflate;


    @Override
    public View initView() {
        inflate = View.inflate(getContext(), R.layout.fragment_newly_created, null);
        setView();
        getHttp();
        return inflate;
    }
    private void setView() {
        newly_recy= (RecyclerView) inflate.findViewById(R.id.newly_recy);
        newly_recy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

    }

    public void getHttp() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.NEWLYCREATED, RequestMethod.POST);
        request.add("rows", 10);
        request.add("page",1);
        request.add("isExamine",1);
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "最新创建" + json);
                if (json == null) {
                    return;
                } else {
                    formatJsonIsJoin(json);
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

    private void formatJsonIsJoin(String json) {
        NewCreatedBean newCreatedBean = mGson.fromJson(json, NewCreatedBean.class);
        List<NewCreatedBean.RowsBean> rows = newCreatedBean.getRows();
        nAdapter=new NewlyCreatedAdapter(getActivity(),rows);
        newly_recy.setAdapter(nAdapter);
    }
}
