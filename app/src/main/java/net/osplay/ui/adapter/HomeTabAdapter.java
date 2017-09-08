package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.app.MyApplication;
import net.osplay.olacos.R;
import net.osplay.service.entity.VideoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/9/9.
 * 首页Tab标签下的Adapter
 */

public class HomeTabAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<VideoBean> data;

    public HomeTabAdapter(Context context, List<VideoBean> list) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        data = new ArrayList<>();
        data.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeTabHolder(mLayoutInflater.inflate(R.layout.item_home_tab, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HomeTabHolder) holder).setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    private class HomeTabHolder extends RecyclerView.ViewHolder {
        private VideoBean bean;
        private TextView mTextView;
        private ImageView mImageView;

        public HomeTabHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item_home_tab);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_item_home_tab);
        }

        public void setData(VideoBean bean) {
            if (bean != null) {
                this.bean = bean;
                bindData();
            }
        }

        private void bindData() {
            Glide.with(MyApplication.getContext()).load(bean.getCoverImg()).into(mImageView);
            mTextView.setText(bean.getMovieName());
        }
    }

}
