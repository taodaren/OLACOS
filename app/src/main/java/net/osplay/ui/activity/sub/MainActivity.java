package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.ui.fragment.sub.TabGodsFragment;
import net.osplay.ui.fragment.sub.TabHomeFragment;
import net.osplay.ui.fragment.sub.TabLeagueFragment;
import net.osplay.ui.fragment.sub.TabWordFragment;
import net.osplay.utils.PublishPopWindow;
import net.osplay.utils.tab.SpecialTab;
import net.osplay.utils.tab.SpecialTabRound;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainActivity extends AppCompatActivity {
    private int id;
    private NavigationController navigationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBottomTab();
        setFabButton();

        Intent intent = getIntent();
        id = intent.getIntExtra("jgb", -1);
//        if (id > 0) {
//            System.out.println("aaa" + id);
//        }
        if (id == 1) {
            replaceFragment(new TabLeagueFragment());
            navigationController.setSelect(4);
        }
    }

    /**
     * 设置悬浮按钮
     */
    private void setFabButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "可交互提示工具", Snackbar.LENGTH_SHORT)
                        .setAction("可点击后续操作", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "进入后续操作...", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }

    /**
     * 设置底部导航
     */
    private void setBottomTab() {
        //默认显示首页
        replaceFragment(new TabHomeFragment());

        PageNavigationView tab = (PageNavigationView) findViewById(R.id.main_tab);

        navigationController = tab.custom()
                .addItem(newItem(R.drawable.tab_home, R.drawable.tab_home_selected, "首页"))
                .addItem(newItem(R.drawable.tab_word, R.drawable.tab_word_selected, "社区"))
                .addItem(newRoundItem(R.drawable.tab_post, R.drawable.tab_post, "发布"))
                .addItem(newItem(R.drawable.tab_secondhand, R.drawable.tab_secondhand_selected, "二手交易"))
                .addItem(newItem(R.drawable.tab_league, R.drawable.tab_league_selected, "社团"))
                .build();

        //监听导航栏的 Item 选中事件
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                //选中时触发
                switch (index) {
                    case 0:
                        replaceFragment(new TabHomeFragment());
                        break;
                    case 1:
                        replaceFragment(new TabWordFragment());
                        break;
                    case 3:
                        replaceFragment(new TabGodsFragment());
                        break;
                    case 4:
                        replaceFragment(new TabLeagueFragment());
                        break;
                }
            }

            @Override
            public void onRepeat(int index) {
                //重复选中时触发
                switch (index) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }
        });
    }

    /**
     * 正常tab
     */
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        SpecialTab mainTab = new SpecialTab(this);
        mainTab.initialize(drawable, checkedDrawable, text);
        mainTab.setTextDefaultColor(0xFF888888);
        mainTab.setTextCheckedColor(0xFFF7C936);
        return mainTab;
    }

    /**
     * 圆形tab
     */
    private BaseTabItem newRoundItem(int drawable, int checkedDrawable, String text) {
        SpecialTabRound mainTab = new SpecialTabRound(this);
        mainTab.initialize(drawable, checkedDrawable, text);
//        mainTab.setTextDefaultColor(0xFF888888);
//        mainTab.setTextCheckedColor(0xFFF7C936);
        return mainTab;
    }

    /**
     * 切换 fragment
     *
     * @param fragment 待切换碎片
     */
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    /**
     * 首页 → 发布
     */
    public void onClickPublish(View v) {
        PublishPopWindow popWindow = new PublishPopWindow(MainActivity.this);
        popWindow.showMoreWindow(v);
    }

    /**
     * 侧滑栏头部布局点击事件
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_avatar:
                startActivity(new Intent(this, MinePageActivity.class));
                break;
            case R.id.nav_code:
                startActivity(new Intent(this, QRCodeActivity.class));
                break;
            default:
        }
    }

}
