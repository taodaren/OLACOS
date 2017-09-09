package net.osplay.ui.activity.sub;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;
import net.osplay.ui.adapter.sub.JoinCommunityDetailsAdapter;
import net.osplay.utils.FastBlur;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences=getSharedPreferences("AddCommunity", Activity.MODE_PRIVATE);
        editor=preferences.edit();
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        noBar();
        setContentView(R.layout.activity_join_community_details);
        initView();
        bindEvent();
    }
    private void initView() {
        jcd_add_but= (Button) findViewById(R.id.jcd_add_but);
        //设置背景图片虚化
//        jcd_bg= (ImageView) findViewById(R.id.jcd_bg);
//        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.example02);
//        jcd_bg.getViewTreeObserver().addOnPreDrawListener(
//                new ViewTreeObserver.OnPreDrawListener() {
//
//                    @Override
//                    public boolean onPreDraw() {
//                        blur(bitmap, jcd_bg);
//                        return true;
//                    }
//                });

        jcd_toolbar= (Toolbar) findViewById(R.id.jcd_toolbar);
        jcd_toolbar.setNavigationIcon(R.drawable.title_back);
        jcd_recy= (RecyclerView) findViewById(R.id.jcd_recy);
        jcd_recy.setLayoutManager(new LinearLayoutManager(JoinCommunityDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
        jAdapter=new JoinCommunityDetailsAdapter(JoinCommunityDetailsActivity.this);
        jcd_recy.setAdapter(jAdapter);

    }

    private void bindEvent() {
        jcd_add_but.setOnClickListener(mOnClickListener);
    }

    //去除状态栏
    private void noBar(){
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
    }

    //图片虚化
    private void blur(Bitmap bkg, View view) {
        long startMs = System.currentTimeMillis();
        float scaleFactor = 4;
        float radius = 2;

        Bitmap overlay = Bitmap.createBitmap(
                (int) (view.getMeasuredWidth() / scaleFactor),
                (int) (view.getMeasuredHeight() / scaleFactor),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft() / scaleFactor, -view.getTop()
                / scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bkg, 0, 0, paint);

        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        view.setBackground(new BitmapDrawable(getResources(), overlay));
        System.out.println(System.currentTimeMillis() - startMs + "ms");
    }

    private View.OnClickListener mOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.jcd_add_but://加入社团
                    Toast.makeText(JoinCommunityDetailsActivity.this,"加入成功",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(JoinCommunityDetailsActivity.this,MainActivity.class));
                    finish();
                    editor.putString("addAnnotated", "addolacos");
                    editor.commit();
                    break;
            }
        }
    };

}

