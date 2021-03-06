package net.osplay.ui.activity.sub;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

/**
 * 搜索功能
 */

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //设置 WebView
        WebView webView = findViewById(R.id.web_view_search);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(I.WEB_SEARCH);


//        //绑定组件
//        SearchView searchView = (SearchView) findViewById(R.id.search_view);//初始化搜索框变量
//
//        //设置点击搜索按键后的操作（通过回调接口）
//        //参数 = 搜索框输入的内容
//        searchView.setOnClickSearch(new ICallBack() {
//            @Override
//            public void SearchAciton(String string) {
//                System.out.println("我收到了" + string);
//            }
//        });
//
//        //设置点击返回按键后的操作（通过回调接口）
//        searchView.setOnClickBack(new bCallBack() {
//            @Override
//            public void BackAciton() {
//                finish();
//            }
//        });
    }

}
