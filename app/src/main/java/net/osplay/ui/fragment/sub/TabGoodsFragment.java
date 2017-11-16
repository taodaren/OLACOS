package net.osplay.ui.fragment.sub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.ui.fragment.base.BaseFragment;

import java.net.URISyntaxException;
import java.util.List;

/**
 * 商品模块
 */
public class TabGoodsFragment extends BaseFragment {
    private static final String TAG = "TabGoodsFragment";
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

    @SuppressLint({"SetJavaScriptEnabled", "WrongConstant", "SdCardPath"})
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
        // enable navigator.geolocation
        settings.setJavaScriptEnabled(true);
        settings.setGeolocationEnabled(true);
        // enable Web Storage: localStorage, sessionStorage
        settings.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
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

        mWebView.setWebChromeClient(new WebChromeClient());

        // 设置允许加载混合内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mWebView.setWebViewClient(new WebViewClient() {
            @SuppressLint("ObsoleteSdkInt")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String newurl) {
                try {
                    //处理intent协议
                    if (newurl.startsWith("intent://")) {
                        Intent intent;
                        try {
                            intent = Intent.parseUri(newurl, Intent.URI_INTENT_SCHEME);
                            intent.addCategory("android.intent.category.BROWSABLE");
                            intent.setComponent(null);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                                intent.setSelector(null);
                            }
                            List<ResolveInfo> resolves = getActivity().getPackageManager().queryIntentActivities(intent, 0);
                            if (resolves.size() > 0) {
                                getActivity().startActivityIfNeeded(intent, -1);
                            }
                            return true;
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                    // 处理自定义scheme协议
                    if (!newurl.startsWith("http")) {
                        Log.i(TAG, "shouldOverrideUrlLoading: yxx==" + newurl);
                        try {
                            // 以下固定写法
                            final Intent intent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse(newurl));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                        } catch (Exception e) {
                            // 防止没有安装的情况
                            e.printStackTrace();
                            launchAppDetail("com.taobao.taobao", "");//启动到应用商店 app 详情界面
                        }
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return super.shouldOverrideUrlLoading(view, newurl);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {//允许所有SSL证书
                handler.proceed();
            }
        });
        mWebView.loadUrl(I.TAB_GOODS);
    }

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
     * 启动到应用商店 app 详情界面
     *
     * @param appPkg    目标 App 的包名
     * @param marketPkg 应用商店包名 ,如果为 "" 则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public void launchAppDetail(String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
