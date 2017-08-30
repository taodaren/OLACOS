package net.osplay.ui.activity.sub;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ReturnGoodsActivity extends BaseActivity {
    private ExpandableListView expandableListView;
    private List<String> groupList;
    private List<String> itemList;
    private ReturnGoodsExListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_goods);

        setToolbar("退货处理", View.VISIBLE);
        initView();
    }

    private void initView() {
        expandableListView = (ExpandableListView) findViewById(R.id.expand_return_goods);
        groupList = new ArrayList<>();
        itemList = new ArrayList<>();
        groupList.add("退货");
        itemList.add("退货");
//        itemList.add("换货");
        expandableListView.setGroupIndicator(null);

        //监听组点击
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;
            }
        });

        //监听每个分组里子控件的点击事件
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                return false;
            }
        });

        adapter = new ReturnGoodsExListAdapter(this);
        expandableListView.setAdapter(adapter);
    }


    /**
     * 退货申请服务 adapter
     */
    class ReturnGoodsExListAdapter extends BaseExpandableListAdapter {
        private Context context;

        public ReturnGoodsExListAdapter(Context context) {
            this.context = context;
        }

        /**
         * 获取组的个数
         */
        @Override
        public int getGroupCount() {
            return groupList.size();
        }

        /**
         * 获取指定组中的子元素个数
         */
        @Override
        public int getChildrenCount(int groupPosition) {
            return itemList.get(groupPosition).length();
        }

        /**
         * 获取指定组中的数据
         */
        @Override
        public Object getGroup(int groupPosition) {
            return groupList.get(groupPosition);
        }

        /**
         * 获取指定组中的指定子元素数据
         */
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return itemList.get(childPosition);
        }

        /**
         * 获取指定组的ID，这个组ID必须是唯一的
         */
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        /**
         * 获取指定组中的指定子元素ID
         */
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        /**
         * 组和子元素是否持有稳定的ID,也就是底层数据的改变不会影响到它们
         */
        @Override
        public boolean hasStableIds() {
            return true;
        }

        /**
         * 获取显示指定组的视图对象
         *
         * @param groupPosition 组位置
         * @param isExpanded    该组是展开状态还是伸缩状态
         * @param convertView   重用已有的视图对象
         * @param parent        返回的视图对象始终依附于的视图组
         * @return
         */
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupHolder groupHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.expendlist_group_return_goods, null);
                groupHolder = new GroupHolder();
                groupHolder.textGroup = (TextView) convertView.findViewById(R.id.text_ex_lv_group);
                convertView.setTag(groupHolder);
            } else {
                groupHolder = (GroupHolder) convertView.getTag();
            }
            groupHolder.textGroup.setText(groupList.get(groupPosition));
            return convertView;
        }

        /**
         * 获取一个视图对象，显示指定组中的指定子元素数据
         *
         * @param groupPosition 组位置
         * @param childPosition 子元素位置
         * @param isLastChild   子元素是否处于组中的最后一个
         * @param convertView   重用已有的视图(View)对象
         * @param parent        返回的视图(View)对象始终依附于的视图组
         * @return
         */
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ItemHolder itemHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.expendlist_item_return_goods, null);
                itemHolder = new ItemHolder();
                itemHolder.textItem = (TextView) convertView.findViewById(R.id.text_th);
                convertView.setTag(itemHolder);
            } else {
                itemHolder = (ItemHolder) convertView.getTag();
            }
            itemHolder.textItem.setText(itemList.get(childPosition));
            return convertView;
        }

        /**
         * 是否选中指定位置上的子元素
         */
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    class GroupHolder {
        TextView textGroup;
    }

    class ItemHolder {
        TextView textItem;
    }

}
