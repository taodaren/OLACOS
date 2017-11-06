package net.osplay.ui.fragment.sub;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.osplay.olacos.R;

/**
 * 商品模块备份
 */
public class TabBatGoodsFragment extends Fragment {
    private View view;
    private ViewPager viewPager;
    int flag = 0;//定义标记变量
    private AnimationDrawable animationDrawable;
    private ImageView switch_egg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_tab_goods_bat, null);
//        initView();
//        switchFragment();

        return view;
    }

    private void switchFragment() {
        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new GoodsMallFragment();
                    case 1:
                        return new GoodsSecondHandFragment();

                }
                return new GoodsMallFragment();
            }

        });
        switch_egg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    switch_egg.setImageResource(R.drawable.animation1);
                    animationDrawable = (AnimationDrawable) switch_egg.getDrawable();
                    animationDrawable.start();
                    viewPager.setCurrentItem(1);
                } else if (flag == 1) {
                    switch_egg.setImageResource(R.drawable.animation2);
                    animationDrawable = (AnimationDrawable) switch_egg.getDrawable();
                    animationDrawable.start();
                    viewPager.setCurrentItem(0);
                }
                flag = (flag + 1) % 2;//其余得到循环执行上面3个不同的功能
            }
        });
    }
//    private void initView() {
//        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
//        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
//        switch_egg = (ImageView) view.findViewById(R.id.switch_egg);
  //  }
}
