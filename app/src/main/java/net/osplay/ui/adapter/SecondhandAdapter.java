package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.olacos.R;
import net.osplay.service.entity.TestSecondBean;

import java.util.List;

/**
 * 二手交易适配器
 */

public class SecondhandAdapter extends RecyclerView.Adapter<SecondhandAdapter.ViewHolder> {
    private Context mContext;
    private List<TestSecondBean> secondhandBeanList;

    public SecondhandAdapter(List<TestSecondBean> secondhandBeanList) {
        this.secondhandBeanList = secondhandBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_secondhand, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TestSecondBean secondhandBean = secondhandBeanList.get(position);
        holder.textTitle.setText(secondhandBean.getGoodsBrief());
        holder.textPrice.setText(secondhandBean.getShopPrice());
        //使用Glide加载图片
        Glide.with(mContext).load(secondhandBean.getGoodsImg()).into(holder.imgSecondhand);
    }

    @Override
    public int getItemCount() {
        return secondhandBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgSecondhand;
        TextView textPrice, textTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imgSecondhand = (ImageView) itemView.findViewById(R.id.img_secondhand);
            textTitle = (TextView) itemView.findViewById(R.id.text_title_secondhand);
            textPrice = (TextView) itemView.findViewById(R.id.text_price_secondhand);
        }
    }
}
