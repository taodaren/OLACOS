package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.osplay.olacos.R;
import net.osplay.service.entity.MeiZiBean;

import java.util.List;

/**
 * 福利适配器
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "MessageAdapter";
    private Context mContext;
    private LayoutInflater mInflater;
    private List<MeiZiBean.ResultsBean> mBeanList;

    public MessageAdapter(Context context, List<MeiZiBean.ResultsBean> mzBeanList) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mBeanList = mzBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_msg_list, parent, false);
        MessageViewHolder holder = new MessageViewHolder(inflate);
        holder.setClickListener();
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MessageViewHolder) holder).bindData(position);
    }

    @Override
    public int getItemCount() {
        return mBeanList == null ? 0 : mBeanList.size();
    }

    private class MessageViewHolder extends RecyclerView.ViewHolder {
        private View outView;//保存子项最外层布局的实例
        private ImageView imgAvatar;

        private MessageViewHolder(View itemView) {
            super(itemView);
            outView = itemView;
            imgAvatar = (ImageView) itemView.findViewById(R.id.img_msg_avatar);
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
            Log.d(TAG, "bindData: " + mBeanList);
            MeiZiBean.ResultsBean meiZiBean = mBeanList.get(position);
            Glide.with(mContext).load(meiZiBean.getUrl()).into(imgAvatar);
        }
    }

}
