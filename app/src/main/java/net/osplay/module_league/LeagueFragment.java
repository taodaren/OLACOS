package net.osplay.module_league;


import android.os.Bundle;
import android.support.annotation.Nullable;
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

    /**
     * 在 onActivityCreated 方法中初始化 Toolbar
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbar(R.id.toolbar_home, R.string.league_name, View.VISIBLE, View.GONE);
    }

}
