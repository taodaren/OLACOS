package net.osplay.ui.fragment.sub;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.ui.adapter.base.FragmentAdapter;
import net.osplay.ui.fragment.base.BaseFragment;
import net.osplay.utils.FastBlur;
import net.osplay.utils.PublishPopWindow;
import net.osplay.utils.TabUtils;

import java.util.ArrayList;
import java.util.List;

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
    private SocialActivityFragment hFragment;
    private MineFragment mFragment;
    private CommunityFragment cFragment;
    private CommunityWorksFragment wFragment;
    private String lannotated = "olacos";
    private String cAnnotated;
    private String addlannotated = "addolacos";
    private String addcAnnotated;
    private View inflate;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private ImageView league_bg;
    private Button jcd_release_but;
    private ImageView league_menu;
    private PopupMenu popupMenu;
    private Menu menu;

    @Override
    public View initView() {
        inflate = View.inflate(getContext(), R.layout.fragment_tab_league, null);
        initDrawerLayout();
        //获取窗户社团的值
        SharedPreferences preferences1 = getActivity().getSharedPreferences("CreateCommunity", getActivity().MODE_PRIVATE);
        cAnnotated = preferences1.getString("Annotated", "defaultname");
        //获取加入社团的值
        SharedPreferences preferences2 = getActivity().getSharedPreferences("AddCommunity", getActivity().MODE_PRIVATE);
        addcAnnotated = preferences2.getString("addAnnotated", "defaultname");
        setView();
        return inflate;
    }

    private void setView() {
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        tabLayout = (TabLayout) inflate.findViewById(R.id.league_tabLayout);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(tabLayout, 25, 25);
            }
        });
        viewPager = (ViewPager) inflate.findViewById(R.id.league_viewPager);
        nFragment = new NewestFragment(getActivity(), R.layout.fragment_newest);//社团推荐
        hFragment = new SocialActivityFragment(getActivity(), R.layout.fragment_create_community);//社团活动
        cFragment = new CommunityFragment(getActivity(), R.layout.fragment_community);//加入或创建社团之后的社团活动
        mFragment = new MineFragment(getActivity(), R.layout.fragment_mine);//社团作品
        wFragment=new CommunityWorksFragment(getActivity(),R.layout.fragment_community_works);//加入或创建社团之后的社团作品
        mList.add(nFragment);
        //如果创建或者加入社团后将不再显示创建或加入社团界面
        if (lannotated.equals(cAnnotated) | addlannotated.equals(addcAnnotated)) {
            mList.add(cFragment);
        } else {
            mList.add(hFragment);
        }
        if(lannotated.equals(cAnnotated) | addlannotated.equals(addcAnnotated)){
            mList.add(wFragment);
        }else{
            mList.add(mFragment);
        }
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), mContext, mList, titles);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);//设置 TabLayout 和 ViewPager 绑定

        appBarLayout = (AppBarLayout) inflate.findViewById(R.id.league_appbar);
        toolbar = (Toolbar) inflate.findViewById(R.id.toolbar_league);
//        league_bg = (ImageView) inflate.findViewById(R.id.league_bg);

        /**
         * 创建或加入社团成功后才显示的社团主页
         */
        if (lannotated.equals(cAnnotated) | addlannotated.equals(addcAnnotated)) {
            appBarLayout.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.GONE);
            //这里是背景虚化加了背景虚化 使用时不得放在隐藏后的APPbar中 之后会非常卡后期看需求
//            final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                    R.drawable.example02);
//            league_bg.getViewTreeObserver().addOnPreDrawListener(
//                    new ViewTreeObserver.OnPreDrawListener() {
//
//                        @Override
//                        public boolean onPreDraw() {
//                            blur(bitmap, league_bg);
//                            return true;
//                        }
//                    });
        } else {
            appBarLayout.setVisibility(View.GONE);
            toolbar.setVisibility(View.VISIBLE);
        }
        /**
         * menu菜单
         */
        popupMenu = new PopupMenu(getContext(), inflate.findViewById(R.id.league_menu));
        menu = popupMenu.getMenu();
        addMenu();
        setOnMenuItemClickListener();

        /**
         * 发布
         */
        jcd_release_but = (Button) inflate.findViewById(R.id.jcd_release_but);
        jcd_release_but.setOnClickListener(mOnClickListener);
        /**
         * 菜单选项
         */
        league_menu = (ImageView) inflate.findViewById(R.id.league_menu);
        league_menu.setOnClickListener(mOnClickListener);
    }

    private void addMenu() {
//        // 通过代码添加菜单项
        menu.add(Menu.NONE, Menu.FIRST + 0, 0, "成员管理");
        menu.add(Menu.NONE, Menu.FIRST + 1, 1, "申请管理");
        menu.add(Menu.NONE, Menu.FIRST + 2, 2, "积分兑换");
        menu.add(Menu.NONE, Menu.FIRST + 3, 3, "设置");
        // 通过XML文件添加菜单项
//        MenuInflater menuInflater = getActivity().getMenuInflater();
//        menuInflater.inflate(R.menu.popupmenu, menu);
    }

    private void setOnMenuItemClickListener() {
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case Menu.FIRST + 0:
                        Toast.makeText(getActivity(), "成员管理",
                                Toast.LENGTH_LONG).show();
                        break;
                    case Menu.FIRST + 1:
                        Toast.makeText(getActivity(), "dfs",
                                Toast.LENGTH_LONG).show();
                        break;
                    case Menu.FIRST + 2:
                        Toast.makeText(getActivity(), "复制",
                                Toast.LENGTH_LONG).show();
                        break;
                    case Menu.FIRST + 3:
                        Toast.makeText(getActivity(), "粘贴",
                                Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbar(R.id.toolbar_league, R.string.league_name, View.VISIBLE, View.GONE, true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//导航按钮固定 id
            mDrawerLayout.openDrawer(GravityCompat.START);//展示滑动菜单
        }
        return true;
    }

    //图片虚化
    private void blur(Bitmap bkg, View view) {
        long startMs = System.currentTimeMillis();
        float scaleFactor = 2;
        float radius = 1;

        Bitmap overlay = Bitmap.createBitmap(
                (int) (view.getMeasuredWidth() / scaleFactor),
                (int) (view.getMeasuredHeight() / scaleFactor),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft() / scaleFactor, -view.getTop()
                / scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bkg, 0, 0, paint);

        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        view.setBackground(new BitmapDrawable(getResources(), overlay));
        System.out.println(System.currentTimeMillis() - startMs + "ms");
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.jcd_release_but:
                    PublishPopWindow popWindow = new PublishPopWindow(getActivity());
                    popWindow.showMoreWindow(v);
                    break;
                case R.id.league_menu:
                    popupMenu.show();
                    break;
            }
        }
    };


}



