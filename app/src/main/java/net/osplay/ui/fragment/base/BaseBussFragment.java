package net.osplay.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by acer-PC on 2017/8/31.
 */

public abstract class BaseBussFragment extends Fragment {
    protected Context context;

    protected int resId;

    public BaseBussFragment() {
    }

    public BaseBussFragment(Context context, int resId) {
        this.context = context;
        this.resId = resId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if(view == null){
            view = inflater.inflate(resId, null);
            initView(view,savedInstanceState);
            bindEvent();
            initData();
        }
        return view;
    }

    protected abstract void initView(View view, Bundle savedInstanceState);

    protected abstract void bindEvent();

    protected abstract void initData();
}
