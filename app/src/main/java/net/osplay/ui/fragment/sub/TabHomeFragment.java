package net.osplay.ui.fragment.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

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
import net.osplay.service.entity.ImgTvBean;
import net.osplay.service.entity.WordHotPostsBean;
import net.osplay.service.entity.base.HomeData;
import net.osplay.ui.activity.sub.MessageActivity;
import net.osplay.ui.adapter.TabBatAdapter;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.utils.HomeDataMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页模块
 */

public class TabHomeFragment extends BaseFragment {
    private static final String TAG = "TabHomeFragment";
    private RecyclerView mRvHome;
    private String memberId;


    private WordHotPostsBean hotTopicBean;//
    private List<WordHotPostsBean.DataBean> mDataList;//热帖列表各个大区的数据
    private List<ImgTvBean> egDatas;//模拟数据
    private Gson gson = new Gson();

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_tab_home, null);
        mRvHome = inflate.findViewById(R.id.recycler_home);

        initDrawerLayout();
        if (!AppHelper.getInstance().isLogined()) {//未登录状态
            memberId = "";
        } else {//登录状态
            memberId = AppHelper.getInstance().getUser().getID();//登录用户ID
        }
        return inflate;
    }

    @Override
    public void initData() {
        super.initData();
        RequestQueue requestQueue = NoHttp.newRequestQueue();

        //创建一个字符串类型请求，自定义请求方法。
        Request<String> hotTopicRequest = NoHttp.createStringRequest(I.POSTS_HOT_LIST, RequestMethod.POST);//热帖

        //获取数据请求并解析
        getHotTopicData(requestQueue, hotTopicRequest);

        //模拟数据
        getDatas();
    }

    private void getDatas() {
        egDatas = new ArrayList<>();
        egDatas.add(new ImgTvBean(R.drawable.banner01, "11e22f990c754e1199d3da90e7ca5655"));//生成指南
        egDatas.add(new ImgTvBean(R.drawable.banner02, "87f3954a4a574a03a8c3d33fa9552488"));//十月新番
        egDatas.add(new ImgTvBean(R.drawable.banner03, "d7ee4cce6b794edfaea7cc7c6002f525"));//文明公约
        egDatas.add(new ImgTvBean(R.drawable.banner04, "bd0b0944db404918a7e5d4679d6da436"));//众望所归
        egDatas.add(new ImgTvBean(R.drawable.banner05, "8908700c0bd64dfab8d48ee07603a0b3"));//内测公告
        initRecyclerView();
    }

    private void getHotTopicData(final RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: 首页热帖请求 --> " + json);

                //数据解析
                hotTopicBean = gson.fromJson(json, WordHotPostsBean.class);
                mDataList = hotTopicBean.getData();
                Log.d(TAG, "首页热帖 data 解析成功");
                initRecyclerView();
            }

            @Override
            public void onFailed(int what, Response<String> response) {
            }

            @Override
            public void onFinish(int what) {
            }
        });
    }

    private void initRecyclerView() {
        if (hotTopicBean != null) {
            LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//            GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
            mRvHome.setLayoutManager(manager);
            manager.setAutoMeasureEnabled(true);
            manager.setSmoothScrollbarEnabled(true);
            mRvHome.setHasFixedSize(true);
            mRvHome.setNestedScrollingEnabled(true);

            List<HomeData> list = new ArrayList<>();
            list.add(HomeDataMapper.transformHomeTestData(egDatas, TabBatAdapter.TYPE_BANNER, true));
            list.addAll(HomeDataMapper.transformTopicDatas(mDataList, TabBatAdapter.TYPE_TOPIC, false));
            TabBatAdapter adapter = new TabBatAdapter(getActivity(), list);
            mRvHome.setAdapter(adapter);
        }
    }

    /**
     * 在 onActivityCreated 方法中初始化 Toolbar
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbar(R.id.toolbar_home, R.string.home_name, View.VISIBLE, View.GONE, true);
        requestCodeQRCodePermissions();
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
