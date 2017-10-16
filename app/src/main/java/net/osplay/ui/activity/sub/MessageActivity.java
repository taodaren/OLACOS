package net.osplay.ui.activity.sub;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.MeiZiBean;
import net.osplay.service.entity.MemberInfoBean;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.MessageAdapter;

import java.util.List;

/**
 * 消息功能
 */

public class MessageActivity extends BaseActivity {
    private RecyclerView mRvMsg;
    private Gson gson = new Gson();
    private String corporationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initData();
        initView();
        corporationId = getIntent().getStringExtra("corporationId");


    }

    private void initView() {
        setToolbar("消息", View.VISIBLE);
        mRvMsg = (RecyclerView) findViewById(R.id.recycler_msg);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvMsg.setLayoutManager(layoutManager);
    }

    private void initData() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.SELECT_GROUP_MEMBER, RequestMethod.POST);
        request.add("rows", 10);
        request.add("page", 1);
        request.add("corporationId", corporationId);
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "获取全部申请列表：：" + json);
                if (json == null) {
                    return;
                } else {
                    formatMembetJson(json);
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

    private void formatMembetJson(String json) {
        MemberInfoBean memberInfoBean = gson.fromJson(json, MemberInfoBean.class);
        List<MemberInfoBean.RowsBean> rows = memberInfoBean.getRows();
        mRvMsg.setAdapter(new MessageAdapter(this,rows));

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
