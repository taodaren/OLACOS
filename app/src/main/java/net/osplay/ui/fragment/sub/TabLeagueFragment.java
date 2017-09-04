package net.osplay.ui.fragment.sub;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.R;
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
    private HottestFragment hFragment;
    private MineFragment mFragment;
    private CommunityFragment cFragment;
    private String lannotated = "olacos";
    private String cAnnotated;

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_tab_league, null);
        initDrawerLayout();
        SharedPreferences preferences = getActivity().getSharedPreferences("CreateCommunity", getActivity().MODE_PRIVATE);
        cAnnotated = preferences.getString("Annotated", "defaultname");
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        tabLayout = (TabLayout) inflate.findViewById(R.id.league_tabLayout);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(tabLayout, 25, 25);
            }
        });
        viewPager = (ViewPager) inflate.findViewById(R.id.league_viewPager);
        nFragment = new NewestFragment(getActivity(), R.layout.fragment_newest);
        hFragment = new HottestFragment(getActivity(), R.layout.fragment_create_community);
        mFragment = new MineFragment(getActivity(), R.layout.fragment_mine);
        cFragment = new CommunityFragment(getActivity(), R.layout.fragment_community);
        mList.add(nFragment);
        if (lannotated.equals(cAnnotated)) {
            mList.add(cFragment);
        } else {
            mList.add(hFragment);
        }
        mList.add(mFragment);
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), mContext, mList, titles);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);//设置 TabLayout 和 ViewPager 绑定
        return inflate;
    }

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


