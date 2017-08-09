package net.osplay.module_word;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import net.osplay.olacos.BaseFragment;
import net.osplay.olacos.R;


/**
 * 社区模块
 */

public class WordFragment extends BaseFragment {

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_word, null);
        return inflate;
    }

    /**
     * 在 onActivityCreated 方法中初始化 Toolbar
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbar(R.id.toolbar_home, R.string.word_name, View.VISIBLE, View.GONE);
    }

}
