package net.osplay.module_secondhand;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.osplay.olacos.BaseFragment;
import net.osplay.olacos.R;


/**
 * 二手交易模块
 */

public class SecondhandFragment extends BaseFragment {

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_secondhand, null);
        return inflate;
    }

    /**
     * 在 onActivityCreated 方法中初始化 Toolbar
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbar(R.id.toolbar_home, R.string.secondhand_name, View.GONE,View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //显示菜单
        inflater.inflate(R.menu.menu_toolbar, menu);
        //显示需要菜单项，隐藏多余菜单项
        menu.findItem(R.id.menu_code).setVisible(false);
        menu.findItem(R.id.menu_msg).setVisible(false);
        menu.findItem(R.id.menu_category).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_category) {
            Toast.makeText(mContext, "menu_category", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
