package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.WordTopicTitleBean;
import net.osplay.ui.activity.sub.PublishPostsActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public class PublishTwoAreaAdapter extends RecyclerView.Adapter<PublishTwoAreaViewHolder> {
    private SetOnClickListen setOnClickListen;

    public void onClick(SetOnClickListen setOnClickListen) {
        this.setOnClickListen = setOnClickListen;
    }
    private Context context;
    private List<WordTopicTitleBean> titleBeanList;

    public PublishTwoAreaAdapter(Context context, List<WordTopicTitleBean> titleBeanList) {
        this.context = context;
        this.titleBeanList = titleBeanList;
    }

    @Override
    public PublishTwoAreaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.publish_area_item, parent, false);
        PublishTwoAreaViewHolder viewHolder = new PublishTwoAreaViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PublishTwoAreaViewHolder holder, final int position) {
        holder.publish_area_item.setText(titleBeanList.get(position).getPART());
        holder.publish_area_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickListen.setOnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleBeanList.size();
    }
}
class PublishTwoAreaViewHolder extends RecyclerView.ViewHolder{

    public TextView publish_area_item;
    public PublishTwoAreaViewHolder(View itemView) {
        super(itemView);
        publish_area_item = (TextView) itemView.findViewById(R.id.publish_area_item);
    }
}
