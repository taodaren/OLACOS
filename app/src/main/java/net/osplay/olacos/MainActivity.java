package net.osplay.olacos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import net.osplay.module_home.HomeFragment;
import net.osplay.module_league.LeagueFragment;
import net.osplay.module_secondhand.SecondhandFragment;
import net.osplay.module_word.WordFragment;
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
    }

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
        mainTab.setTextDefaultColor(0xFF888888);
        mainTab.setTextCheckedColor(0xFFF7C936);
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
}
