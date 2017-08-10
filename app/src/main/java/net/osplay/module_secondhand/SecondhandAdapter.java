package net.osplay.module_secondhand;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.osplay.olacos.R;

/**
 * Created by Administrator on 2017/8/10.
 */

public class SecondhandAdapter {

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgSecondhand;
        TextView textPrice, textTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imgSecondhand = (ImageView) itemView.findViewById(R.id.img_secondhand);
//            textTitle = itemView.findViewById(R.id.)
        }
    }
}
