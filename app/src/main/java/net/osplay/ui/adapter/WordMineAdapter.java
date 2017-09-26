package net.osplay.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.osplay.app.I;
import net.osplay.app.MyApplication;
import net.osplay.olacos.R;
import net.osplay.service.entity.WordAddBean;
import net.osplay.service.entity.WordRecoBean;
import net.osplay.service.entity.base.HomeData;
import net.osplay.ui.activity.sub.MinePageOtherActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区 → 我的适配器
 */

public class WordMineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "WordMineAdapter";
    public static final int TYPE_ADD_WORD = 0;
    public static final int TYPE_RECOM_WORD = 1;

    private Activity mContext;
    private LayoutInflater mInflater;

    private List<HomeData> mDataList;
    private List<WordAddBean> mAddWordList;
    private List<WordRecoBean> mRecoWordList;

    public WordMineAdapter(Activity context, List<HomeData> data, List<WordAddBean> addWordList, List<WordRecoBean> recoList) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);

        this.mDataList = new ArrayList<>();
        this.mAddWordList = new ArrayList<>();
        this.mRecoWordList = new ArrayList<>();

        this.mDataList.addAll(data);
        this.mAddWordList.addAll(addWordList);
        this.mRecoWordList.addAll(recoList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ADD_WORD:
                return new AddViewHolder(mInflater.inflate(R.layout.layout_word_add, parent, false));
            case TYPE_RECOM_WORD:
                return new RecoViewHolder(mInflater.inflate(R.layout.layout_word_recommend, parent, false));
            default:
                Log.e(TAG, "onCreateViewHolder: is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (mDataList.get(position).getItemType()) {
            case TYPE_ADD_WORD:
                ((AddViewHolder) holder).bindData((List<WordAddBean>) mDataList.get(position).getData());
                break;
            case TYPE_RECOM_WORD:
                ((RecoViewHolder) holder).bindData((List<WordRecoBean>) mDataList.get(position).getData());
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

    private static class AddViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rvAddWord;
        private List<WordAddBean> addWordList;
        private StaggeredGridLayoutManager layoutManager;
        private MineAddAdapter adapter;

        private AddViewHolder(View itemView) {
            super(itemView);
            rvAddWord = (RecyclerView) itemView.findViewById(R.id.recycler_add_word);
        }

        private void bindData(List<WordAddBean> beanList) {
            if (beanList != null && !beanList.isEmpty()) {
                this.addWordList = new ArrayList<>();
                this.addWordList.addAll(beanList);
                bindRecom();
            }
        }

        private void bindRecom() {
            layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            rvAddWord.setLayoutManager(layoutManager);
            adapter = new MineAddAdapter(MyApplication.getContext(), addWordList);
            rvAddWord.setAdapter(adapter);
        }

        private class MineAddAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
            private Context context;
            private LayoutInflater inflater;
            private List<WordAddBean> datas;

            private MineAddAdapter(Context context, List<WordAddBean> datas) {
                this.context = context;
                this.inflater = LayoutInflater.from(MyApplication.getContext());
                this.datas = new ArrayList<>();
                this.datas.addAll(datas);
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View inflate = inflater.inflate(R.layout.item_add_word, parent, false);
                AddItemViewHolder holder = new AddItemViewHolder(inflate);
                holder.setClickListener();
                return holder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((AddItemViewHolder) holder).bindData(datas.get(position));
            }

            @Override
            public int getItemCount() {
                return datas == null ? 0 : datas.size();
            }

            private class AddItemViewHolder extends RecyclerView.ViewHolder {
                private View outView;//保存子项最外层布局的实例
                private ImageView imgAvatar;
                private TextView textNick;

                private AddItemViewHolder(View itemView) {
                    super(itemView);
                    outView = itemView;
                    imgAvatar = (ImageView) itemView.findViewById(R.id.add_word_avatar);
                    textNick = (TextView) itemView.findViewById(R.id.add_word_type);
                }

                private void bindData(WordAddBean addBean) {
                    Glide.with(MyApplication.getContext()).load(I.BASE_URL + addBean.getPART_PATH()).into(imgAvatar);
                    Log.d(TAG, "AddItemViewHolder bindData: " + imgAvatar);
                    textNick.setText(addBean.getPART());
                }

                private void setClickListener() {
                    outView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int position = getAdapterPosition();
                            WordAddBean addBean = datas.get(position);
                            Toast.makeText(context, "点击" + addBean.getNOTES() + "布局", Toast.LENGTH_SHORT).show();
                        }
                    });
                    imgAvatar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, MinePageOtherActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    });
                }
            }
        }

    }

    private static class RecoViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rvRecomWord;
        private List<WordRecoBean> recoWordList;
        private RecyclerView.LayoutManager layoutManager;
        private MineRecoAdapter adapter;

        private RecoViewHolder(View itemView) {
            super(itemView);
            rvRecomWord = (RecyclerView) itemView.findViewById(R.id.recycler_recommend_word);
        }

        private void bindData(List<WordRecoBean> beanList) {
            if (beanList != null && !beanList.isEmpty()) {
                this.recoWordList = new ArrayList<>();
                this.recoWordList.addAll(beanList);
                bindRecommend();
            }
        }

        private void bindRecommend() {
            layoutManager = new LinearLayoutManager(MyApplication.getContext(), LinearLayoutManager.VERTICAL, false);
            rvRecomWord.setLayoutManager(layoutManager);
            adapter = new MineRecoAdapter(MyApplication.getContext(), recoWordList);
            rvRecomWord.setAdapter(adapter);
        }

        private class MineRecoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
            private Context context;
            private LayoutInflater inflater;
            private List<WordRecoBean> datas;

            private MineRecoAdapter(Context context, List<WordRecoBean> datas) {
                this.context = context;
                this.inflater = LayoutInflater.from(MyApplication.getContext());
                this.datas = new ArrayList<>();
                this.datas.addAll(datas);
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View inflate = inflater.inflate(R.layout.item_recommend_word, parent, false);
                RecomItemViewHolder holder = new RecomItemViewHolder(inflate);
                holder.setClickListener();
                return holder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((RecomItemViewHolder) holder).bindData(datas.get(position));
            }

            @Override
            public int getItemCount() {
                return datas == null ? 0 : datas.size();
            }

            private class RecomItemViewHolder extends RecyclerView.ViewHolder {
                private View outView;//保存子项最外层布局的实例
                private ImageView imgAvatar;
                private Button btnAdd;
                private TextView textNick, textMember, textPosts, textInfo;

                private RecomItemViewHolder(View itemView) {
                    super(itemView);
                    outView = itemView;
                    imgAvatar = (ImageView) itemView.findViewById(R.id.recommend_word_avatar);
                    btnAdd = (Button) itemView.findViewById(R.id.recommend_word_add);
                    textNick = (TextView) itemView.findViewById(R.id.recommend_word_type);
                    textMember = (TextView) itemView.findViewById(R.id.recommend_word_member);
                    textPosts = (TextView) itemView.findViewById(R.id.recommend_word_posts);
                    textInfo = (TextView) itemView.findViewById(R.id.recommend_word_info);
                }

                private void bindData(WordRecoBean recomBean) {
                    Glide.with(MyApplication.getContext()).load(I.BASE_URL + recomBean.getPART_PATH()).into(imgAvatar);
                    textNick.setText(recomBean.getPART());
                    textMember.setText("成员:" + recomBean.getMEMBER_COUNT());
                    textPosts.setText("帖子:" + recomBean.getTOPICK_COUNT());
                    textInfo.setText(recomBean.getNOTES());
                }

                private void setClickListener() {
                    outView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int position = getAdapterPosition();
                            WordRecoBean recomBean = datas.get(position);
                            Toast.makeText(context, "点击" + recomBean.getPART() + "布局", Toast.LENGTH_SHORT).show();
                        }
                    });
                    imgAvatar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, MinePageOtherActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    });
                }
            }
        }

    }

}
