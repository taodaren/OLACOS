package net.osplay.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import net.osplay.olacos.R;
import net.osplay.ui.fragment.base.BaseFragment;

/**
 * 专区详情容器部分
 */

public class DetailsTopicInfoFragment extends BaseFragment implements View.OnClickListener {
    private int mCurrentIndex, mIndex;

    private RadioButton mrbAll, mrbFine, mrbCity;
    private Fragment[] mFragments;
    private RadioButton[] mRadioButtons;

    @SuppressLint("ValidFragment")
    public DetailsTopicInfoFragment() {
    }

    @SuppressLint("ValidFragment")
    public DetailsTopicInfoFragment(Context context, int resId) {
        super(context, resId);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_details_topic_info, null);

        mrbAll = (RadioButton) inflate.findViewById(R.id.rb_all_topic);
        mrbFine = (RadioButton) inflate.findViewById(R.id.rb_fine_topic);
        mrbCity = (RadioButton) inflate.findViewById(R.id.rb_city_topic);

        mrbAll.setOnClickListener(this);
        mrbFine.setOnClickListener(this);
        mrbCity.setOnClickListener(this);

        initFragment();
        initRadioButton();
        showFragment();

        return inflate;
    }

    private void initFragment() {
        TopicInfoAllFragment mAllFragment = new TopicInfoAllFragment();
        TopicInfoFineFragment mFineFragment = new TopicInfoFineFragment();
        TopicInfoCityFragment mCityFragment = new TopicInfoCityFragment();

        mFragments = new Fragment[3];
        mFragments[0] = mAllFragment;
        mFragments[1] = mFineFragment;
        mFragments[2] = mCityFragment;
    }

    private void initRadioButton() {
        mRadioButtons = new RadioButton[3];
        mRadioButtons[0] = mrbAll;
        mRadioButtons[1] = mrbFine;
        mRadioButtons[2] = mrbCity;
    }

    private void showFragment() {
        getChildFragmentManager().beginTransaction()
                .add(R.id.fragment_container_topic, mFragments[0])
                .add(R.id.fragment_container_topic, mFragments[1])
                .add(R.id.fragment_container_topic, mFragments[2])
                .show(mFragments[0])
                .hide(mFragments[1])
                .hide(mFragments[2])
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_all_topic:
                mIndex = 0;
                break;
            case R.id.rb_fine_topic:
                mIndex = 1;
                break;
            case R.id.rb_city_topic:
                mIndex = 2;
                break;
            default:
        }
        setFragment();
    }

    private void setFragment() {
        if (mIndex != mCurrentIndex) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.hide(mFragments[mCurrentIndex]);
            if (!mFragments[mIndex].isAdded()) {
                fragmentTransaction.add(R.id.fragment_container_topic, mFragments[mIndex]);
            }
            fragmentTransaction.show(mFragments[mIndex]);
            fragmentTransaction.commit();
            mCurrentIndex = mIndex;
        }
        setRadioButton();
    }

    private void setRadioButton() {
        for (int i = 0; i < mRadioButtons.length; i++) {
            mRadioButtons[i].setChecked(i == mIndex);
        }
    }

}
