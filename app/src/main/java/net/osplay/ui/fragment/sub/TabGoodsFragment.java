package net.osplay.ui.fragment.sub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.osplay.olacos.R;
import net.osplay.ui.fragment.base.BaseFragment;


/**
 * 商品模块
 */

public class TabGoodsFragment extends Fragment {
    private View view;
    private Fragment currentFragment;
    private TestFragment mFragment;
    private GoodsSecondHandFragment hFragment;
    private FragmentManager supportFragmentManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_tab_goods, null);
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.mall_container, new GoodsMallFragment())
                        .commit();
        return view;
    }

    //    @Override
//    public View initView() {
//        view = View.inflate(getContext(), R.layout.fragment_tab_goods, null);
//        mFragment = new GoodsMallFragment();
//        hFragment = new GoodsSecondHandFragment();
//        FragmentManager manager = getFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        mFragment = new GoodsMallFragment();
//        transaction.add(R.id.mall_container, mFragment);
//        transaction.commit();
//        return view;
//    }

}

