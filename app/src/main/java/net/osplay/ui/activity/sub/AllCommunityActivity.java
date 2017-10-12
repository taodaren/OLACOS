package net.osplay.ui.activity.sub;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.AllCommunityBean;
import net.osplay.ui.adapter.sub.AllCommunityAdapter;

import java.util.List;


public class AllCommunityActivity extends Activity {

    private RecyclerView all_recy;
    private Gson mGson = new Gson();
    private List<AllCommunityBean.RowsBean> rows;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_community_activity);
        all_recy = (RecyclerView) findViewById(R.id.all_recy);
        all_recy.setLayoutManager(new GridLayoutManager(AllCommunityActivity.this, 3));
        setData();// 给listView设置adapter
    }

    private void setData() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.ALL_ASSOCIATION, RequestMethod.POST);
        request.add("rows", 5);
        request.add("page", 1);
//        request.add("isExamine", 1);
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "全部社团数据：" + json);
                if (json == null) {
                    return;
                } else {
                    formatJson(json);
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

    private void formatJson(String json) {
        AllCommunityBean allCommunityBean = mGson.fromJson(json, AllCommunityBean.class);
        rows = allCommunityBean.getRows();
        Log.e("JGB", "全部社团集合：" + rows);
        String photo = rows.get(2).getPHOTO();
        Log.e("JGB", "头像：" + rows);
        all_recy.setAdapter(new AllCommunityAdapter(AllCommunityActivity.this, rows));
    }
    public void onClick(View view) {
        finish();
    }
}