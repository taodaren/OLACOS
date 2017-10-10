package net.osplay.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import net.osplay.app.AppHelper;
import net.osplay.app.I;
import net.osplay.app.MyApplication;
import net.osplay.data.bean.CommonTitleBean;
import net.osplay.olacos.R;
import net.osplay.service.entity.WordAddBean;
import net.osplay.service.entity.WordRecoBean;
import net.osplay.service.entity.base.HomeData;
import net.osplay.ui.activity.sub.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 社区 → 我的适配器
 */

public class WordMineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "WordMineAdapter";
    public static final int TYPE_TITLE = 0;
    public static final int TYPE_ADD_WORD = 1;
    public static final int TYPE_RECO_WORD = 2;
    public static final int TYPE_ADD_EMPTY = 3;

    private Activity mContext;
    private LayoutInflater mInflater;

    private List<HomeData> mDataList;
    private ActionListener mListener;

    public interface ActionListener {

        void actionFollow(String areaID, int action, WordAddBean bean, int actionPosition);
    }

    public ActionListener getListener() {
        return mListener;
    }

    public void setListener(ActionListener listener) {
        mListener = listener;
    }

    public WordMineAdapter(Activity context, List<HomeData> data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);

        this.mDataList = new ArrayList<>();
        this.mDataList.addAll(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                return new CommonTitleHolder(mInflater.inflate(R.layout.item_commo_title, parent, false));
            case TYPE_ADD_WORD:
                return new AddViewHolder(mInflater.inflate(R.layout.layout_word_add, parent, false));
            case TYPE_RECO_WORD:
                return new RecoViewHolder(mInflater.inflate(R.layout.layout_word_recommend, parent, false));
            case TYPE_ADD_EMPTY:
                return new AddEmptyHolder(mInflater.inflate(R.layout.item_add, parent, false));
            default:
                Log.e(TAG, "onCreateViewHolder: is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_TITLE:
                ((CommonTitleHolder) holder).setData((HomeData<CommonTitleBean>) mDataList.get(position));
                break;
            case TYPE_ADD_WORD:
                ((AddViewHolder) holder).setData((HomeData<WordAddBean>) mDataList.get(position));
                break;
            case TYPE_RECO_WORD:
                ((RecoViewHolder) holder).setData((HomeData<WordRecoBean>) mDataList.get(position), position);
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

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isFullSpanType(position)) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    private boolean isFullSpanType(int pos) {
        return mDataList.get(pos).isSpan();
    }

    public void setData(List<HomeData> datas, int refreshIndex, int rangeCount) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
            mDataList.addAll(datas);
        } else {
            mDataList.clear();
            mDataList.addAll(datas);
        }
        notifyItemRangeChanged(refreshIndex, rangeCount);
    }

    public void setData(List<HomeData> datas) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
            mDataList.addAll(datas);
        } else {
            mDataList.clear();
            mDataList.addAll(datas);
        }
        notifyDataSetChanged();
    }


    //////////////////// view holder ////////////////////

    public class CommonTitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_common_title)
        ImageView ivItemCommonTitle;
        @BindView(R.id.tv_item_common_title)
        TextView tvItemCommonTitle;
        @BindView(R.id.empty_view_common_title)
        View emptyView;
        private CommonTitleBean data;

        CommonTitleHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(HomeData<CommonTitleBean> data) {
            if (data != null) {
                this.data = data.getData();
                bindData();
            }
        }

        private void bindData() {
            tvItemCommonTitle.setText(data.getTitle());
            ivItemCommonTitle.setImageResource(data.getImgID());
            // 显示边距
            if ("推荐的专区".equals(data.getTitle())) {
                emptyView.setVisibility(View.VISIBLE);
            }
        }
    }

    public class AddViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_word_add_cover)
        ImageView ivItemWordAddCover;
        @BindView(R.id.tv_item_word_add)
        TextView tvItemWordAdd;
        private WordAddBean data;

        AddViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(HomeData<WordAddBean> data) {
            if (data != null) {
                this.data = data.getData();
                bindData();
            }
        }

        private void bindData() {
            tvItemWordAdd.setText(data.getPART());
            Glide.with(MyApplication.getContext()).load(I.BASE_URL + data.getPART_PATH()).into(ivItemWordAddCover);
        }
    }

    public class RecoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recommend_word_avatar)
        RoundedImageView recommendWordAvatar;
        @BindView(R.id.recommend_word_type)
        TextView recommendWordType;
        @BindView(R.id.recommend_word_member)
        TextView recommendWordMember;
        @BindView(R.id.recommend_word_posts)
        TextView recommendWordPosts;
        @BindView(R.id.recommend_word_add)
        Button recommendWordAdd;
        @BindView(R.id.ll_recommend_word)
        LinearLayout llRecommendWord;
        @BindView(R.id.recommend_word_info)
        TextView recommendWordInfo;
        @BindView(R.id.layout_recommend_word)
        LinearLayout layoutRecommendWord;
        private WordRecoBean data;
        private int position;

        RecoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(HomeData<WordRecoBean> data, int position) {
            if (data != null) {
                this.data = data.getData();
                this.position = position;
                bindData();
            }
        }

        private void bindData() {
            Glide.with(MyApplication.getContext()).load(I.BASE_URL + data.getPART_PATH()).into(recommendWordAvatar);
            recommendWordType.setText(data.getPART());
            recommendWordMember.setText("成员:" + data.getMEMBER_COUNT());
            recommendWordPosts.setText("帖子:" + data.getTOPICK_COUNT());
            recommendWordInfo.setText(data.getNOTES());

            if ("true".equals(data.getFOLLOW())) {
                recommendWordAdd.setBackground(mContext.getDrawable(R.drawable.btn_shape_white_un));
                recommendWordAdd.setTextColor(ContextCompat.getColor(mContext, R.color.colorTiShiWenZi));
                recommendWordAdd.setText("已加入");
            } else {
                recommendWordAdd.setBackground(mContext.getDrawable(R.drawable.btn_shape_white));
                recommendWordAdd.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                recommendWordAdd.setText("+ 加入");
            }
        }

        @OnClick({R.id.recommend_word_add, R.id.layout_recommend_word})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.recommend_word_add:
                    checkoutState();
                    break;
                case R.id.layout_recommend_word:

                    break;
            }
        }

        private void checkoutState() {
            if (AppHelper.getInstance().isLogined()) {
                actionFollow();
            } else {
                // goto LoginActivity
                mContext.startActivity(LoginActivity.getCallIntent(mContext));
            }
        }

        private void actionFollow() {
            // Add bean
            WordAddBean bean = new WordAddBean();
            bean.setID(data.getID());
            bean.setPART(data.getPART());
            bean.setPART_PATH(data.getPART_PATH());
            bean.setNOTES(data.getNOTES());

            if ("true".equals(data.getFOLLOW())) {
                mListener.actionFollow(data.getID(), I.Action.ACTION_CANCEL, bean, position);
            } else {
                mListener.actionFollow(data.getID(), I.Action.ACTION_DO, bean, position);
            }
        }
    }

    public class AddEmptyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.add_empty)
        RoundedImageView addEmpty;

        AddEmptyHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.add_empty)
        public void onViewClicked() {
            gotoActivity();
        }

        private void gotoActivity() {
            if (AppHelper.getInstance().isLogined()) {

            } else {
                Intent callIntent = LoginActivity.getCallIntent(mContext);
                mContext.startActivity(callIntent);
            }
        }
    }
}
