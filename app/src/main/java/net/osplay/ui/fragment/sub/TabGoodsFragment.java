package net.osplay.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
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
    private WebView mWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_tab_goods, null);
        //注意 getActivity()若使用 view 会报错，此处有大坑
        mDrawerLayout = getActivity().findViewById(R.id.drawer_layout);
        //设置侧滑界面
        initDrawerLayout();
        initWebView(inflate);
        return inflate;
    }

    @SuppressLint({"SetJavaScriptEnabled", "WrongConstant"})
    private void initWebView(View view) {
        mWebView = view.findViewById(R.id.web_view_goods);

        //android 原生不支持 onclick 所以js要使用 ontouch 事件。
        WebSettings settings = mWebView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSavePassword(true);
        settings.setSaveFormData(true);
        settings.setJavaScriptEnabled(true);     // enable navigator.geolocation
        settings.setGeolocationEnabled(true);
        settings.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");     // enable Web Storage: localStorage, sessionStorage
        settings.setDomStorageEnabled(true);

        mWebView.requestFocus();
        mWebView.setScrollBarStyle(0);
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                //按下返回键并且 webView 界面可以返回
                if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mWebView.goBack();
                        }
                    });
                    return true;
                }
                return false;
            }
        });
//        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(I.TAB_GOODS);
    }

    //    /**
//     * 在 onActivityCreated 方法中初始化 Toolbar
//     */
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setToolbar(R.id.toolbar_goods, R.string.goods_name, View.VISIBLE, View.GONE, true);
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        //显示菜单
//        inflater.inflate(R.menu.menu_toolbar, menu);
//        //显示需要菜单项，隐藏多余菜单项
//        menu.findItem(R.id.menu_msg).setVisible(false);
//        menu.findItem(R.id.menu_search).setVisible(true);
//        menu.findItem(R.id.menu_code).setVisible(false);
//        menu.findItem(R.id.menu_category).setVisible(false);
//        menu.findItem(R.id.menu_register).setVisible(false);
//        menu.findItem(R.id.menu_set).setVisible(false);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://导航按钮固定 id
                //展示滑动菜单
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
//            case R.id.menu_search:
//                //跳转搜索界面
//                startActivity(new Intent(getContext(), SearchActivity.class));
//                break;
        }
        return true;
    }

    /**
     * 销毁 WebView
     */
    @Override
    public void onDestroyView() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroyView();
    }

}
