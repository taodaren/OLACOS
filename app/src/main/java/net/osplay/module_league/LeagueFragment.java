package net.osplay.module_league;


import android.view.View;

import net.osplay.olacos.BaseFragment;
import net.osplay.olacos.R;


/**
 * 社团模块
 */

public class LeagueFragment extends BaseFragment {

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_league, null);
        return inflate;
    }

}
