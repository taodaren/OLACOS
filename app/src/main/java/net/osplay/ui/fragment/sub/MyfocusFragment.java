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
import net.osplay.service.entity.MyFocusBean;
import net.osplay.ui.adapter.MyAreaAdapter;
import net.osplay.ui.adapter.MyFocusAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyfocusFragment extends Fragment {
    private View inflate;
    private RecyclerView focus_recycler;
    private Gson mGson=new Gson();
    private List<MyFocusBean.RowsBean> rows;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_myfocus, container, false);
        setView();
        initData();
        return inflate;
    }
    private void setView() {
        focus_recycler= (RecyclerView) inflate.findViewById(R.id.focus_recycler);
        focus_recycler.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    private void initData() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.MY_FOCUS_PAGER, RequestMethod.POST);
        request.add("page","1");
        request.add("rows","20");
        request.add("memberId", AppHelper.getInstance().getUser().getID());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }
            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB","-----------fo:"+json);
                if(json!=null){
                    formatMyfocus(json);
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

    private void formatMyfocus(String json) {
        MyFocusBean myFocusBean = mGson.fromJson(json, MyFocusBean.class);
        rows = myFocusBean.getRows();
        focus_recycler.setAdapter(new MyFocusAdapter(getContext(),rows));
    }


}
