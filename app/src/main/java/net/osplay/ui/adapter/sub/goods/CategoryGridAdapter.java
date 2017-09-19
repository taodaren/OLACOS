package net.osplay.ui.adapter.sub.goods;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.olacos.R;
import net.osplay.service.entity.goods.ResultBeanData;
import net.osplay.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 * 二手分类条目适配器
 */

public class CategoryGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.ChannelInfoBean> data;

    public CategoryGridAdapter(Context mContext, List<ResultBeanData.ResultBean.ChannelInfoBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryViewHolder viewHolder;
        if(convertView==null){
            convertView=View.inflate(mContext,R.layout.category_item,null);
            viewHolder=new CategoryViewHolder();
            viewHolder.iv_icon= (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (CategoryViewHolder) convertView.getTag();
        }

        //根据位置得到对应的数据
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE+data.get(position).getImage()).into(viewHolder.iv_icon);
        viewHolder.tv_title.setText(data.get(position).getChannel_name());
        return convertView;
    }
    static class CategoryViewHolder{
        private ImageView iv_icon;
        private TextView tv_title;

    }
}

