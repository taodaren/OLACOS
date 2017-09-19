package net.osplay.ui.activity.sub;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.MeiZiBean;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.MessageAdapter;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 消息功能
 */

public class MessageActivity extends BaseActivity {
    private static final String TAG = "MessageActivity";
    private RecyclerView mRvMsg;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initData();
        initView();
    }

    private void initData() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> msgRequest = NoHttp.createStringRequest(I.MSG_FULI, RequestMethod.GET);//Banner 数据
        getMsgItemData(requestQueue, msgRequest);
    }

    private void getMsgItemData(RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: msgRequest====================" + json);

                //数据解析（集合）
//                Type type = new TypeToken<List<MeiZiBean>>() {
//                }.getType();
                MeiZiBean bean = gson.fromJson(json, MeiZiBean.class);
                List<MeiZiBean.ResultsBean> results = bean.getResults();
                Log.d(TAG, "onSucceed: results====================" + results);
                initRecyclerView(results);
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    private void initRecyclerView(List<MeiZiBean.ResultsBean> results) {
        if (results != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRvMsg.setLayoutManager(layoutManager);
            mRvMsg.setHasFixedSize(true);
            MessageAdapter adapter = new MessageAdapter(this, results);
            mRvMsg.setAdapter(adapter);
        }
    }

    private void initView() {
        setToolbar("消息", View.VISIBLE);
        setClickListener();
        setChat();
    }

    private void setChat() {
        mRvMsg = (RecyclerView) findViewById(R.id.recycler_msg);
    }

    private void setClickListener() {
        //回复我的
        findViewById(R.id.layout_msg_replay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MessageActivity.this, "回复我的【需要空白页】", Toast.LENGTH_SHORT).show();
            }
        });

        //消息提醒
        findViewById(R.id.layout_msg_remind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MessageActivity.this, "消息提醒【需要空白页】", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

}
