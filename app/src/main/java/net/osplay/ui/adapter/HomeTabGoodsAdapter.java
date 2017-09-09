package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.osplay.app.MyApplication;
import net.osplay.olacos.R;
import net.osplay.service.entity.VideoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页 → Tab → 商品 Adapter
 */

public class HomeTabGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<VideoBean> mDatas;

    public HomeTabGoodsAdapter(Context context, List<VideoBean> list) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mDatas = new ArrayList<>();
        mDatas.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.item_home_goods_two, parent, false);
        HomeTabGoodsViewHolder holder = new HomeTabGoodsViewHolder(inflate);
        holder.setClickListener();
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HomeTabGoodsViewHolder) holder).setData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    private class HomeTabGoodsViewHolder extends RecyclerView.ViewHolder {
        private View outView;//保存子项最外层布局的实例
        private VideoBean bean;
        private ImageView imgAvatar, imgOne, imgTwo;
        private TextView textTime, textPrice, textInfo, textAddress, textZan, textSpeak, textNick;

        private HomeTabGoodsViewHolder(View itemView) {
            super(itemView);
            outView = itemView;
            imgAvatar = (ImageView) itemView.findViewById(R.id.home_tab_goods_avatar);
            imgOne = (ImageView) itemView.findViewById(R.id.home_tab_goods_one);
            imgTwo = (ImageView) itemView.findViewById(R.id.home_tab_goods_two);
            textTime = (TextView) itemView.findViewById(R.id.home_tab_goods_time);
            textPrice = (TextView) itemView.findViewById(R.id.home_tab_goods_price);
            textInfo = (TextView) itemView.findViewById(R.id.home_tab_goods_info);
            textAddress = (TextView) itemView.findViewById(R.id.home_tab_goods_address);
            textZan = (TextView) itemView.findViewById(R.id.home_tab_goods_zan);
            textSpeak = (TextView) itemView.findViewById(R.id.home_tab_goods_speak);
            textNick = (TextView) itemView.findViewById(R.id.home_tab_goods_nick);
        }

        public void setData(VideoBean bean) {
            if (bean != null) {
                this.bean = bean;
                bindData();
            }
        }

        private void bindData() {
            Glide.with(MyApplication.getContext()).load(bean.getCoverImg()).into(imgAvatar);
            Glide.with(MyApplication.getContext()).load(bean.getCoverImg()).into(imgOne);
            Glide.with(MyApplication.getContext()).load(bean.getCoverImg()).into(imgTwo);
            String strVideoLength = String.valueOf(bean.getVideoLength());
            String strMovieId = String.valueOf(bean.getMovieId());
            String strId = String.valueOf(bean.getId());
            String strAddress = bean.getSummary().substring(0, 2);
            textTime.setText("发布于" + strVideoLength + "小时前");
            textPrice.setText("￥" + strVideoLength);
            textInfo.setText(bean.getSummary());
            textAddress.setText("来自" + strAddress);
            textZan.setText(strId);
            textSpeak.setText(strMovieId);
            textNick.setText(bean.getMovieName());
        }

        private void setClickListener() {
            outView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    VideoBean videoBean = mDatas.get(position);
                    Toast.makeText(mContext, "点击" + videoBean.getMovieName() + "布局", Toast.LENGTH_SHORT).show();
                }
            });

            imgAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    VideoBean videoBean = mDatas.get(position);
                    Toast.makeText(mContext, "跳转到" + videoBean.getMovieName() + "个人界面", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
