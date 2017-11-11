package net.osplay.ui.fragment.sub;


import android.annotation.SuppressLint;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.osplay.olacos.R;
import net.osplay.ui.fragment.base.BaseFragment;

/**
 * 社团模块
 */

public class TabLeagueFragment extends BaseFragment {
    private DrawerLayout mDrawerLayout;
    private WebView mWebView;

    //    private TabLayout tabLayout;
//    private ViewPager viewPager;
//    private List<Fragment> mList = new ArrayList<>();
//    private String[] titles = new String[]{"推荐", "社团活动", "社团作品"};
//    private FragmentAdapter fragmentAdapter = null;
//    private NewestFragment nFragment;
//    private CommunityALoginFragment lFragment;
//    private CommunityPLoginFragment pFragment;
    private View inflate;
//    private AppBarLayout appBarLayout;
//    private Toolbar league_toolbar;
//    private Gson mGson=new Gson();
//
//    private CircleImageView association_avatar_img;
//    private ImageView association_bg_img;
//    private TextView association_name_tv;
//    private TextView association_time_tv;
//    private TextView association_membet_tv;
//    private TextView association_jianjie_tv;
//    private ImageView league_menu;
//    private RelativeLayout ranking_rl;
//    private Button jcd_release_but,news_tv;
//    private List<AssociationInfoBean.RowsBean> aa;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View initView() {
        inflate = View.inflate(getContext(), R.layout.fragment_tab_league, null);
        //注意 getActivity()若使用 view 会报错，此处有大坑
        mDrawerLayout = getActivity().findViewById(R.id.drawer_layout);
        //设置侧滑界面
        initDrawerLayout();
        initWebView(inflate);
//        setView();
//        setOnClick();
//        setFragment();
//        setToolbars();
//        getAssociationHttp();
        return inflate;
    }

    @SuppressLint({"SetJavaScriptEnabled", "WrongConstant"})
    private void initWebView(View view) {
        mWebView = view.findViewById(R.id.web_view_league);
        mWebView.getSettings().setJavaScriptEnabled(true);
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
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("http://192.168.1.7:8080/qda/html/noneClub.html");
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

//    private void setFragment() {
//        mList.add(nFragment);
//        mList.add(lFragment);
//        mList.add(pFragment);
//        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), mContext, mList, titles);
//        viewPager.setAdapter(fragmentAdapter);
//        tabLayout.setupWithViewPager(viewPager);//设置 TabLayout 和 ViewPager 绑定
//    }

//    //设置toolbar
//    private void setToolbars() {
//        league_toolbar.setNavigationIcon(R.drawable.title_mi);
//        league_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDrawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
//        league_toolbar.setPopupTheme(R.style.toolbarMenu);
//        league_toolbar.inflateMenu(R.menu.popupmenu);
//        league_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.special_topic:
//                        Log.e("JGB","菜单");
//                        break;
//                }
//                return false;
//            }
//        });
//    }

//    //设置
//    private void setView() {
//        league_toolbar= (Toolbar) inflate.findViewById(R.id.league_toolbar);
//        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
//        tabLayout = (TabLayout) inflate.findViewById(R.id.league_tabLayout);
//        tabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                TabUtils.setIndicator(tabLayout, 25, 25);
//            }
//        });
//        viewPager = (ViewPager) inflate.findViewById(R.id.league_viewPager);
//        appBarLayout = (AppBarLayout) inflate.findViewById(R.id.league_appbar);
//        nFragment = new NewestFragment();//社团推荐
//        lFragment=new CommunityALoginFragment();//活动未等陆的提醒
//        pFragment=new CommunityPLoginFragment();//作品未的登录的提醒
//        association_avatar_img = (CircleImageView) inflate.findViewById(R.id.association_avatar_img);
//        association_bg_img = (ImageView) inflate.findViewById(R.id.association_bg_img);
//        association_name_tv = (TextView) inflate.findViewById(R.id.association_name_tv);
//        association_time_tv = (TextView) inflate.findViewById(R.id.association_time_tv);
//        association_membet_tv = (TextView) inflate.findViewById(R.id.association_membet_tv);
//        association_jianjie_tv = (TextView) inflate.findViewById(R.id.association_jianjie_tv);
//        // league_menu = (ImageView) inflate.findViewById(R.id.league_menu);
//        //Glide.with(getActivity()).asGif().load(R.drawable.dtu).into(league_menu);加载动态图
//        ranking_rl = (RelativeLayout) inflate.findViewById(R.id.ranking_rl);
//        jcd_release_but = (Button) inflate.findViewById(R.id.jcd_release_but);
//        news_tv = (Button) inflate.findViewById(R.id.news_tv);
//
//    }
//    private void setOnClick() {
//        ranking_rl.setOnClickListener(mOnClickListener);
//        jcd_release_but.setOnClickListener(mOnClickListener);
//    }
//
//    //获取当前加入或创建过的社团
//    public void getAssociationHttp() {
//        if(!AppHelper.getInstance().isLogined()){
//            appBarLayout.setVisibility(View.GONE);
//        }else{
//            RequestQueue requestQueue = NoHttp.newRequestQueue();
//            Request<String> request = NoHttp.createStringRequest(I.IS_JOIN, RequestMethod.POST);
//            request.add("memberId", AppHelper.getInstance().getUser().getID());
//            requestQueue.add(0, request, new OnResponseListener<String>() {
//                @Override
//                public void onStart(int what) {
//
//                }
//
//                @Override
//                public void onSucceed(int what, Response<String> response) {
//                    String json = response.get();
//                    Log.e("JGB", "检查是否创建过社团" + json);
//                    if (json == null) {
//                        return;
//                    } else {
//                        formatJsonIsJoin(json);
//                    }
//                }
//
//                @Override
//                public void onFailed(int what, Response<String> response) {
//
//                }
//
//                @Override
//                public void onFinish(int what) {
//
//                }
//            });
//        }
//
//
//        }
//    private void formatJsonIsJoin(String json) {
//        JoinBean joinBean = mGson.fromJson(json, JoinBean.class);
//        List<JoinBean.RowsBean> rows = joinBean.getRows();
//        if (rows.size() == 0) {//没有加入或是创建过社团
//            Log.e("JGB","还没有创建或加入过社团");
//            appBarLayout.setVisibility(View.GONE);
//        } else {//判断当前社团是否通过审核
//            getAssociationInfoHttp(rows);//获取当前社团信息
//        }
//    }
//
//    //查询当前社团信息
//    public void getAssociationInfoHttp(List<JoinBean.RowsBean> rows) {
//        RequestQueue requestQueue = NoHttp.newRequestQueue();
//        Request<String> request = NoHttp.createStringRequest(I.ASSOCIATION_INFO, RequestMethod.POST);
//        request.add("corporationId", rows.get(0).getCORPORATIONID());
//        requestQueue.add(0, request, new OnResponseListener<String>() {
//            @Override
//            public void onStart(int what) {
//
//            }
//
//            @Override
//            public void onSucceed(int what, Response<String> response) {
//                String json = response.get();
//                Log.e("JGB", "获取社团信息：：" + json);
//                if (json == null) {
//                    return;
//                } else {
//                    formatAssociationInfoJson(json);
//                }
//            }
//
//            @Override
//            public void onFailed(int what, Response<String> response) {
//
//            }
//
//            @Override
//            public void onFinish(int what) {
//
//            }
//        });
//    }
//    private void formatAssociationInfoJson(String json) {
//        AssociationInfoBean associationInfoBean = mGson.fromJson(json, AssociationInfoBean.class);
//        final List<AssociationInfoBean.RowsBean> aList = associationInfoBean.getRows();
//        Handler mainHandler = new Handler(Looper.getMainLooper());
//        String isexamine = aList.get(0).getISEXAMINE();
//        Log.e("JGB","判断社团是否创建通过："+isexamine);
//        String headid = aList.get(0).getHEADID();
//        Log.e("JGB","判断当前社团是否自己创建的："+headid);
//        if(!aList.get(0).getISEXAMINE().equals("1")){//判断审核是否通过
//            appBarLayout.setVisibility(View.GONE);
//            Log.e("JGB","社团未创建通过");
//        }else{
//            if(AppHelper.getInstance().getUser().getID().equals(headid)){
//                appBarLayout.setVisibility(View.VISIBLE);
//                league_toolbar.setVisibility(View.GONE);
//                Glide.with(getActivity()).load(I.BASE_URL+ aList.get(0).getPHOTO()).into(association_avatar_img);
//                Glide.with(getActivity()).load(I.BASE_URL+ aList.get(0).getBACKGROUND()).into(association_bg_img);
//                association_name_tv.setText(aList.get(0).getNAME());
//                association_time_tv.setText(aList.get(0).getCREATEDATE());
//                //association_membet_tv.setText(aList.get(0));
//                association_jianjie_tv.setText(aList.get(0).getINTRODUCTION());
//                news_tv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent=new Intent(getActivity(),MessageActivity.class);
//                        intent.putExtra("corporationId",aList.get(0).getID());
//                        startActivity(intent);
//                    }
//                });
//                Log.e("JGB","自己的社团创建成功才走这里");
//            }
//            else{
//                getAssociationStatusHttp(aList);
//                Log.e("JGB","加入的用户来到这里");
//            }
//        }
//
//    }
//
//    //查询当前成员信息
//    public void getAssociationStatusHttp(final List<AssociationInfoBean.RowsBean> aList) {
//        RequestQueue requestQueue = NoHttp.newRequestQueue();
//        Request<String> request = NoHttp.createStringRequest(I.ASSOCIATION_STATUS, RequestMethod.POST);
//        request.add("memberId",AppHelper.getInstance().getUser().getID());
//        request.add("corporationId",aList.get(0).getID());
//        requestQueue.add(0, request, new OnResponseListener<String>() {
//            @Override
//            public void onStart(int what) {
//
//            }
//
//            @Override
//            public void onSucceed(int what, Response<String> response) {
//                String json = response.get();
//                Log.e("JGB", "审核加入是否通过：：" + json);
//                if (json == null) {
//                    return;
//                } else {
//                    MemberInfoBean memberInfoBean = mGson.fromJson(json, MemberInfoBean.class);
//                    List<MemberInfoBean.RowsBean> mLists = memberInfoBean.getRows();
//                    String isexamine = mLists.get(0).getISEXAMINE();
//                    Log.e("JGB", "加入社团的审核状态：：" + isexamine);
//                    if(!isexamine.equals("1")){
//                        appBarLayout.setVisibility(View.GONE);
//                        Toast.makeText(getActivity(),"还未加入通过",Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getActivity(),"加入已通过",Toast.LENGTH_SHORT).show();
//                        //加载当前社团信息并显示
//                        appBarLayout.setVisibility(View.VISIBLE);
//                        league_toolbar.setVisibility(View.GONE);
//                        Glide.with(getActivity()).load(I.BASE_URL+aList.get(0).getPHOTO()).into(association_avatar_img);
//                        Glide.with(getActivity()).load(I.BASE_URL+aList.get(0).getBACKGROUND()).into(association_bg_img);
//                        association_name_tv.setText(aList.get(0).getNAME());
//                        association_time_tv.setText(aList.get(0).getCREATEDATE());
//                        //association_membet_tv.setText(aList.get(0));
//                        association_jianjie_tv.setText(aList.get(0).getINTRODUCTION());
//
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailed(int what, Response<String> response) {
//
//            }
//
//            @Override
//            public void onFinish(int what) {
//
//            }
//        });
//
//    }
//    private View.OnClickListener mOnClickListener=new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()){
//                case R.id.ranking_rl:
//                    startActivity(new Intent(getActivity(), HotRankingActivity.class));
//                   break;
//                case R.id.jcd_release_but:
//                    startActivity(new Intent(getActivity(), LeagueIMActivity.class));
//                    break;
//            }
//        }
//    };
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        getAssociationHttp();
//    }


}




