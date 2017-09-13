package net.osplay.ui.adapter.sub.goods;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.goods.ResultBeanData;
import net.osplay.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 *秒杀的适配器
 */

public class SeckillAdapter extends RecyclerView.Adapter<SeckillViewHolder>{
    private SetOnClickListen setOnClickListen;
    public void onClick(SetOnClickListen setOnClickListen){
        this.setOnClickListen=setOnClickListen;
    }
    private Context mContext;
    private List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list;
    public SeckillAdapter(Context mContext, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public SeckillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_seckill, parent, false);
        SeckillViewHolder viewHolder = new SeckillViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SeckillViewHolder holder, final int position) {
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE + list.get(position).getFigure()).into(holder.iv_figure);
        holder.tv_cover_price.setText(list.get(position).getCover_price());
        holder.tv_cover_price.setText(list.get(position).getOrigin_price());
        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setOnClickListen != null){
                    setOnClickListen.setOnClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class SeckillViewHolder extends RecyclerView.ViewHolder{
    public ImageView iv_figure;
    public TextView tv_cover_price,tv_origin_price;
    public LinearLayout ll_root;
    public SeckillViewHolder(View itemView) {
        super(itemView);
        iv_figure= (ImageView) itemView.findViewById(R.id.iv_figure);
        tv_cover_price= (TextView) itemView.findViewById(R.id.tv_cover_price);
        tv_origin_price= (TextView) itemView.findViewById(R.id.tv_origin_price);
        ll_root= (LinearLayout) itemView.findViewById(R.id.ll_root);
    }
}