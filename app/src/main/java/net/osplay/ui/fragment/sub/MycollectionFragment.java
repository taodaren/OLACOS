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
import net.osplay.service.entity.MyAreaBean;
import net.osplay.service.entity.MycollectionBean;
import net.osplay.ui.adapter.MyAreaAdapter;
import net.osplay.ui.adapter.MycollectionAdapter;

import java.util.List;

/**
 * 个人中心---我的收藏
 */
public class MycollectionFragment extends Fragment {

    private View inflate;
    private RecyclerView collection_recycler;
    private Gson mGson=new Gson();
    private List<MycollectionBean.RowsBean> rows;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_mine_page_word, container, false);
        setView();
        initData();
        return inflate;
    }
    private void setView() {
        collection_recycler= (RecyclerView) inflate.findViewById(R.id.collection_recycler);
        collection_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initData() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.MY_COLLECTION, RequestMethod.POST);
        request.add("page","1");
        request.add("rows","9");
        request.add("memberId", AppHelper.getInstance().getUser().getID());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }
            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB","-----------:"+json);
                if(json!=null){
                    formatMyarea(json);
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

    private void formatMyarea(String json) {
        MycollectionBean mycollectionBean = mGson.fromJson(json, MycollectionBean.class);
        rows = mycollectionBean.getRows();
        collection_recycler.setAdapter(new MycollectionAdapter(getContext(),rows) );
    }



}
