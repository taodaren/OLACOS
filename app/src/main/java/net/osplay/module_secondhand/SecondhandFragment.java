package net.osplay.module_secondhand;


import android.view.View;

import net.osplay.olacos.BaseFragment;
import net.osplay.olacos.R;


/**
 * 二手交易模块
 */

public class SecondhandFragment extends BaseFragment {

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_secondhand, null);
        return inflate;
    }

}
