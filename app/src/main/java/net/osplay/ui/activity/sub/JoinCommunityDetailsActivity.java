package net.osplay.ui.activity.sub;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.sub.JoinCommunityDetailsAdapter;

/**
 * j加入社团详情
 */
public class JoinCommunityDetailsActivity extends BaseActivity {
    private android.webkit.WebView webView;
    private CollapsingToolbarLayout coll_title;
    private RecyclerView jcd_recy;
    private JoinCommunityDetailsAdapter jAdapter;
    private Toolbar jcd_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
        setContentView(R.layout.activity_join_community_details);
        jcd_toolbar= (Toolbar) findViewById(R.id.jcd_toolbar);
        jcd_toolbar.setNavigationIcon(R.drawable.title_back);
        jcd_recy= (RecyclerView) findViewById(R.id.jcd_recy);
        jcd_recy.setLayoutManager(new LinearLayoutManager(JoinCommunityDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
        jAdapter=new JoinCommunityDetailsAdapter(JoinCommunityDetailsActivity.this);
        jcd_recy.setAdapter(jAdapter);


    }

    }

