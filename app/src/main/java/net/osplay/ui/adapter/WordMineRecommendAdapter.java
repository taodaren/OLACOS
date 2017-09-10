package net.osplay.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.osplay.olacos.R;
import net.osplay.service.entity.VideoBean;
import net.osplay.service.entity.base.HomeData;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区 → 我的 → 推荐社区适配器
 */

public class WordMineRecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "TabHomeAdapter";
    public static final int TYPE_RECOMMEND_WORD = 0;

    private Activity mContext;
    private LayoutInflater mInflater;

    private List<HomeData> mDataList;

    public WordMineRecommendAdapter(Activity context, List<HomeData> data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDataList = new ArrayList<>();
        this.mDataList.addAll(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_RECOMMEND_WORD:
                return new RecommendViewHolder(mInflater.inflate(R.layout.layout_recommend_word, parent, false));
            default:
                Log.e(TAG, "onCreateViewHolder: is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (mDataList.get(position).getItemType()) {
            case TYPE_RECOMMEND_WORD:
                ((RecommendViewHolder) holder).bindData((List<VideoBean>) mDataList.get(position).getData());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).getItemType();
    }

    //////////////////// view holder ////////////////////

    private static class RecommendViewHolder extends RecyclerView.ViewHolder {
        private List<VideoBean> datas;
        private ImageView imgAvatar;
        private Button btnAdd;
        private TextView textNick, textMember, textPosts, textInfo;

        private RecommendViewHolder(View itemView) {
            super(itemView);
            imgAvatar = (ImageView) itemView.findViewById(R.id.recommend_word_avatar);
            btnAdd = (Button) itemView.findViewById(R.id.recommend_word_add);
            textNick = (TextView) itemView.findViewById(R.id.recommend_word_type);
            textMember = (TextView) itemView.findViewById(R.id.recommend_word_member);
            textPosts = (TextView) itemView.findViewById(R.id.recommend_word_posts);
            textInfo = (TextView) itemView.findViewById(R.id.recommend_word_info);
        }

        private void bindData(List<VideoBean> beanList) {
            if (beanList != null && !beanList.isEmpty()) {
                datas = new ArrayList<>();
                datas.addAll(beanList);
                bindRecommend();
            }
        }

        private void bindRecommend() {

        }
    }

}
