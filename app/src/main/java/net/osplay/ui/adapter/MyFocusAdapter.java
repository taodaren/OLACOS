package net.osplay.ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiangyy.easydialog.CommonDialog;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.app.MyApplication;
import net.osplay.app.SetOnClickListen;
import net.osplay.olacos.R;
import net.osplay.service.entity.MyFocusBean;
import net.osplay.ui.activity.sub.MinePageOtherActivity;
import net.osplay.ui.activity.sub.MinePageSelfActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fanyanmin on 2017/9/30.
 */

public class MyFocusAdapter extends RecyclerView.Adapter<FocusViewHolder> {
    private SetOnClickListen setOnClickListen;

    public void onClick(SetOnClickListen setOnClickListen) {
        this.setOnClickListen = setOnClickListen;
    }
    private Activity context;
    private List<MyFocusBean.RowsBean> rows;

    public MyFocusAdapter(Activity context, List<MyFocusBean.RowsBean> rows) {
        this.context = context;
        this.rows = rows;
    }

    @Override
    public FocusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_focus, parent, false);
        FocusViewHolder viewHolder = new FocusViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FocusViewHolder holder, final int position) {
      //  Glide.with(context).load(I.BASE_URL+rows.get(position).getHEAD_PATH()).error(R.drawable.avatar_default).into(holder.focus_user_icon);
        Glide.with(context).load(I.BASE_URL+rows.get(position).getHEAD_PATH()).into(holder.focus_user_icon);
        holder.focus_nick_name.setText(rows.get(position).getNICK_NAME());
        holder.focus_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rows.get(position).getID().equals(AppHelper.getInstance().getUser().getID())){
                    context.finish();
                }else{
                    Intent intent=new Intent(context, MinePageOtherActivity.class);
                    intent.putExtra("memberId",rows.get(position).getID());
                    context.startActivity(intent);
                }
            }
        });
        holder.item_gz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String followId = rows.get(position).getID();
                setOnClickListen.setOnClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rows.size();
    }
}
class FocusViewHolder extends RecyclerView.ViewHolder{

    public CircleImageView focus_user_icon;
    public TextView focus_nick_name;
    public LinearLayout focus_ll;
    public ImageView item_gz;
    public FocusViewHolder(View itemView) {
        super(itemView);
        focus_user_icon= (CircleImageView) itemView.findViewById(R.id.focus_user_icon);
        focus_nick_name= (TextView) itemView.findViewById(R.id.focus_nick_name);
        focus_ll= (LinearLayout) itemView.findViewById(R.id.focus_ll);
        item_gz=(ImageView) itemView.findViewById(R.id.item_gz);
    }
}
