package net.osplay.ui.fragment.sub;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.osplay.olacos.R;
import net.osplay.ui.fragment.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicInfoFineFragment extends BaseFragment {


    public TopicInfoFineFragment() {
        // Required empty public constructor
    }

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_topic_info_fine, null);
        return inflate;
    }

}
