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
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.I;
import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.MycollectionBean;
import net.osplay.ui.adapter.MycollectionAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 其他用户的收藏
 */
public class OtherCollectionFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.center_recycler)
    RecyclerView centerRecycler;
    @BindView(R.id.center_not_data_iv)
    ImageView centerNotDataIv;

    private View inflate;
    private String memberId;
    private Gson mGson = new Gson();
    private List<MycollectionBean.RowsBean> rows;
    private MycollectionAdapter cAadapter;
    int flag = 0;//定义标记变量


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.center_layout, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        centerRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if (bundle != null) {
            memberId = bundle.getString("otherMemberId");
        }
        initData();
        return inflate;
    }

    private void initData() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.MY_COLLECTION, RequestMethod.POST);
        request.add("page", "1");
        request.add("rows", "5");
        request.add("memberId", memberId);
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "其他用户收藏:" + json);
                if (json != null) {
                    formatMyarea(json);
                } else {
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
        if (mycollectionBean.getTotal() == 0) {
            centerRecycler.setVisibility(View.GONE);
            centerNotDataIv.setVisibility(View.VISIBLE);
        } else {
            rows = mycollectionBean.getRows();
            cAadapter=new MycollectionAdapter(getContext(), rows);
            centerRecycler.setAdapter(cAadapter);

            //点赞的点击事件
            SetOnClickListen setOnClickListen = new SetOnClickListen() {
                @Override
                public void setOnClick(int position) {

                }

                @Override
                public void setOnClick(int position, TextView zanTv, TextView collecTv, TextView commentTv, ImageView zanIv, ImageView cllecIv) {
                    if (flag == 0) {
                        RequestQueue requestQueue = NoHttp.newRequestQueue();
                        Request<String> request = NoHttp.createStringRequest(I.POSTS_ZAN, RequestMethod.POST);
                        request.add("memberId", rows.get(position).getMEMID());
                        request.add("topickId", rows.get(position).getID());
                        request.add("mark",0);
                        requestQueue.add(0, request, new OnResponseListener<String>() {
                            @Override
                            public void onStart(int what) {
                            }
                            @Override
                            public void onSucceed(int what, Response<String> response) {
                                String json = response.get();
                                Log.e("TAG","点赞结果："+json);
                                if (json != null) {
                                } else {
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
                        int zanCount = Integer.parseInt(zanTv.getText().toString());
                        zanTv.setText(zanCount+1+"");
                        zanIv.setImageResource(R.drawable.ic_sugar_selected);
                    } else if (flag == 1) {
                        RequestQueue requestQueue = NoHttp.newRequestQueue();
                        Request<String> request = NoHttp.createStringRequest(I.POSTS_ZAN, RequestMethod.POST);
                        request.add("memberId", rows.get(position).getMEMID());
                        request.add("topickId", rows.get(position).getID());
                        request.add("mark",1);
                        requestQueue.add(0, request, new OnResponseListener<String>() {
                            @Override
                            public void onStart(int what) {
                            }
                            @Override
                            public void onSucceed(int what, Response<String> response) {
                                String json = response.get();
                                Log.e("TAG","点赞结果："+json);
                                if (json != null) {
                                } else {
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
                        int zanCount = Integer.parseInt(zanTv.getText().toString());
                        zanTv.setText(zanCount-1+"");
                        zanIv.setImageResource(R.drawable.ic_sugar_unselected);
                    }
                    flag = (flag + 1) % 2;//其余得到循环执行上面2个不同的功能
                }




            };
            cAadapter .onClick(setOnClickListen);
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
