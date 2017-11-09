package net.osplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.WordAddBean;
import net.osplay.service.entity.WordTopicBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public class PublishAreaAdapter extends RecyclerView.Adapter<PublishAreaViewHolder> {
    private SetOnClickListen setOnClickListen;

    public void onClick(SetOnClickListen setOnClickListen) {
        this.setOnClickListen = setOnClickListen;
    }
    private Context context;
    private List<WordTopicBean> mTopicList;

    public PublishAreaAdapter(Context context, List<WordTopicBean> mTopicList) {
        this.context = context;
        this.mTopicList = mTopicList;
    }

    @Override
    public PublishAreaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.publish_area_item, parent, false);
        PublishAreaViewHolder viewHolder = new PublishAreaViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PublishAreaViewHolder holder, final int position) {
        Log.e("JGB","适配器中的集合："+mTopicList);
        holder.publish_area_item.setText(mTopicList.get(position).getPART());
        holder.publish_area_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnClickListen.setOnClick(position);
               // holder.publish_area_item.setBackgroundResource(R.drawable.vote_btn_shape2);//选中后的样式切换
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTopicList.size();
    }
}
class PublishAreaViewHolder extends RecyclerView.ViewHolder{

    public TextView publish_area_item;
    public PublishAreaViewHolder(View itemView) {
        super(itemView);
        publish_area_item = (TextView) itemView.findViewById(R.id.publish_area_item);
    }
}
