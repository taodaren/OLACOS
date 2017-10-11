package net.osplay.ui.fragment.sub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import net.osplay.app.AppHelper;
import net.osplay.olacos.R;
import net.osplay.ui.activity.sub.LeagueIMActivity;
import net.osplay.ui.adapter.base.FragmentAdapter;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.utils.TabUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 社团模块
 */

public class TabLeagueFragment extends BaseFragment {
    private DrawerLayout mDrawerLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> mList = new ArrayList<>();
    private String[] titles = new String[]{"推荐", "社团活动", "社团作品"};
    private FragmentAdapter fragmentAdapter = null;
    private NewestFragment nFragment;
    private SocialActivityFragment hFragment;
    private MineFragment mFragment;
    private CommunityFragment cFragment;
    private CommunityWorksFragment wFragment;
    private CommunityALoginFragment lFragment;
    private CommunityPLoginFragment pFragment;
    private String lannotated = "olacos";
    private String cAnnotated;
    private String addlannotated = "addolacos";
    private String addcAnnotated;
    private View inflate;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;


    @Override
    public View initView() {
        inflate = View.inflate(getContext(), R.layout.fragment_tab_league, null);
        initDrawerLayout();
        //获取创建社团的值
        SharedPreferences preferences1 = getActivity().getSharedPreferences("CreateCommunity", getActivity().MODE_PRIVATE);
        cAnnotated = preferences1.getString("Annotated", "defaultname");
        //获取加入社团的值
        SharedPreferences preferences2 = getActivity().getSharedPreferences("AddCommunity", getActivity().MODE_PRIVATE);
        addcAnnotated = preferences2.getString("addAnnotated", "defaultname");
        setView();
        return inflate;
    }

    private void setView() {
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        tabLayout = (TabLayout) inflate.findViewById(R.id.league_tabLayout);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(tabLayout, 25, 25);
            }
        });
        viewPager = (ViewPager) inflate.findViewById(R.id.league_viewPager);
        nFragment = new NewestFragment(getActivity(), R.layout.fragment_newest);//社团推荐
        hFragment = new SocialActivityFragment(getActivity(), R.layout.fragment_create_community);//社团活动
        cFragment = new CommunityFragment(getActivity(), R.layout.fragment_community);//加入或创建社团之后的社团活动
        mFragment = new MineFragment(getActivity(), R.layout.fragment_mine);//社团作品
        wFragment=new CommunityWorksFragment(getActivity(),R.layout.fragment_community_works);//加入或创建社团之后的社团作品
        lFragment=new CommunityALoginFragment();//活动未等陆的提醒
        pFragment=new CommunityPLoginFragment();//作品未的登录的提醒
        mList.add(nFragment);
        mList.add(lFragment);
        mList.add(pFragment);
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), mContext, mList, titles);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);//设置 TabLayout 和 ViewPager 绑定
        appBarLayout = (AppBarLayout) inflate.findViewById(R.id.league_appbar);
        toolbar = (Toolbar) inflate.findViewById(R.id.toolbar_league);

        /**
         * 创建或加入社团成功后才显示的社团主页
         */
        if (lannotated.equals(cAnnotated) | addlannotated.equals(addcAnnotated)) {
            appBarLayout.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.GONE);
        } else {
            appBarLayout.setVisibility(View.GONE);
            toolbar.setVisibility(View.VISIBLE);
        }

    }


    //设置toolbar
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbar(R.id.toolbar_league, R.string.league_name, View.VISIBLE, View.GONE, true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//导航按钮固定 id
            mDrawerLayout.openDrawer(GravityCompat.START);//展示滑动菜单
        }
        return true;
    }


}




