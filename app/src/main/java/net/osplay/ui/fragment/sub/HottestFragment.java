package net.osplay.ui.fragment.sub;


import android.support.v4.app.Fragment;
import android.view.View;

import net.osplay.olacos.R;
import net.osplay.ui.fragment.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HottestFragment extends BaseFragment {

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_hottest, null);
        return inflate;
    }
}
