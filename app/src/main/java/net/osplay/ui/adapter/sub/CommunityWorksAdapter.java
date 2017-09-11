package net.osplay.ui.adapter.sub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.osplay.olacos.R;

/**
 * Created by Administrator on 2017/9/11.
 */

public class CommunityWorksAdapter extends RecyclerView.Adapter<WorksViewHolder> {
    private Context context;

    public CommunityWorksAdapter(Context context) {
        this.context = context;
    }

    @Override
    public WorksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.community_works_item, parent, false);
        WorksViewHolder viewHolder = new WorksViewHolder(inflate);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(WorksViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
class WorksViewHolder extends RecyclerView.ViewHolder{

    public WorksViewHolder(View itemView) {
        super(itemView);
    }
}

