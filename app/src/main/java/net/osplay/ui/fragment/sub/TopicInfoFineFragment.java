package net.osplay.ui.fragment.sub;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.ui.fragment.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicInfoFineFragment extends BaseFragment {
    private String parentId;

    public static TopicInfoFineFragment newInstance(String parentId) {
        TopicInfoFineFragment fragment = new TopicInfoFineFragment();
        Bundle bundle = new Bundle();
        bundle.putString(I.Organization.PARENT_ID, parentId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.parentId = getArguments().getString(I.Organization.PARENT_ID, "0");
    }


    public TopicInfoFineFragment() {
        // Required empty public constructor
    }

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_topic_info_fine, null);
        return inflate;
    }

}
