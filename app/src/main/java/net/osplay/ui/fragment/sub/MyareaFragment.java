package net.osplay.ui.fragment.sub;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import net.osplay.service.entity.UserLoginBean;
import net.osplay.ui.adapter.MyAreaAdapter;
import net.osplay.ui.fragment.base.BaseFragment;

import java.util.List;

/**
 * 我的关注列表
 */
public class MyareaFragment extends Fragment {
    private View inflate;
    private RecyclerView area_recycler;
    private Gson mGson=new Gson();
    private List<MyAreaBean.RowsBean> rows;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_mine_page_goods, container, false);
        setView();
        initData();
        return inflate;
    }
    private void setView() {
        area_recycler= (RecyclerView) inflate.findViewById(R.id.area_recycler);
        area_recycler.setLayoutManager(new GridLayoutManager(getContext(),3));


    }

    private void initData() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.MY_AREA, RequestMethod.POST);
        request.add("page","1");
        request.add("rows","20");
        request.add("memberId",AppHelper.getInstance().getUser().getID());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }
            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
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
        MyAreaBean myAreaBean = mGson.fromJson(json, MyAreaBean.class);
        rows = myAreaBean.getRows();
        area_recycler.setAdapter(new MyAreaAdapter(getActivity(),rows) );
    }




}
