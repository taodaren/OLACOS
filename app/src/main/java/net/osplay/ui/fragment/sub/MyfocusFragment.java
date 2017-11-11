package net.osplay.ui.fragment.sub;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiangyy.easydialog.CommonDialog;
import com.wang.avi.AVLoadingIndicatorView;
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
import net.osplay.service.entity.MyFocusBean;
import net.osplay.ui.adapter.MyFocusAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 个人中心-----我的关注(ok)
 */
public class MyfocusFragment extends Fragment {
    @BindView(R.id.center_recycler)
    RecyclerView centerRecycler;
    Unbinder unbinder;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    @BindView(R.id.center_not_data_tv)
    TextView centerNotDataTv;
    private View inflate;
    private Gson mGson = new Gson();
    private List<MyFocusBean.RowsBean> rows;
    private MyFocusAdapter myFocusAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.center_layout, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        centerRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        initData();
        return inflate;
    }

    private void initData() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.MY_FOCUS_PAGER, RequestMethod.POST);
        request.add("page", "1");
        request.add("rows", Integer.MAX_VALUE);
        request.add("memberId", AppHelper.getInstance().getUser().getID());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                avi.show();
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "关注的人数据:" + json);
                if (json == null) {
                    return;
                } else {
                    avi.hide();
                    formatMyfocus(json);
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
        Log.e("JGB", "关注数量：" + myFocusBean.getTotal());
        int total = myFocusBean.getTotal();
        if (total == 0) {
            centerRecycler.setVisibility(View.GONE);
            centerNotDataTv.setVisibility(View.VISIBLE);
            centerNotDataTv.setText("你还没有任何关注，快去逛逛吧！");
        } else {
            rows = myFocusBean.getRows();
            myFocusAdapter = new MyFocusAdapter(getActivity(), rows);
            centerRecycler.setAdapter(myFocusAdapter);
            adapterOnclick(rows);
        }
    }

    //取消关注的点击事件
    private void adapterOnclick(final List<MyFocusBean.RowsBean> rows) {
        SetOnClickListen setOnClickListen = new SetOnClickListen() {
            @Override
            public void setOnClick(final int position) {
                new CommonDialog.Builder(getActivity())
                        .setTitle("提示")
                        .setMessage("确定不再关注此人")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                unsubScribe(rows, position);

                            }
                        }).setNegativeButton("取消", null).show();
            }

            @Override
            public void setOnClick(int position, TextView zanTv, TextView collecTv, TextView commentTv, ImageView zanIv, ImageView cllecIv) {

            }
        };
        myFocusAdapter.onClick(setOnClickListen);
    }

    // 取消关注的方法
    private void unsubScribe(List<MyFocusBean.RowsBean> rows, final int position) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.FOLLOW, RequestMethod.POST);
        request.add("memberId", AppHelper.getInstance().getUser().getID());
        request.add("followId", rows.get(position).getID());
        request.add("mark", 1);
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "我的关注取消结果:" + json);
                if (json == null) {
                    return;
                } else {
                    MyfocusFragment.this.rows.remove(position);
                    myFocusAdapter.notifyItemRemoved(position);
                    myFocusAdapter.notifyDataSetChanged();
                    initData();
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }
}
