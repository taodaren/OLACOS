package net.osplay.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.osplay.olacos.R;

/**
 * 社团模块
 */

public class LeagueFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.fragment_league, null);
        tabLayout= (TabLayout) inflate.findViewById(R.id.league_tabLayout);
        viewPager= (ViewPager) inflate.findViewById(R.id.league_viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            String[] itemName=new String[]{
                    "最新","最热","我的"
            };
            @Override
            public int getCount() {
                return itemName.length;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return new NewestFragment();
                    case 1:
                        return new HottestFragment();
                    case 2:
                        return new MineFragment();
                }
                return new NewestFragment();
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return itemName[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        return  inflate;
    }
}
