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
public class TopicInfoCityFragment extends BaseFragment {


    public TopicInfoCityFragment() {
        // Required empty public constructor
    }


    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_topic_info_city, null);
        return inflate;
    }
}
