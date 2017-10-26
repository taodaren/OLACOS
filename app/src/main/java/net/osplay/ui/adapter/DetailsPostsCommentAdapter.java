package net.osplay.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.app.I;
import net.osplay.olacos.R;
import net.osplay.service.entity.WordDetailsCommentBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 帖子详情评论适配器
 */

public class DetailsPostsCommentAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "DetailsPostsCommentAdapter";
    private Context mContext;
    private LayoutInflater mInflater;
    private WordDetailsCommentBean mCommentBean;
    private AlertDialog mCommentEditDialog;
    private int mCurrentGroupPosition = 0;

    public DetailsPostsCommentAdapter(Context context, WordDetailsCommentBean mCommentBean) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mCommentBean = mCommentBean;
        createCommentEditDialog();
    }

    private void createCommentEditDialog() {
//        View commentInputView = mInflater.inflate(R.layout.dialog_sub, null);
//        final EditText commentEdit = (EditText) commentInputView.findViewById(R.id.dialogSubComment_commentContentInput_edt);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//        builder.setTitle("请输入内容");
//        builder.setView(commentInputView);
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                String commentContent = commentEdit.getText().toString().trim();
//                SubCommentItem subCommentItem = new SubCommentItem(R.drawable.ic_launcher, "评论用户", commentContent);
//                if (!commentContent.equals("")) {
//                    mCommentItemList.get(mCurrentGroupPosition).getSubCommentItems().add(subCommentItem);
//
//                    notifyDataSetChanged();
//                }
//            }
//        });
//
//        mCommentEditDialog = builder.create();
    }

    //获取分组的个数
    @Override
    public int getGroupCount() {
        return mCommentBean.getOne().size();
    }

    //获取指定分组中的子选项的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        if (mCommentBean.getTwo() == null) {
            return 0;
        } else {
            return mCommentBean.getOne().get(groupPosition).getCOUNT();
        }
    }

    //获取指定的分组数据
    @Override
    public Object getGroup(int groupPosition) {
        List<WordDetailsCommentBean.OneBean> one = mCommentBean.getOne();
        return one.get(groupPosition);
    }

    //获取指定分组中的指定子选项数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (mCommentBean.getOne().get(groupPosition).getID() == mCommentBean.getTwo().get(childPosition).getPARENTID()) {
            return mCommentBean.getTwo().get(childPosition);
        }
        return null;
    }

    //获取指定分组的ID, 这个ID必须是唯一的
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //获取子选项的ID, 这个ID必须是唯一的
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们。
    @Override
    public boolean hasStableIds() {
        return false;
    }

    //获取显示指定分组的视图
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        CommentViewHolder cViewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_comment, null);
            cViewHolder = new CommentViewHolder(convertView);
            convertView.setTag(cViewHolder);
        } else {
            cViewHolder = (CommentViewHolder) convertView.getTag();
        }
        Glide.with(mContext).load(I.BASE_URL + mCommentBean.getOne().get(groupPosition).getHEAD_PATH()).into(cViewHolder.dtl_posts_comment_avatar);
        cViewHolder.dtl_posts_comment_name.setText(mCommentBean.getOne().get(groupPosition).getNICK_NAME());
        cViewHolder.dtl_posts_comment_content.setText(mCommentBean.getOne().get(groupPosition).getCONTENT());
        cViewHolder.dtl_posts_comment_time.setText(mCommentBean.getOne().get(groupPosition).getCREATEDATE());
        return convertView;
    }

    //获取显示指定分组中的指定子选项的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SubViewHolder subViewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_comment_sub, null);
            subViewHolder = new SubViewHolder(convertView);
            convertView.setTag(subViewHolder);
        } else {
            subViewHolder = (SubViewHolder) convertView.getTag();
        }

        Glide.with(mContext).load(I.BASE_URL + mCommentBean.getTwo().get(childPosition).getHEAD_PATH()).into(subViewHolder.dtl_posts_comment_avatar);
        subViewHolder.dtl_posts_comment_name.setText(mCommentBean.getTwo().get(childPosition).getNICK_NAME());
        subViewHolder.dtl_posts_comment_content.setText(mCommentBean.getTwo().get(childPosition).getCONTENT());
        subViewHolder.dtl_posts_comment_time.setText(mCommentBean.getTwo().get(childPosition).getCREATEDATE());
        return convertView;
    }

    //指定位置上的子元素是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class CommentViewHolder {
        public CircleImageView dtl_posts_comment_avatar;
        public TextView dtl_posts_comment_name, dtl_posts_comment_content, dtl_posts_comment_time;
        public ImageView dtl_posts_comment_zan;

        public CommentViewHolder(View convertView) {
            dtl_posts_comment_avatar = (CircleImageView) convertView.findViewById(R.id.dtl_posts_comment_avatar);
            dtl_posts_comment_name = (TextView) convertView.findViewById(R.id.dtl_posts_comment_name);
            dtl_posts_comment_content = (TextView) convertView.findViewById(R.id.dtl_posts_comment_content);
            dtl_posts_comment_time = (TextView) convertView.findViewById(R.id.dtl_posts_comment_time);
            dtl_posts_comment_zan = (ImageView) convertView.findViewById(R.id.dtl_posts_comment_zan);
        }
    }

    private class SubViewHolder {
        public CircleImageView dtl_posts_comment_avatar;
        public TextView dtl_posts_comment_name, dtl_posts_comment_content, dtl_posts_comment_time;
        public ImageView dtl_posts_comment_zan;

        public SubViewHolder(View convertView) {
            dtl_posts_comment_avatar = (CircleImageView) convertView.findViewById(R.id.dtl_posts_comment_avatar);
            dtl_posts_comment_name = (TextView) convertView.findViewById(R.id.dtl_posts_comment_name);
            dtl_posts_comment_content = (TextView) convertView.findViewById(R.id.dtl_posts_comment_content);
            dtl_posts_comment_time = (TextView) convertView.findViewById(R.id.dtl_posts_comment_time);
            dtl_posts_comment_zan = (ImageView) convertView.findViewById(R.id.dtl_posts_comment_zan);
        }
    }

}

