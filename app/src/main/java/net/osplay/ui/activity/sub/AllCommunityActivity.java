package net.osplay.ui.activity.sub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import net.osplay.app.FilterListener;
import net.osplay.olacos.R;
import net.osplay.service.entity.AllCommunityBean;
import net.osplay.ui.adapter.sub.AllCommunityAdapter;

import java.util.ArrayList;
import java.util.List;


public class AllCommunityActivity extends Activity {
    private EditText et_ss;
    private GridView lsv_ss;
    private List<AllCommunityBean> list = new ArrayList<AllCommunityBean>();
    boolean isFilter;
    private AllCommunityAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_community_activity);
        setViews();// 控件初始化
        setData();// 给listView设置adapter
        setListeners();// 设置监听
    }

    /**
     * 数据初始化并设置adapter
     */
    private void setData() {
        initData();// 初始化数据
        // 这里创建adapter的时候，构造方法参数传了一个接口对象，这很关键，回调接口中的方法来实现对过滤后的数据的获取
        adapter = new AllCommunityAdapter(list, this, new FilterListener() {
            // 回调方法获取过滤后的数据
            public void getFilterData(List<AllCommunityBean> list) {
                // 这里可以拿到过滤后数据，所以在这里可以对搜索后的数据进行操作
                Log.e("TAG", "接口回调成功");
                Log.e("TAG", list.toString());
                setItemClick(list);
            }
        });
        lsv_ss.setAdapter(adapter);
    }

    /**
     * 给listView添加item的单击事件
     * @param filter_lists  过滤后的数据集
     */
    protected void setItemClick(final List<AllCommunityBean> filter_lists) {
        lsv_ss.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                startActivity(new Intent(AllCommunityActivity.this, JoinCommunityDetailsActivity.class));
                // 点击对应的item时，弹出toast提示所点击的内容
//                Toast.makeText(AllCommunityActivity.this, filter_lists.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 简单的list集合添加一些测试数据
     */
    private void initData() {
        list.add(new AllCommunityBean(R.drawable.all1,"cp交友"));
        list.add(new AllCommunityBean(R.drawable.all2,"宅物"));
        list.add(new AllCommunityBean(R.drawable.all3,"影视"));
        list.add(new AllCommunityBean(R.drawable.all4,"手工"));
        list.add(new AllCommunityBean(R.drawable.all5,"游戏"));
        list.add(new AllCommunityBean(R.drawable.all6,"古风"));
        list.add(new AllCommunityBean(R.drawable.all7,"特影"));
        list.add(new AllCommunityBean(R.drawable.all1,"宅舞"));
        list.add(new AllCommunityBean(R.drawable.all2,"美漫"));
        list.add(new AllCommunityBean(R.drawable.all3,"MAD"));
        list.add(new AllCommunityBean(R.drawable.all4,"MMD"));
        list.add(new AllCommunityBean(R.drawable.all5,"鬼畜"));
        list.add(new AllCommunityBean(R.drawable.all6,"百合"));
        list.add(new AllCommunityBean(R.drawable.all7,"萌宠"));
        list.add(new AllCommunityBean(R.drawable.all1,"水族馆"));
        list.add(new AllCommunityBean(R.drawable.all2,"女装乐园"));
        list.add(new AllCommunityBean(R.drawable.all3,"11区"));
        list.add(new AllCommunityBean(R.drawable.all4,"青花"));
        list.add(new AllCommunityBean(R.drawable.all5,"12区"));
        list.add(new AllCommunityBean(R.drawable.all6,"cp交友"));
        list.add(new AllCommunityBean(R.drawable.all7,"5区"));
        list.add(new AllCommunityBean(R.drawable.all1,"cp交友"));
        list.add(new AllCommunityBean(R.drawable.all2,"宅物"));
        list.add(new AllCommunityBean(R.drawable.all3,"影视"));
        list.add(new AllCommunityBean(R.drawable.all4,"手工"));
        list.add(new AllCommunityBean(R.drawable.all5,"游戏"));
        list.add(new AllCommunityBean(R.drawable.all6,"古风"));
        list.add(new AllCommunityBean(R.drawable.all7,"特影"));
        list.add(new AllCommunityBean(R.drawable.all1,"宅舞"));
        list.add(new AllCommunityBean(R.drawable.all2,"美漫"));
        list.add(new AllCommunityBean(R.drawable.all3,"MAD"));
        list.add(new AllCommunityBean(R.drawable.all4,"MMD"));
        list.add(new AllCommunityBean(R.drawable.all5,"鬼畜"));
        list.add(new AllCommunityBean(R.drawable.all6,"百合"));
        list.add(new AllCommunityBean(R.drawable.all7,"萌宠"));
        list.add(new AllCommunityBean(R.drawable.all1,"水族馆"));
        list.add(new AllCommunityBean(R.drawable.all2,"女装乐园"));
        list.add(new AllCommunityBean(R.drawable.all3,"11区"));
        list.add(new AllCommunityBean(R.drawable.all4,"青花"));
        list.add(new AllCommunityBean(R.drawable.all5,"12区"));
        list.add(new AllCommunityBean(R.drawable.all6,"cp交友"));
        list.add(new AllCommunityBean(R.drawable.all7,"5区"));

    }

    private void setListeners() {
        // 没有进行搜索的时候，也要添加对listView的item单击监听
        setItemClick(list);

        /**
         * 对编辑框添加文本改变监听，搜索的具体功能在这里实现
         * 很简单，文本该变的时候进行搜索。关键方法是重写的onTextChanged（）方法。
         */
        et_ss.addTextChangedListener(new TextWatcher() {

            /**
             *
             * 编辑框内容改变的时候会执行该方法
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 如果adapter不为空的话就根据编辑框中的内容来过滤数据
                if(adapter != null){
                    adapter.getFilter().filter(s);
                }else{
                    Toast.makeText(AllCommunityActivity.this,"fdsf",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
    }

    /**
     * 控件初始化
     */
    private void setViews() {
        et_ss = (EditText) findViewById(R.id.et_ss);// EditText控件
        lsv_ss = (GridView) findViewById(R.id.lsv_ss);// ListView控件
    }

}