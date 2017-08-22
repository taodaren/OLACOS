package net.osplay.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.service.entity.TestSecondBean;
import net.osplay.service.presenter.TestSecondPresenter;
import net.osplay.service.view.TextSecondView;
import net.osplay.ui.adapter.SecondhandAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 二手交易模块
 */

public class SecondhandFragment extends BaseFragment {
    //侧滑菜单
    private DrawerLayout mDrawerLayout;
    //下拉刷新
    private SwipeRefreshLayout swipeRefresh;

    //测试使用数据，有后台后可删除使用网络数据
//    private SecondhandBean[] secondhands = {
//            new SecondhandBean(R.drawable.example01, "￥356.00", "【二次元】北京奥世普雷科技有限公司怎么全是帅哥美女"),
//            new SecondhandBean(R.drawable.example02, "￥158.00", "【游戏人生剧场版】 玩家夫妇挑战了世界【MAD】"),
//            new SecondhandBean(R.drawable.example03, "￥286.00", "【花泽香菜/物语系列】千石抚子的黑与白~~"),
//            new SecondhandBean(R.drawable.example04, "￥9527.00", "【2017拜年祭单品】漫画家桐人—我只想讲一个关于梦想的故事"),
//            new SecondhandBean(R.drawable.example05, "￥945.00", "【全职高手】叶修中心向mad 这一路走来"),
//            new SecondhandBean(R.drawable.example06, "￥1725.00", "[CLANNAD/MAD]这段人生已走过十年，而我们将用更多十年走完各自的人生"),
//            new SecondhandBean(R.drawable.example07, "￥365.00", "【全职高手】1025荣耀学院"),
//            new SecondhandBean(R.drawable.example08, "￥131.00", "【MAD】 聖なるかな 永遠になるかな CompleteVer"),
//            new SecondhandBean(R.drawable.example09, "￥186.00", "【ToLove】我的后宫不可能都是狐狸精！"),
//            new SecondhandBean(R.drawable.example10, "￥1121.00", "【腐向】一言不合就发车 奉上你们想要的《K》")
//    };

    //    private List<SecondhandBean> beanList = new ArrayList<>();
    private List<TestSecondBean> beanList = new ArrayList<>();
    private SecondhandAdapter adapter;
    private TestSecondPresenter testSecondPresenter = new TestSecondPresenter(getContext());
    private TextSecondView testSecondView;

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_secondhand, null);
        //注意 getActivity() 若使用 view 会报错，此处有大坑
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        testSecondPresenter.onCreate();
        testSecondPresenter.attachView(testSecondView);

        setRecyclerView(inflate);
        setSwipeRefresh(inflate);
        return inflate;
    }

    private void setRecyclerView(View inflate) {
        initSecondhands();
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_secondhand);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SecondhandAdapter(beanList);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 设置下拉刷新
     *
     * @param inflate
     */
    private void setSwipeRefresh(View inflate) {
        swipeRefresh = (SwipeRefreshLayout) inflate.findViewById(R.id.swipe_refresh_secondhand);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //在此处理刷新逻辑
                refreshSecondhand();
            }
        });
    }

    private void refreshSecondhand() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //为了本地测试效果
                    Thread.sleep(1300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //将线程切回主线程
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //重新生成数据
                        initSecondhands();
                        //通知数据发生变化
                        adapter.notifyDataSetChanged();
                        //刷新事件结束，并隐藏刷新进度条
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initSecondhands() {
        //清空数据
//        beanList.clear();
        //模拟数据
//        for (int i = 0; i < 50; i++) {
//            Random random = new Random();
//            int index = random.nextInt(secondhands.length);
//            beanList.add(secondhands[index]);
//        }
        testSecondPresenter.getTestSecond(0, 1, 5);
        testSecondView = new TextSecondView() {
            @Override
            public void onSuccess(List<TestSecondBean> listTestSecond) {
                Log.e("taodaren", "onSuccess: " + listTestSecond.toString());
            }

            @Override
            public void onError(String result) {
                Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
            }
        };
    }

    /**
     * 在 onActivityCreated 方法中初始化 Toolbar
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbar(R.id.toolbar_home, R.string.secondhand_name, View.GONE, View.GONE, true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //显示菜单
        inflater.inflate(R.menu.menu_toolbar, menu);
        //显示需要菜单项，隐藏多余菜单项
        menu.findItem(R.id.menu_set).setVisible(false);
        menu.findItem(R.id.menu_register).setVisible(false);
        menu.findItem(R.id.menu_code).setVisible(false);
        menu.findItem(R.id.menu_msg).setVisible(false);
        menu.findItem(R.id.menu_category).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//导航按钮固定 id
            //展示滑动菜单
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        if (item.getItemId() == R.id.menu_category) {
            Toast.makeText(mContext, "menu_category", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
