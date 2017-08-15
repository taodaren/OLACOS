package net.osplay.module_word;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;

import net.osplay.olacos.base.BaseFragment;
import net.osplay.olacos.R;


/**
 * 社区模块
 */

public class WordFragment extends BaseFragment {
    //侧滑菜单
    private DrawerLayout mDrawerLayout;

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_word, null);
        //注意 getActivity() 若使用 view 会报错，此处有大坑
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        isLogin();
        return inflate;
    }

    /**
     * 在 onActivityCreated 方法中初始化 Toolbar
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbar(R.id.toolbar_home, R.string.word_name, View.VISIBLE, View.GONE, true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//导航按钮固定 id
            //展示滑动菜单
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }

}
