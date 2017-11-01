package net.osplay.app;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/9/7.
 */

public interface SetOnClickListen {
      void
      setOnClick(int position);
      void setOnClick(int position, TextView zanTv, TextView collecTv, TextView commentTv, ImageView zanIv,ImageView cllecIv);
}
