package net.osplay.ui.fragment.sub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import net.osplay.olacos.R;


/**
 * 商品模块
 */

public class TabGoodsFragment extends Fragment {
    private View view;
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



}

