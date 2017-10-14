package net.osplay.ui.activity.sub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bumptech.glide.Glide;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.app.MFGT;
import net.osplay.olacos.R;
import net.osplay.ui.fragment.sub.TabBatFragment;
import net.osplay.ui.fragment.sub.TabGoodsFragment;
import net.osplay.ui.fragment.sub.TabLeagueFragment;
import net.osplay.ui.fragment.sub.TabWordFragment;
import net.osplay.utils.PublishPopWindow;

import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private Fragment currentFragment;
    private TabBatFragment tabHomeFragment;
//    private TabHomeFragment tabHomeFragment;
    private TabWordFragment tabWordFragment;
    private TabGoodsFragment tabGoodsFragment;
    private TabLeagueFragment tabLeagueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        initBottomNavBar();
        initFabButton();
        defaultShowHome();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUserInfo();
    }

    private void setUserInfo() {
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0);
        CircleImageView imgAvatar = (CircleImageView) headerView.findViewById(R.id.nav_avatar);
        TextView tvAccount = (TextView) headerView.findViewById(R.id.nav_account);

        if (AppHelper.getInstance().isLogined()) {//如果是登录状态
            tvAccount.setText(AppHelper.getInstance().getUser().getNICK_NAME());
            if (AppHelper.getInstance().getUser().getHEAD_PATH() == null) {
                Glide.with(this).load(R.drawable.avatar_default).into(imgAvatar);
            } else {
                Glide.with(this).load(I.BASE_URL + AppHelper.getInstance().getUser().getHEAD_PATH()).into(imgAvatar);
            }
        }
    }

    /**
     * 默认显示 TabHomeFragment
     */
    private void defaultShowHome() {
        tabHomeFragment = new TabBatFragment();
        if (!tabHomeFragment.isAdded()) {
            addFragment(R.id.main_content, tabHomeFragment);
            currentFragment = tabHomeFragment;
        }
    }

    /**
     * 设置底部导航
     */
    private void initBottomNavBar() {
        BottomNavigationBar mNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_nav_bar);

        //进行必要的设置
        mNavigationBar.setBarBackgroundColor(R.color.colorPrimary);//设置模块名背景色
        mNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);//设置背景模式
        mNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);//设置Tab点击的模式

        //添加 Tab
        mNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.tab_home_selected, R.string.home_name)//设置选中图标和模块名
                        .setInactiveIconResource(R.drawable.tab_home)//设置未选中图标
                        .setActiveColorResource(R.color.colorWhite))//设置导航背景色
                .addItem(new BottomNavigationItem(R.drawable.tab_word_selected, R.string.word_name)
                        .setInactiveIconResource(R.drawable.tab_word)
                        .setActiveColorResource(R.color.colorWhite))
                .addItem(new BottomNavigationItem(R.drawable.tab_post, null)
                        .setActiveColorResource(R.color.colorWhite))
                .addItem(new BottomNavigationItem(R.drawable.tab_goods_selected, R.string.goods_name)
                        .setInactiveIconResource(R.drawable.tab_goods)
                        .setActiveColorResource(R.color.colorWhite))
                .addItem(new BottomNavigationItem(R.drawable.tab_league_selected, R.string.league_name)
                        .setInactiveIconResource(R.drawable.tab_league)
                        .setActiveColorResource(R.color.colorWhite))
                .setFirstSelectedPosition(0)//默认显示面板
                .initialise();//初始化

        mNavigationBar.setTabSelectedListener(this);
    }

    /**
     * 设置悬浮按钮
     */
    private void initFabButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首页 → 发布
                PublishPopWindow popWindow = new PublishPopWindow(MainActivity.this);
                popWindow.showMoreWindow(v);
            }
        });
    }

    /**
     * 添加一个 {@link Fragment} 到活动的布局
     *
     * @param containerViewId 存放 fragment 容器的 id
     * @param fragment        被添加的 fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 切换 fragment
     *
     * @param fragment 待切换碎片
     */
    @SuppressLint("CommitTransaction")
    private void replaceFragment(Fragment fragment) {
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), fragment);
    }

    /**
     * 添加或者显示 fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
        if (currentFragment == fragment)
            return;
        //如果当前 fragment 未被添加，则添加到 Fragment 管理器中
        if (!fragment.isAdded()) {
            transaction.hide(currentFragment).add(R.id.main_content, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    /**
     * Tab 被选中
     *
     * @param position 当前被选中的 Tab
     */
    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                if (tabHomeFragment == null) {
                    tabHomeFragment = new TabBatFragment();
                }
                replaceFragment(tabHomeFragment);
                break;
            case 1:
                if (tabWordFragment == null) {
                    tabWordFragment = new TabWordFragment();
                }
                replaceFragment(tabWordFragment);
                break;
            case 3:
                if (tabGoodsFragment == null) {
                    tabGoodsFragment = new TabGoodsFragment();
                }
                replaceFragment(tabGoodsFragment);
                break;
            case 4:
                if (tabLeagueFragment == null) {
                    tabLeagueFragment = new TabLeagueFragment();
                }
                replaceFragment(tabLeagueFragment);
                break;
            default:
        }
    }

    /**
     * Tab 被取消选中
     *
     * @param position 当前被选中的 Tab
     */
    @Override
    public void onTabUnselected(int position) {

    }

    /**
     * Tab 被重新选中
     *
     * @param position 当前被选中的 Tab
     */
    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        } else {
            finish();
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    /**
     * 侧滑栏头部布局点击事件
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_avatar:
                MFGT.gotoLogin(MainActivity.this, MinePageSelfActivity.class, "loginMine");
                break;
            case R.id.nav_code:
//                startActivity(new Intent(this, QRCodeActivity.class));
                break;
            default:
        }
    }

}
