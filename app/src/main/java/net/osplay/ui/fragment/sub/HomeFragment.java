package net.osplay.ui.fragment.sub;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.ui.activity.sub.LoginActivity;
import net.osplay.ui.activity.sub.MineCollectActivity;
import net.osplay.ui.activity.sub.MineMoneyActivity;
import net.osplay.ui.activity.sub.MinePublishActivity;
import net.osplay.ui.activity.sub.MineSetActivity;
import net.osplay.ui.activity.sub.OrderActivity;
import net.osplay.ui.activity.sub.QRCodeActivity;
import net.osplay.ui.fragment.base.BaseFragment;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 首页模块
 */

public class HomeFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks {
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;

    //侧滑菜单
    private DrawerLayout mDrawerLayout;

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_home, null);
        setDrawerLayout();
        return inflate;
    }

    /**
     * 设置侧滑界面
     */
    private void setDrawerLayout() {
        //注意 getActivity() 若使用 view 会报错，此处有大坑
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        //设置默认选中项
//        navView.setCheckedItem(R.id.nav_money);

        //设置 menu 部分点击事件
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //将滑动菜单关闭
//                mDrawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_money:
                        startActivity(new Intent(getContext(), MineMoneyActivity.class));
                        break;
                    case R.id.nav_car:
                        Toast.makeText(mContext, "跳转到购物车", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_order:
                        startActivity(new Intent(getContext(), OrderActivity.class));
                        break;
                    case R.id.nav_collect:
                        startActivity(new Intent(getContext(), MineCollectActivity.class));
                        break;
                    case R.id.nav_publish:
                        startActivity(new Intent(getContext(), MinePublishActivity.class));
                        break;
                    case R.id.nav_set:
                        startActivity(new Intent(getContext(), MineSetActivity.class));
                        break;
                    default:
                }
                return true;
            }
        });
    }

    /**
     * 在 onActivityCreated 方法中初始化 Toolbar
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbar(R.id.toolbar_home, R.string.home_name, View.GONE, View.VISIBLE, true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //显示菜单
        inflater.inflate(R.menu.menu_toolbar, menu);
        //显示需要菜单项，隐藏多余菜单项
        menu.findItem(R.id.menu_code).setVisible(true);
        menu.findItem(R.id.menu_msg).setVisible(true);
        menu.findItem(R.id.menu_category).setVisible(false);
        menu.findItem(R.id.menu_register).setVisible(false);
        menu.findItem(R.id.menu_set).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://导航按钮固定 id
                //展示滑动菜单
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.menu_msg:
//                Toast.makeText(mContext, "menu_msg", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
        }
        if (item.getItemId() == R.id.menu_code) {
            startActivity(new Intent(getContext(), QRCodeActivity.class));
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(getContext(), perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }
}
