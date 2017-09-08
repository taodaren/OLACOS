package net.osplay.ui.activity.sub;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import net.osplay.ui.activity.base.BaseActivity;

import net.osplay.ui.adapter.sub.EventCommentAdapter;
import net.osplay.ui.adapter.sub.EventIconAdapter;
import net.osplay.ui.adapter.sub.EventSignUpAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 社团活动详情页
 */
public class EventDetailsActivity extends BaseActivity {
    private RecyclerView event_signup_recy,event_comment_recy;
    private EventSignUpAdapter adapter;
    private EventIconAdapter iAdapter;
    private EventCommentAdapter cAdapter;
    private Gson mGson = new Gson();
    private  RequestQueue requestQueue;
    private EditText event_comment_ed;
    private Button event_send_but;
    private  String s;

    private List<String> comments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        setToolbar("活动详情", View.VISIBLE);
        initView();
    }

    private void initView() {
        /**
         * 评价
         */
        event_comment_recy= (RecyclerView) findViewById(R.id.event_comment_recy);
        event_comment_recy.setLayoutManager(new LinearLayoutManager(EventDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
        event_comment_ed= (EditText) findViewById(R.id.event_comment_ed);
        event_send_but= (Button) findViewById(R.id.event_send_but);
        event_send_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = event_comment_ed.getText().toString();
                comments.add(s);
                cAdapter=new EventCommentAdapter(EventDetailsActivity.this,comments,R.layout.item_event_comment);
                event_comment_recy.setAdapter(cAdapter);
            }
        });
        /**
         * 报名人展示
         */
        event_signup_recy= (RecyclerView) findViewById(R.id.event_signup_recy);
        event_signup_recy.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        event_signup_recy.setItemAnimator(new DefaultItemAnimator());
        bind();

    }

    private void bind() {
        requestQueue=NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest("http://api.m.mtime.cn/PageSubArea/TrailerList.api", RequestMethod.GET);
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                gsonFormat(json);
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }
    private void gsonFormat(String json) {
        RecommendBean recommendBean = mGson.fromJson(json, RecommendBean.class);
        List<LeagueBean.TrailersBean> trailers = recommendBean.getTrailers();
        adapter = new EventSignUpAdapter(EventDetailsActivity.this, trailers);
        event_signup_recy.setAdapter(adapter);
    }


}
