package net.osplay.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.osplay.olacos.R;
import net.osplay.service.entity.MeiZiBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 帖子详情内容适配器
 */

public class DetailsPostsContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "DetailsPostsContentAdapter";
    private Activity mContext;
    private LayoutInflater mInflater;
    private List<MeiZiBean.ResultsBean> mBeanList;

    public DetailsPostsContentAdapter(Activity context, List<MeiZiBean.ResultsBean> contentList) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mBeanList = new ArrayList<>();
        this.mBeanList.addAll(contentList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_img_tv_2, parent, false);
        PostsContentHolder holder = new PostsContentHolder(inflate);
        holder.setClickListener();
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PostsContentHolder) holder).bindData(position);
    }

    @Override
    public int getItemCount() {
        return mBeanList == null ? 0 : mBeanList.size();
    }

    private class PostsContentHolder extends RecyclerView.ViewHolder {
        private View outView;//保存子项最外层布局的实例
        private ImageView imgContent;
        private TextView tvContent;

        private PostsContentHolder(View itemView) {
            super(itemView);
            outView = itemView;
            imgContent = (ImageView) itemView.findViewById(R.id.img_dp);
            tvContent = (TextView) itemView.findViewById(R.id.tv_dp);
        }

        public void setClickListener() {
            outView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "outView", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void bindData(int position) {
            MeiZiBean.ResultsBean meiZiBean = mBeanList.get(position);
            Glide.with(mContext).load(meiZiBean.getUrl()).into(imgContent);
            tvContent.setText(meiZiBean.getUrl());
        }
    }

}
