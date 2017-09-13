package net.osplay.ui.adapter.sub.goods;

import android.content.Context;
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
 * Created by Administrator on 2017/9/13.
 */

public class HotGridAdapter extends BaseAdapter {
    private Context mContextr;
    private List<ResultBeanData.ResultBean.HotInfoBean> hot_info;
    public HotGridAdapter(Context mContextr, List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
        this.mContextr=mContextr;
        this.hot_info=hot_info;
    }

    @Override
    public int getCount() {
        return hot_info.size();
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
        HotViewHolder viewHolder;
        if(convertView==null){
            convertView=View.inflate(mContextr,R.layout.item_hot_grid_view,null);
            viewHolder=new HotViewHolder();
            viewHolder.iv_hot= (ImageView) convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price= (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (HotViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        Glide.with(mContextr).load(Constants.BASE_URl_IMAGE+hot_info.get(position).getFigure()).into(viewHolder.iv_hot);
        viewHolder.tv_name.setText(hot_info.get(position).getName());
        viewHolder.tv_price.setText(hot_info.get(position).getCover_price());
        return convertView;
    }
    static class HotViewHolder{
        ImageView iv_hot;
        TextView tv_name,tv_price;
    }
}
