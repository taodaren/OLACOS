package net.osplay.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.osplay.app.MyApplication;
import net.osplay.olacos.R;
import net.osplay.service.entity.VideoBean;
import net.osplay.service.entity.base.HomeData;
import net.osplay.ui.activity.sub.DetailsColumnActivity;
import net.osplay.ui.activity.sub.MinePageOtherActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区 → 热区 → 专栏适配器
 */

public class WordColumnAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "WordColumnAdapter";
    public static final int TYPE_SUBSCRIBE = 0;
    public static final int TYPE_UN_SUBSCRIBE = 1;

    private Activity mContext;
    private LayoutInflater mInflater;

    private List<HomeData> mDataList;
    private List<VideoBean> mSubList;
    private List<VideoBean> mUnSubList;

    public WordColumnAdapter(Activity mContext, List<HomeData> mDataList, List<VideoBean> mSubList, List<VideoBean> mUnSubList) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);

        this.mDataList = new ArrayList<>();
        this.mSubList = new ArrayList<>();
        this.mUnSubList = new ArrayList<>();
        this.mDataList.addAll(mDataList);
        this.mSubList.addAll(mSubList);
        this.mUnSubList.addAll(mUnSubList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_SUBSCRIBE:
                return new SubHolder(mInflater.inflate(R.layout.layout_column_sub, parent, false));
            case TYPE_UN_SUBSCRIBE:
                return new UnSubHolder(mInflater.inflate(R.layout.layout_column_unsub, parent, false));
            default:
                Log.e(TAG, "WordColumnAdapter onCreateViewHolder: is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (mDataList.get(position).getItemType()) {
            case TYPE_SUBSCRIBE:
                ((SubHolder) holder).bindData((List<VideoBean>) mDataList.get(position).getData());
                break;
            case TYPE_UN_SUBSCRIBE:
                ((UnSubHolder) holder).bindData((List<VideoBean>) mDataList.get(position).getData());
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

    private class SubHolder extends RecyclerView.ViewHolder {
        private RecyclerView rvColumnSub;
        private List<VideoBean> subList;
        private LinearLayoutManager layoutManager;
        private ColumnSubAdapter adapter;

        private SubHolder(View inflate) {
            super(inflate);
            rvColumnSub = (RecyclerView) inflate.findViewById(R.id.recycler_column_sub);
        }

        public void bindData(List<VideoBean> beanList) {
            if (beanList != null && !beanList.isEmpty()) {
                this.subList = new ArrayList<>();
                this.subList.addAll(beanList);
                bindSub();
            }
        }

        private void bindSub() {
            layoutManager = new LinearLayoutManager(MyApplication.getContext(), LinearLayoutManager.VERTICAL, false);
            rvColumnSub.setLayoutManager(layoutManager);
            adapter = new ColumnSubAdapter(MyApplication.getContext(), subList);
            rvColumnSub.setAdapter(adapter);
        }

        private class ColumnSubAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
            private Context context;
            private LayoutInflater inflater;
            private List<VideoBean> datas;

            private ColumnSubAdapter(Context context, List<VideoBean> subList) {
                this.context = context;
                this.inflater = LayoutInflater.from(MyApplication.getContext());
                this.datas = new ArrayList<>();
                this.datas.addAll(subList);
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View inflate = inflater.inflate(R.layout.item_column_sub, parent, false);
                SubItemViewHolder holder = new SubItemViewHolder(inflate);
                holder.setClickListener();
                return holder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((SubItemViewHolder) holder).bindData(datas.get(position));
            }

            @Override
            public int getItemCount() {
                return datas == null ? 0 : datas.size();
            }

            private class SubItemViewHolder extends RecyclerView.ViewHolder {
                private View outView;//保存子项最外层布局的实例
                private LinearLayout layout;
                private ImageView imgAvatar;
                private TextView tvNick;

                private SubItemViewHolder(View itemView) {
                    super(itemView);
                    outView = itemView;
                    layout = (LinearLayout) itemView.findViewById(R.id.card_column_sub);
                    imgAvatar = (ImageView) itemView.findViewById(R.id.img_column_sub);
                    tvNick = (TextView) itemView.findViewById(R.id.text_title_column_sub);
                }

                private void bindData(VideoBean videoBean) {
                    Glide.with(MyApplication.getContext()).load(videoBean.getCoverImg()).into(imgAvatar);
                    tvNick.setText(videoBean.getVideoTitle().substring(0, 3));
                }

                private void setClickListener() {
                    outView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mContext.startActivity(new Intent(mContext, DetailsColumnActivity.class));
                        }
                    });

                    imgAvatar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mContext.startActivity(new Intent(mContext, MinePageOtherActivity.class));
                        }
                    });
                }
            }
        }
    }

    private class UnSubHolder extends RecyclerView.ViewHolder {
        private RecyclerView rvColumnSub;
        private List<VideoBean> subList;
        private LinearLayoutManager layoutManager;
        private ColumnUnSubAdapter adapter;

        private UnSubHolder(View inflate) {
            super(inflate);
            rvColumnSub = (RecyclerView) inflate.findViewById(R.id.recycler_column_unsub);
        }

        public void bindData(List<VideoBean> beanList) {
            if (beanList != null && !beanList.isEmpty()) {
                this.subList = new ArrayList<>();
                this.subList.addAll(beanList);
                bindUnSub();
            }
        }

        private void bindUnSub() {
            layoutManager = new LinearLayoutManager(MyApplication.getContext(), LinearLayoutManager.VERTICAL, false);
            rvColumnSub.setLayoutManager(layoutManager);
            adapter = new ColumnUnSubAdapter(MyApplication.getContext(), subList);
            rvColumnSub.setAdapter(adapter);
        }

        private class ColumnUnSubAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
            private Context context;
            private LayoutInflater inflater;
            private List<VideoBean> datas;

            private ColumnUnSubAdapter(Context context, List<VideoBean> subList) {
                this.context = context;
                this.inflater = LayoutInflater.from(MyApplication.getContext());
                this.datas = new ArrayList<>();
                this.datas.addAll(subList);
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View inflate = inflater.inflate(R.layout.item_column_unsub, parent, false);
                UnSubItemViewHolder holder = new UnSubItemViewHolder(inflate);
                holder.setClickListener();
                return holder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((UnSubItemViewHolder) holder).bindData(datas.get(position));
            }

            @Override
            public int getItemCount() {
                return datas == null ? 0 : datas.size();
            }

            private class UnSubItemViewHolder extends RecyclerView.ViewHolder {
                private View outView;//保存子项最外层布局的实例
                private LinearLayout layout;
                private ImageView imgAvatar;
                private TextView tvNick;

                private UnSubItemViewHolder(View itemView) {
                    super(itemView);
                    outView = itemView;
                    layout = (LinearLayout) itemView.findViewById(R.id.card_column_unsub);
                    imgAvatar = (ImageView) itemView.findViewById(R.id.img_column_unsub);
                    tvNick = (TextView) itemView.findViewById(R.id.text_title_column_unsub);
                }

                private void bindData(VideoBean videoBean) {
                    Glide.with(MyApplication.getContext()).load(videoBean.getCoverImg()).into(imgAvatar);
                    tvNick.setText(videoBean.getVideoTitle().substring(0, 3));
                }

                private void setClickListener() {
                    outView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mContext.startActivity(new Intent(mContext, DetailsColumnActivity.class));
                        }
                    });

                    imgAvatar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mContext.startActivity(new Intent(mContext, MinePageOtherActivity.class));
                        }
                    });
                }
            }
        }
    }

}
