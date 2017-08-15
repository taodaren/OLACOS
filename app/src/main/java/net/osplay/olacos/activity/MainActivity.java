package net.osplay.olacos.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import net.osplay.module_home.HomeFragment;
import net.osplay.module_league.LeagueFragment;
import net.osplay.module_post.PublishPopWindow;
import net.osplay.module_secondhand.SecondhandFragment;
import net.osplay.module_word.WordFragment;
import net.osplay.olacos.R;
import net.osplay.tab.SpecialTab;
import net.osplay.tab.SpecialTabRound;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBottomTab();
        setFabButton();
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
        replaceFragment(new HomeFragment());

        PageNavigationView tab = (PageNavigationView) findViewById(R.id.main_tab);

        NavigationController navigationController = tab.custom()
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
                        replaceFragment(new HomeFragment());
                        break;
                    case 1:
                        replaceFragment(new WordFragment());
                        break;
                    case 3:
                        replaceFragment(new SecondhandFragment());
                        break;
                    case 4:
                        replaceFragment(new LeagueFragment());
                        break;
                }
            }

            @Override
            public void onRepeat(int index) {
                //重复选中时触发
                switch (index) {
                    case 0:
                        replaceFragment(new HomeFragment());
                        break;
                    case 1:
                        replaceFragment(new WordFragment());
                        break;
                    case 3:
                        replaceFragment(new SecondhandFragment());
                        break;
                    case 4:
                        replaceFragment(new LeagueFragment());
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

    /**
     * 首页 → 发布
     */
    public void onClickPublish(View v) {
        PublishPopWindow popWindow = new PublishPopWindow(MainActivity.this);
        popWindow.showMoreWindow(v);
    }

}
