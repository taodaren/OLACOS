package net.osplay.ui.fragment.sub;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import net.osplay.olacos.R;
import net.osplay.ui.fragment.base.BaseFragment;

/**
 * 斗图详情容器部分
 */

public class DetailsDouInfoFragment extends BaseFragment implements View.OnClickListener {

    @SuppressLint("ValidFragment")
    public DetailsDouInfoFragment() {
    }

    @SuppressLint("ValidFragment")
    public DetailsDouInfoFragment(Context context, int resId) {
        super(context, resId);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_details_dou_info, null);
        return inflate;
    }

    @Override
    public void onClick(View v) {
    }

}
