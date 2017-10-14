package net.osplay.ui.adapter.sub;

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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.app.MFGT;
import net.osplay.data.bean.ActionResultBean;
import net.osplay.olacos.R;
import net.osplay.service.entity.WordTopicListBean;
import net.osplay.ui.activity.sub.DetailsPostsActivity;
import net.osplay.ui.activity.sub.MinePageOtherActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 社区帖子详情 → 全部/精品/同城
 */

public class WordTopicListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<WordTopicListBean.RowsBean> mRowsBeanList;

    public WordTopicListAdapter(Context mContext, List<WordTopicListBean.RowsBean> rowsBeanList) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mRowsBeanList = new ArrayList<>();
        this.mRowsBeanList.addAll(rowsBeanList);
    }

    public void setData(List<WordTopicListBean.RowsBean> data) {
        if (mRowsBeanList != null) {
            mRowsBeanList.clear();
        } else {
            mRowsBeanList = new ArrayList<>();
        }
        mRowsBeanList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public PostsInfoListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PostsInfoListHolder holder = new PostsInfoListHolder(mInflater.inflate(R.layout.item_word_topic_list, parent, false));
        holder.setClickListener();
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PostsInfoListHolder) holder).bindData(position);
    }

    @Override
    public int getItemCount() {
        return mRowsBeanList == null ? 0 : mRowsBeanList.size();
    }

    private class PostsInfoListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String TAG = "PostsInfoListHolder";
        private View outView;
        private LinearLayout llZan, llCollect, llComment;
        private TextView tvNick, tvTime, tvTitle, tvZan, tvCollect, tvComment;
        private ImageView imgAvatar, imgBg, imgZan, imgCollect;
        private String postsId;//帖子 ID
        private WordTopicListBean.RowsBean rowsBean;
        private Intent intent;

        private PostsInfoListHolder(View itemView) {
            super(itemView);
            outView = itemView;
            tvNick = (TextView) itemView.findViewById(R.id.tv_topic_list_nick);
            tvTime = (TextView) itemView.findViewById(R.id.tv_topic_list_time);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_topic_list_title);
            tvZan = (TextView) itemView.findViewById(R.id.tv_topic_list_zan);
            tvCollect = (TextView) itemView.findViewById(R.id.tv_topic_list_collect);
            tvComment = (TextView) itemView.findViewById(R.id.tv_topic_list_comment);
            imgAvatar = (ImageView) itemView.findViewById(R.id.img_topic_list_avatar);
            imgBg = (ImageView) itemView.findViewById(R.id.img_topic_list_icon);
            imgZan = (ImageView) itemView.findViewById(R.id.img_topic_list_zan);
            imgCollect = (ImageView) itemView.findViewById(R.id.img_topic_list_collect);
            llZan = (LinearLayout) itemView.findViewById(R.id.ll_topic_list_zan);
            llCollect = (LinearLayout) itemView.findViewById(R.id.ll_topic_list_collect);
            llComment = (LinearLayout) itemView.findViewById(R.id.ll_topic_list_comment);
        }

        public void bindData(int position) {
            rowsBean = mRowsBeanList.get(position);
            if (rowsBean.getHEAD_PATH() != null) {
                Glide.with(mContext).load(I.BASE_URL + rowsBean.getHEAD_PATH()).into(imgAvatar);
            } else {
                Glide.with(mContext).load(R.drawable.avatar_default).into(imgAvatar);
            }
            Glide.with(mContext).load(I.BASE_URL + rowsBean.getCOVERIMG()).into(imgBg);
            tvNick.setText(rowsBean.getNICK_NAME());
            tvTime.setText(rowsBean.getCREATEDATE());
            tvTitle.setText(rowsBean.getTITLE());
            tvZan.setText(rowsBean.getZAN_COUNT());
            tvCollect.setText(rowsBean.getCOLLECT_COUNT());
            tvComment.setText(rowsBean.getPINGLUN_COUNT());
            postsId = rowsBean.getID();

            if ("true".equals(rowsBean.getZAN())) {//如果已点赞，显示点赞状态
                imgZan.setImageResource(R.drawable.ic_sugar_selected);
            }

            if ("true".equals(rowsBean.getCOLLECT())) {//如果已收藏，显示收藏状态
                imgCollect.setImageResource(R.drawable.ic_collect_selected);
            }
        }

        public void setClickListener() {
            outView.setOnClickListener(this);
            llZan.setOnClickListener(this);
            llCollect.setOnClickListener(this);
            llComment.setOnClickListener(this);
            imgAvatar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            WordTopicListBean.RowsBean bean = mRowsBeanList.get(getAdapterPosition());
            switch (v.getId()) {
                case R.id.img_topic_list_avatar:
                    intent = new Intent(mContext, MinePageOtherActivity.class);
                    intent.putExtra("memberId", bean.getUSERID());
                    mContext.startActivity(intent);
                    break;
                case R.id.ll_topic_list_zan://点赞
                    if (!(AppHelper.getInstance().isLogined())) {
                        MFGT.gotoLogin(mContext, "loginZan");
                    } else {
                        actionSupport();
                    }
                    break;
                case R.id.ll_topic_list_collect://收藏
                    if (!(AppHelper.getInstance().isLogined())) {
                        MFGT.gotoLogin(mContext, "loginCollect");
                    } else {
                        actionCommunity();
                    }
                    break;
//                case R.id.ll_topic_list_comment://评论
//                    Toast.makeText(mContext, "click comment", Toast.LENGTH_SHORT).show();
//                    break;
                default:
                    intent = new Intent(mContext, DetailsPostsActivity.class);
                    intent.putExtra(I.Posts.POSTS_ID, postsId);
                    mContext.startActivity(intent);
                    break;
            }
        }

        /**
         * 点赞功能
         */
        private void actionSupport() {
            RequestQueue requestQueue = NoHttp.newRequestQueue();
            int action = 0;
            if ("true".equals(rowsBean.getZAN())) {
                action = 1;//取消点赞
            }
            Request<String> request = NoHttp.createStringRequest(I.POSTS_ZAN, RequestMethod.POST);
            request.add("memberId", AppHelper.getInstance().getUserID());
            request.add("topickId", rowsBean.getID());
            request.add("mark", action);

            final int finalAction = action;
            requestQueue.add(0, request, new OnResponseListener<String>() {
                @Override
                public void onStart(int what) {
                }

                @Override
                public void onSucceed(int what, Response<String> response) {
                    String json = response.get();
                    Log.e(TAG, "点赞请求：" + json);
                    if (json != null) {
                        Type type = new TypeToken<ActionResultBean>() {
                        }.getType();
                        Gson gson = new Gson();
                        ActionResultBean resultBean = gson.fromJson(json, type);
                        if (resultBean.isOk()) {
                            switch (finalAction) {
                                case 0://点赞成功
                                    rowsBean.setZAN("true");//重置用户对当前帖子的点赞状态
                                    rowsBean.setZAN_COUNT(String.valueOf(
                                            Integer.parseInt(rowsBean.getZAN_COUNT()) + 1));
                                    imgZan.setImageResource(R.drawable.ic_sugar_selected);
                                    tvZan.setText(rowsBean.getZAN_COUNT());
                                    break;
                                case 1://取消点赞成功
                                    rowsBean.setZAN("false");//重置用户对当前帖子的点赞状态
                                    rowsBean.setZAN_COUNT(String.valueOf(
                                            Integer.parseInt(rowsBean.getZAN_COUNT()) - 1));
                                    imgZan.setImageResource(R.drawable.ic_sugar_unselected);
                                    tvZan.setText(rowsBean.getZAN_COUNT());
                                    break;
                            }
                        }
                    } else {
                        return;
                    }
                }

                @Override
                public void onFailed(int what, Response<String> response) {
                }

                @Override
                public void onFinish(int what) {
                }
            });
        }

        /**
         * 收藏功能
         */
        private void actionCommunity() {
            RequestQueue requestQueue = NoHttp.newRequestQueue();
            int action = 0;
            if ("true".equals(rowsBean.getCOLLECT())) {
                action = 1;//取消收藏
            }
            Request<String> request = NoHttp.createStringRequest(I.POSTS_COLLECT, RequestMethod.POST);
            request.add("memberId", AppHelper.getInstance().getUserID());
            request.add("CommunityId", rowsBean.getID());
            request.add("mark", action);

            final int finalAction = action;
            requestQueue.add(0, request, new OnResponseListener<String>() {
                @Override
                public void onStart(int what) {
                }

                @Override
                public void onSucceed(int what, Response<String> response) {
                    String json = response.get();
                    Log.e(TAG, "收藏请求：" + json);
                    if (json != null) {
                        Type type = new TypeToken<ActionResultBean>() {
                        }.getType();
                        Gson gson = new Gson();
                        ActionResultBean resultBean = gson.fromJson(json, type);
                        if (resultBean.isOk()) {
                            switch (finalAction) {
                                case 0://收藏成功
                                    rowsBean.setCOLLECT("true");//重置用户对当前帖子的收藏状态
                                    rowsBean.setCOLLECT_COUNT(String.valueOf(
                                            Integer.parseInt(rowsBean.getCOLLECT_COUNT()) + 1));
                                    imgCollect.setImageResource(R.drawable.ic_collect_selected);
                                    tvCollect.setText(rowsBean.getCOLLECT_COUNT());
                                    break;
                                case 1://取消收藏成功
                                    rowsBean.setCOLLECT("false");//重置用户对当前帖子的收藏状态
                                    rowsBean.setCOLLECT_COUNT(String.valueOf(
                                            Integer.parseInt(rowsBean.getCOLLECT_COUNT()) - 1));
                                    imgCollect.setImageResource(R.drawable.ic_collect_unselected);
                                    tvCollect.setText(rowsBean.getCOLLECT_COUNT());
                                    break;
                            }
                        }
                    } else {
                        return;
                    }
                }

                @Override
                public void onFailed(int what, Response<String> response) {
                }

                @Override
                public void onFinish(int what) {
                }
            });
        }
    }

}
