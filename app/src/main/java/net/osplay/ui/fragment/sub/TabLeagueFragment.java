package net.osplay.ui.fragment.sub;


import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.AssociationInfoBean;
import net.osplay.service.entity.JoinBean;
import net.osplay.ui.adapter.base.FragmentAdapter;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.utils.TabUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 社团模块
 */

public class TabLeagueFragment extends BaseFragment {
    private DrawerLayout mDrawerLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> mList = new ArrayList<>();
    private String[] titles = new String[]{"推荐", "社团活动", "社团作品"};
    private FragmentAdapter fragmentAdapter = null;
    private NewestFragment nFragment;
    private CommunityALoginFragment lFragment;
    private CommunityPLoginFragment pFragment;
    private View inflate;
    private AppBarLayout appBarLayout;
    private Toolbar league_toolbar;
    private Gson mGson=new Gson();

    private CircleImageView association_avatar_img;
    private ImageView association_bg_img;
    private TextView association_name_tv;
    private TextView association_time_tv;
    private TextView association_membet_tv;
    private TextView association_jianjie_tv;

   @Override
    public View initView() {
        inflate = View.inflate(getContext(), R.layout.fragment_tab_league, null);
        initDrawerLayout();
        setView();
        setFragment();
        setToolbars();
        getAssociationHttp();

        return inflate;
    }

    private void setFragment() {
        mList.add(nFragment);
        mList.add(lFragment);
        mList.add(pFragment);
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), mContext, mList, titles);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);//设置 TabLayout 和 ViewPager 绑定
    }

    //设置toolbar
    private void setToolbars() {
        league_toolbar.setNavigationIcon(R.drawable.menu_set);
        league_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        league_toolbar.setPopupTheme(R.style.toolbarMenu);
        league_toolbar.inflateMenu(R.menu.popupmenu);
        league_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.special_topic:
                        Log.e("JGB","菜单");
                        break;
                }
                return false;
            }
        });
    }

    //设置
    private void setView() {

        league_toolbar= (Toolbar) inflate.findViewById(R.id.league_toolbar);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        tabLayout = (TabLayout) inflate.findViewById(R.id.league_tabLayout);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(tabLayout, 25, 25);
            }
        });
        viewPager = (ViewPager) inflate.findViewById(R.id.league_viewPager);
        appBarLayout = (AppBarLayout) inflate.findViewById(R.id.league_appbar);
        nFragment = new NewestFragment();//社团推荐
        lFragment=new CommunityALoginFragment();//活动未等陆的提醒
        pFragment=new CommunityPLoginFragment();//作品未的登录的提醒
        association_avatar_img = (CircleImageView) inflate.findViewById(R.id.association_avatar_img);
        association_bg_img = (ImageView) inflate.findViewById(R.id.association_bg_img);
        association_name_tv = (TextView) inflate.findViewById(R.id.association_name_tv);
        association_time_tv = (TextView) inflate.findViewById(R.id.association_time_tv);
        association_membet_tv = (TextView) inflate.findViewById(R.id.association_membet_tv);
        association_jianjie_tv = (TextView) inflate.findViewById(R.id.association_jianjie_tv);

    }


    public void getAssociationHttp() {
        if(!AppHelper.getInstance().isLogined()){
            appBarLayout.setVisibility(View.GONE);
//            league_toolbar.setVisibility(View.VISIBLE);
        }else{
            RequestQueue requestQueue = NoHttp.newRequestQueue();
            Request<String> request = NoHttp.createStringRequest(I.IS_JOIN, RequestMethod.POST);
            request.add("memberId", AppHelper.getInstance().getUser().getID());
            requestQueue.add(0, request, new OnResponseListener<String>() {
                @Override
                public void onStart(int what) {

                }

                @Override
                public void onSucceed(int what, Response<String> response) {
                    String json = response.get();
                    Log.e("JGB", "检查是否创建过社团" + json);
                    if (json == null) {
                        return;
                    } else {
                        formatJsonIsJoin(json);
                    }
                }

                @Override
                public void onFailed(int what, Response<String> response) {

                }

                @Override
                public void onFinish(int what) {

                }
            });
        }


        }


    private void formatJsonIsJoin(String json) {
        JoinBean joinBean = mGson.fromJson(json, JoinBean.class);
        List<JoinBean.RowsBean> rows = joinBean.getRows();
        if (rows.size() == 0) {//没有加入或是创建过社团
            appBarLayout.setVisibility(View.GONE);
        }else if(rows.get(0).getISEXAMINE().equals(0)){
            appBarLayout.setVisibility(View.GONE);
        } else {//已经加入过社团
            Log.e("JGB","已经成功加入过社团");
            appBarLayout.setVisibility(View.VISIBLE);
            league_toolbar.setVisibility(View.GONE);
            getAssociationInfoHttp(rows);//获取当前社团信息
        }
    }

    public void getAssociationInfoHttp(List<JoinBean.RowsBean> rows) {
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        Request<String> request = NoHttp.createStringRequest(I.ASSOCIATION_INFO, RequestMethod.POST);
        request.add("corporationId", rows.get(0).getCORPORATIONID());
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                Log.e("JGB", "获取社团信息：：" + json);
                if (json == null) {
                    return;
                } else {
                    formatAssociationInfoJson(json);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    private void formatAssociationInfoJson(String json) {
        AssociationInfoBean associationInfoBean = mGson.fromJson(json, AssociationInfoBean.class);
        List<AssociationInfoBean.RowsBean> aList = associationInfoBean.getRows();
        Log.e("JGB","解析社团信息："+aList);
        if(aList==null){//没有加入过的社团信息,隐藏社团顶部信息
          return;
        }else{//显示加载社团信息
            Glide.with(getActivity()).load(I.BASE_URL+aList.get(0).getPHOTO()).into(association_avatar_img);
            Glide.with(getActivity()).load(I.BASE_URL+aList.get(0).getBACKGROUND()).into(association_bg_img);
            association_name_tv.setText(aList.get(0).getNAME());
            association_time_tv.setText(aList.get(0).getCREATEDATE());
            //association_membet_tv.setText(aList.get(0));
            association_jianjie_tv.setText(aList.get(0).getINTRODUCTION());
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        getAssociationHttp();
    }
}




