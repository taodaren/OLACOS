package net.osplay.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.osplay.olacos.R;
import net.osplay.service.entity.TestBean;
import net.osplay.service.presenter.TestPresenter;
import net.osplay.service.view.TextDataView;

import java.util.List;


/**
 * 社团模块
 */

public class LeagueFragment extends BaseFragment {
    //侧滑菜单
    private DrawerLayout mDrawerLayout;

    private TextView textShow;
    private TestPresenter testPresenter = new TestPresenter(getContext());
    private TextDataView textDataView = new TextDataView() {
        @Override
        public void onSuccess(List<TestBean> testBean) {
            textShow.setText(testBean.toString());
        }

        @Override
        public void onError(String result) {
            Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public View initView() {
        View inflate = View.inflate(getContext(), R.layout.fragment_league, null);
        //注意 getActivity() 若使用 view 会报错，此处有大坑
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

        textShow = (TextView) inflate.findViewById(R.id.textData);
        testPresenter.onCreate();
        testPresenter.attachView(textDataView);
        inflate.findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPresenter.getTextData(0, 1, 5);
            }
        });
        return inflate;
    }

    /**
     * 在 onActivityCreated 方法中初始化 Toolbar
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolbar(R.id.toolbar_home, R.string.league_name, View.VISIBLE, View.GONE, true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//导航按钮固定 id
            //展示滑动菜单
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }
}
