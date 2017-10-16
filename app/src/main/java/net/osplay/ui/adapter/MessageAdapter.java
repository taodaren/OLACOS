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
import net.osplay.service.entity.MemberInfoBean;

import java.util.List;

/**
 * 福利适配器
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    private Context context;
    private List<MemberInfoBean.RowsBean> rows;

    public MessageAdapter(Context context, List<MemberInfoBean.RowsBean> rows) {
        this.context = context;
        this.rows = rows;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_msg_list, parent, false);
        MessageViewHolder viewHolder = new MessageViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return rows.size();
    }
}
class MessageViewHolder extends RecyclerView.ViewHolder{
    public MessageViewHolder(View itemView) {
        super(itemView);
    }
}
