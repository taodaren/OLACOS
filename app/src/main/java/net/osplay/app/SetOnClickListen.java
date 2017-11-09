package net.osplay.app;

import android.widget.ImageView;
import android.widget.TextView;

public interface SetOnClickListen {
    void setOnClick(int position);

    void setOnClick(int position, TextView zanTv, TextView collecTv, TextView commentTv, ImageView zanIv, ImageView cllecIv);
}
