package net.osplay.ui.adapter.sub;

import android.content.Context;
import android.widget.TextView;

import net.osplay.olacos.R;
import net.osplay.ui.adapter.base.BaseRecyclerViewAdapter;
import net.osplay.ui.adapter.base.BaseViewHolder;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 * eevent社团详情页的评论
 */

public class EventCommentAdapter extends BaseRecyclerViewAdapter<String> {
    private TextView tv;
    public EventCommentAdapter(Context context, List<String> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, String data, int position) {
        tv=holder.getView(R.id.comm_tv);
        tv.setText(data);
    }
}
