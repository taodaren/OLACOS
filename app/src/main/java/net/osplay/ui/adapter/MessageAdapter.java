package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.osplay.app.I;
import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.MeiZiBean;
import net.osplay.service.entity.MemberInfoBean;
import net.osplay.service.entity.MessageBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 消息
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    private SetOnClickListen setOnClickListen;
    private SetOnClickListen setOnClickListen1;

    public void onClick(SetOnClickListen setOnClickListen,SetOnClickListen setOnClickListen1) {
        this.setOnClickListen = setOnClickListen;
        this.setOnClickListen1=setOnClickListen1;
    }


    private Context context;
    private List<MessageBean.RowsBean> rows;

    public MessageAdapter(Context context, List<MessageBean.RowsBean> rows) {
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
    public void onBindViewHolder(final MessageViewHolder holder, final int position) {
        Glide.with(context).load(I.BASE_URL+rows.get(position).getHEAD_PATH()).into(holder.img_msg_avatar);
        holder.tv_list_item.setText(rows.get(position).getNICK_NAME());
        holder.reason_tv.setText(rows.get(position).getREASON());
        holder.message_item_agree_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.message_item_agree_tv.setText("已同意");
                setOnClickListen.setOnClick(position);
            }
        });
        holder.message_item_disagree_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickListen1.setOnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }
}
class MessageViewHolder extends RecyclerView.ViewHolder{
    public CircleImageView img_msg_avatar;
    public TextView tv_list_item,reason_tv;
    public Button message_item_agree_tv,message_item_disagree_tv;
    public MessageViewHolder(View itemView) {
        super(itemView);
        img_msg_avatar = (CircleImageView) itemView.findViewById(R.id.img_msg_avatar);
        tv_list_item = (TextView) itemView.findViewById(R.id.tv_list_item);
        reason_tv = (TextView) itemView.findViewById(R.id.reason_tv);
        message_item_agree_tv = (Button) itemView.findViewById(R.id.message_item_agree_tv);
        message_item_disagree_tv = (Button) itemView.findViewById(R.id.message_item_disagree_tv);
    }
}
