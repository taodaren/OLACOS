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

public class HomeTabPostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<VideoBean> mDatas;

    public HomeTabPostsAdapter(Context context, List<VideoBean> list) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mDatas = new ArrayList<>();
        mDatas.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.item_home_posts_three, parent, false);
        HomeTabPostsViewHolder holder = new HomeTabPostsViewHolder(inflate);
        holder.setClickListener();
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HomeTabPostsViewHolder) holder).setData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    private class HomeTabPostsViewHolder extends RecyclerView.ViewHolder {
        private View outView;//保存子项最外层布局的实例
        private VideoBean bean;
        private ImageView imgAvatar, imgOne, imgTwo, imgThree;
        private TextView textTime, textInfo, textAddress, textCollect, textZan, textSpeak, textNick;

        private HomeTabPostsViewHolder(View itemView) {
            super(itemView);
            outView = itemView;
            imgAvatar = (ImageView) itemView.findViewById(R.id.home_tab_posts_avatar);
            imgOne = (ImageView) itemView.findViewById(R.id.home_tab_posts_one);
            imgTwo = (ImageView) itemView.findViewById(R.id.home_tab_posts_two);
            imgThree = (ImageView) itemView.findViewById(R.id.home_tab_posts_three);
            textTime = (TextView) itemView.findViewById(R.id.home_tab_posts_time);
            textInfo = (TextView) itemView.findViewById(R.id.home_tab_posts_info);
            textAddress = (TextView) itemView.findViewById(R.id.home_tab_posts_address);
            textCollect = (TextView) itemView.findViewById(R.id.home_tab_posts_collect);
            textZan = (TextView) itemView.findViewById(R.id.home_tab_posts_zan);
            textSpeak = (TextView) itemView.findViewById(R.id.home_tab_posts_speak);
            textNick = (TextView) itemView.findViewById(R.id.home_tab_posts_nick);
        }

        public void setData(VideoBean bean) {
            if (bean != null) {
                this.bean = bean;
                bindData();
            }
        }

        private void bindData() {
            Glide.with(MyApplication.getContext()).load(bean.getCoverImg()).into(imgAvatar);
//            Glide.with(MyApplication.getContext()).load(bean.getCoverImg()).into(imgOne);
            Glide.with(MyApplication.getContext()).load(bean.getCoverImg()).into(imgTwo);
//            Glide.with(MyApplication.getContext()).load(bean.getCoverImg()).into(imgThree);
            String strVideoLength = String.valueOf(bean.getVideoLength());
            String strMovieId = String.valueOf(bean.getMovieId());
            String strId = String.valueOf(bean.getId());
            String strAddress = bean.getSummary().substring(0, 2);
            textTime.setText("发布于" + strVideoLength + "小时前");
            textInfo.setText(bean.getSummary());
            textAddress.setText("来自" + strAddress);
            textCollect.setText(strVideoLength);
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
