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
import com.makeramen.roundedimageview.RoundedImageView;

import net.osplay.app.MyApplication;
import net.osplay.olacos.R;
import net.osplay.service.entity.VideoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 专栏详情适配器
 */

public class DetailsColumnAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity mContext;
    private LayoutInflater mInflater;
    private List<VideoBean> mDtlColumnList;

    public DetailsColumnAdapter(Activity context, List<VideoBean> datas) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDtlColumnList = new ArrayList<>();
        this.mDtlColumnList.addAll(datas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DtlColumnHolder holder = new DtlColumnHolder(mInflater.inflate(R.layout.item_column_details, parent, false));
        holder.setClickListener();
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DtlColumnHolder) holder).bindData(mDtlColumnList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDtlColumnList == null ? 0 : mDtlColumnList.size();
    }

    private class DtlColumnHolder extends RecyclerView.ViewHolder {
        private View outView;//保存子项最外层布局的实例
        private RoundedImageView imgOne, imgTwo, imgThree;
        private TextView tvTitle;

        private DtlColumnHolder(View itemView) {
            super(itemView);
            outView = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tv_column_details_title);
            imgThree = (RoundedImageView) itemView.findViewById(R.id.img_column_details_three);
        }

        public void bindData(VideoBean videoBean) {
            Glide.with(MyApplication.getContext()).load(videoBean.getCoverImg()).into(imgThree);
            tvTitle.setText(videoBean.getVideoTitle());
        }

        public void setClickListener() {
            outView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "跳转到对应 WebView", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
