package net.osplay.ui.activity.sub;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.sub.JoinCommunityDetailsAdapter;

/**
 * j加入社团详情
 */
public class JoinCommunityDetailsActivity extends BaseActivity {
    private CollapsingToolbarLayout coll_title;
    private RecyclerView jcd_recy;
    private JoinCommunityDetailsAdapter jAdapter;
    private Toolbar jcd_toolbar;
    private ImageView jcd_bg;
    private Button jcd_add_but;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String corporationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("AddCommunity", Activity.MODE_PRIVATE);
        editor = preferences.edit();
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        noBar();
        setContentView(R.layout.activity_join_community_details);
        initView();
        bindEvent();
    }

    private void initView() {
        jcd_add_but = (Button) findViewById(R.id.jcd_add_but);
        jcd_toolbar = (Toolbar) findViewById(R.id.jcd_toolbar);
        jcd_toolbar.setNavigationIcon(R.drawable.title_back);
        jcd_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        jcd_recy = (RecyclerView) findViewById(R.id.jcd_recy);
        jcd_recy.setLayoutManager(new LinearLayoutManager(JoinCommunityDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
        jAdapter = new JoinCommunityDetailsAdapter(JoinCommunityDetailsActivity.this);
        jcd_recy.setAdapter(jAdapter);

    }

    private void bindEvent() {
        jcd_add_but.setOnClickListener(mOnClickListener);
    }

    //去除状态栏
    private void noBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            corporationId = getIntent().getStringExtra("corporationId");//得到社团id
            switch (v.getId()) {
                case R.id.jcd_add_but://加入社团
                    Toast.makeText(JoinCommunityDetailsActivity.this, "加入成功", Toast.LENGTH_SHORT).show();
                    //这里是加入成功的社团显示传值
//                    Intent show=new Intent(JoinCommunityDetailsActivity.this,MainActivity.class);
//                    show.putExtra("jgb",1);
//                    startActivity(show);
//                    finish();


//                    startActivity(new Intent(JoinCommunityDetailsActivity.this,MainActivity.class));
//                    finish();
//                    editor.putString("addAnnotated", "addolacos");
//                    editor.commit()
                    joinHttp();
                    break;
            }
        }
    };

    private void joinHttp() {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.ADD_GROUP_MEMBER, RequestMethod.POST);
        request.add("memberId", AppHelper.getInstance().getUser().getID());
        request.add("corporationId", corporationId);

        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "申请加入社团结果：" + json);
                if (json == null) {
                    return;
                } else {
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

}

