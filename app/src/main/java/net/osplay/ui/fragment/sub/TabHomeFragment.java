package net.osplay.ui.fragment.sub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.I;
import net.osplay.data.bean.HomeData;
import net.osplay.olacos.R;
import net.osplay.service.entity.HomeBannerBean;
import net.osplay.service.entity.VideoBean;
import net.osplay.service.entity.VideoMapperBean;
import net.osplay.ui.adapter.TabHomeAdapter;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.utils.HomeDataMapper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页模块
 */

public class TabHomeFragment extends BaseFragment {
    private static final String TAG = "TabHomeFragment";
    private DrawerLayout mDrawerLayout;//侧滑菜单
    private RecyclerView mRvHome;

    private List<HomeBannerBean> bannerBeanList;
    private List<HomeBannerBean> recommendBeanList;
    private List<String> tabList;
    private List<VideoBean> newGoodsList;
    private List<VideoBean> hotTopicList;

    private Gson gson = new Gson();

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_tab_home, null);
        //注意 getActivity()若使用 view 会报错，此处有大坑
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        mRvHome = (RecyclerView) inflate.findViewById(R.id.recycler_home);

        initDrawerLayout();
        return inflate;
    }

    @Override
    public void initData() {
        super.initData();
        RequestQueue requestQueue = NoHttp.newRequestQueue();

        //创建一个字符串类型请求，自定义请求方法。
        Request<String> bannerRequest = NoHttp.createStringRequest(I.HOME_BANNER, RequestMethod.GET);//Banner 数据
        Request<String> recommendRequest = NoHttp.createStringRequest(I.HOME_BANNER, RequestMethod.GET);//推荐数据
        Request<String> newGoodsRequest = NoHttp.createStringRequest(I.HOME_DETAIL, RequestMethod.GET);//最新商品
        Request<String> hotTopicRequest = NoHttp.createStringRequest(I.HOME_DETAIL, RequestMethod.GET);//热帖

        //获取数据请求并解析
        getBannerData(requestQueue, bannerRequest);
        getRecommendData(requestQueue, recommendRequest);
        getTabData();
        getNewGoodsData(requestQueue, newGoodsRequest);
        getHotTopicData(requestQueue, hotTopicRequest);
    }

    private void getBannerData(final RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: bannerRequest====================" + json);

                //数据解析（集合）
                Type type = new TypeToken<List<HomeBannerBean>>() {
                }.getType();
                bannerBeanList = gson.fromJson(json, type);
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

    private void getRecommendData(final RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: recommendRequest====================" + json);

                //数据解析（集合）
                Type type = new TypeToken<List<HomeBannerBean>>() {
                }.getType();
                recommendBeanList = gson.fromJson(json, type);
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

    private void getTabData() {
        tabList = new ArrayList<>();
        tabList.add(getString(R.string.text_new_goods));
        tabList.add(getString(R.string.text_hot_topic));
        initRecyclerView();
    }

    private void getNewGoodsData(final RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: newGoodsRequest====================" + json);

                //数据解析（集合）
                Type type = new TypeToken<VideoMapperBean>() {
                }.getType();
                VideoMapperBean bean = gson.fromJson(json, type);
                List<VideoBean> temp = bean.getTrailers();
                newGoodsList = new ArrayList<>();
                for (int i = 10; i < 20; i++) {
                    newGoodsList.add(temp.get(i));
                }
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

    private void getHotTopicData(final RequestQueue requestQueue, Request<String> request) {
        requestQueue.add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();//得到请求数据
                Log.d(TAG, "onSucceed: hotTopicRequest====================" + json);

                //数据解析（集合）
                Type type = new TypeToken<VideoMapperBean>() {
                }.getType();
                VideoMapperBean bean = gson.fromJson(json, type);
                List<VideoBean> temp = bean.getTrailers();
                hotTopicList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    hotTopicList.add(temp.get(i));
                }
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
        if (checkoutData()) {
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mRvHome.setLayoutManager(mLayoutManager);
            mRvHome.setHasFixedSize(true);

            List<HomeData> list = new ArrayList<>();
            list.add(HomeDataMapper.transformBannerData(bannerBeanList, TabHomeAdapter.TYPE_BANNER, false));
            list.add(HomeDataMapper.transformRecommendData(recommendBeanList, TabHomeAdapter.TYPE_CATE, false));
            list.add(HomeDataMapper.transformTabData(tabList, TabHomeAdapter.TYPE_TABLE, false));

            TabHomeAdapter mHomeAdapter = new TabHomeAdapter(getActivity(), list, newGoodsList, hotTopicList);
            mRvHome.setAdapter(mHomeAdapter);
        }
    }

    private boolean checkoutData() {
        return bannerBeanList != null
                && recommendBeanList != null
                && tabList != null
                && newGoodsList != null
                && hotTopicList != null;
    }

    /**
     * 在 onActivityCreated 方法中初始化 Toolbar
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbar(R.id.toolbar_home, R.string.home_name, View.GONE, View.VISIBLE, true);
        requestCodeQRCodePermissions();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //显示菜单
        inflater.inflate(R.menu.menu_toolbar, menu);
        //显示需要菜单项，隐藏多余菜单项
        menu.findItem(R.id.menu_code).setVisible(false);
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
                Toast.makeText(mContext, "menu_msg", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

}
