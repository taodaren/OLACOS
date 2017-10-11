package net.osplay.ui.fragment.sub;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import net.osplay.app.MFGT;
import net.osplay.olacos.R;
import net.osplay.service.entity.JoinBean;
import net.osplay.ui.activity.sub.CreateOrJoinActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 社团活动---提醒登录
 */
public class CommunityALoginFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.community_login_tv)
    TextView communityLoginTv;
    @BindView(R.id.community_login_ll)
    LinearLayout communityLoginLl;//提醒登录
    @BindView(R.id.community_joinorEstablish_tv)
    TextView communityJoinorEstablishTv;
    @BindView(R.id.community_joinorEstablish_ll)
    LinearLayout communityJoinorEstablishLl;//提醒加入创建
    @BindView(R.id.community_activity_ll)
    LinearLayout communityActivityLl;//社团活动帖
    @BindView(R.id.text)
    TextView text;
    private View inflate;
    private Gson mGson = new Gson();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_community_login, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setLayout() {
        if (!(AppHelper.getInstance().isLogined())) {//未登录状态下隐藏提醒创建
            communityJoinorEstablishLl.setVisibility(View.GONE);
            communityActivityLl.setVisibility(View.GONE);
        } else {
            isJoinHttp();//检查是否加入或创建过社团的网络请求
            communityLoginLl.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        setLayout();
    }

    public void isJoinHttp() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.IS_JOIN, RequestMethod.POST);
        request.add("memberId", AppHelper.getInstance().getUser().getID());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "检查是否创建过社团" + json);
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
        JoinBean joinBean = mGson.fromJson(json, JoinBean.class);
        List<JoinBean.RowsBean> rows = joinBean.getRows();
        if (rows.size() == 0) {
            communityActivityLl.setVisibility(View.GONE);//隐藏社团活动
            communityJoinorEstablishLl.setVisibility(View.VISIBLE);//显示提醒加入或创建
        } else {
            communityJoinorEstablishLl.setVisibility(View.GONE);//隐藏提醒加入或创建
            communityActivityLl.setVisibility(View.VISIBLE);//显示社团活动
            text.setText(rows.toString());
        }
    }

    @OnClick({R.id.community_login_tv, R.id.community_joinorEstablish_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.community_login_tv:
                MFGT.gotoLogin(getActivity(), "loginCOJ1");
                break;
            case R.id.community_joinorEstablish_tv:
                startActivity(new Intent(getActivity(), CreateOrJoinActivity.class));
                break;
        }
    }
}
