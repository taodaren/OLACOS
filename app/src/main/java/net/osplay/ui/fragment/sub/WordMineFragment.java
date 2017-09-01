package net.osplay.ui.fragment.sub;


import android.content.Context;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.fragment.base.BaseFragment;

/**
 * 社区 → 我的
 */

public class WordMineFragment extends BaseFragment {

    public WordMineFragment() {
    }

    public WordMineFragment(Context mContext, int resId) {
        super(mContext, resId);
    }

    @Override
    public View initView() {
        View view = View.inflate(getContext(), R.layout.fragment_word_mine, null);
        return view;
    }

}
