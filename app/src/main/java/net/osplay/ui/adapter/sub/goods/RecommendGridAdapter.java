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

public class RecommendGridAdapter extends BaseAdapter {
    private Context mContext;
    private  List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info;
    public RecommendGridAdapter(Context mContext, List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
        this.mContext=mContext;
        this.recommend_info=recommend_info;
    }

    @Override
    public int getCount() {
        return recommend_info.size();
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
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=View.inflate(mContext, R.layout.item_recommend_grid_view,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_recommend= (ImageView) convertView.findViewById(R.id.iv_recommend);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price= (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE+recommend_info.get(position).getFigure()).into(viewHolder.iv_recommend);
        viewHolder.tv_name.setText(recommend_info.get(position).getName());
        viewHolder.tv_price.setText(recommend_info.get(position).getCover_price());
        return convertView;
    }

    static class ViewHolder{
        ImageView iv_recommend;
        TextView tv_name,tv_price;
    }
}
