package net.osplay.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.ui.fragment.base.BaseFragment;

/**
 * 商品模块
 */
public class TabGoodsFragment extends BaseFragment {
    private DrawerLayout mDrawerLayout;//侧滑菜单

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_tab_goods, null);
        //注意 getActivity()若使用 view 会报错，此处有大坑
        mDrawerLayout = getActivity().findViewById(R.id.drawer_layout);

        //设置 WebView
        WebView webView = inflate.findViewById(R.id.web_view_goods);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(I.TAB_GOODS);

        //设置侧滑界面
        initDrawerLayout();
        return inflate;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://导航按钮固定 id
                //展示滑动菜单
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

}
