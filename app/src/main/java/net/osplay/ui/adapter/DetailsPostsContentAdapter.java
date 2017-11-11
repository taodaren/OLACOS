package net.osplay.ui.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.liji.imagezoom.util.ImageZoom;

import net.osplay.olacos.R;
import net.osplay.service.entity.WordDetailsPostsBean;
import net.osplay.ui.activity.sub.EditInfoActivity;
import net.osplay.utils.SharedPreferencesUtils;
import net.osplay.utils.UILKit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xrichtext.XRichText;

/**
 * 帖子详情内容适配器
 */

public class DetailsPostsContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity mContext;
    private LayoutInflater mInflater;
    private List<WordDetailsPostsBean> mDtlPostsList;

    public DetailsPostsContentAdapter(Activity context, List<WordDetailsPostsBean> dtlPostsList) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mDtlPostsList = new ArrayList<>();
        this.mDtlPostsList.addAll(dtlPostsList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostsContentHolder(mInflater.inflate(R.layout.item_details_posts_html, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PostsContentHolder) holder).bindData(position);
    }

    @Override
    public int getItemCount() {
        return mDtlPostsList == null ? 0 : mDtlPostsList.size();
    }

    private class PostsContentHolder extends RecyclerView.ViewHolder {
        private XRichText tvHtml;

        private PostsContentHolder(View itemView) {
            super(itemView);
            tvHtml = itemView.findViewById(R.id.tv_dtl_posts_html);
        }

        public void bindData(int position) {
            tvHtml.callback(new XRichText.BaseClickCallback() {
                @Override
                public boolean onLinkClick(String url) {
                    Toast.makeText(mContext, "链接地址：：" + url, Toast.LENGTH_SHORT).show();
                    return true;
                }

                @Override
                public void onImageClick(List<String> urlList, int position) {
                    super.onImageClick(urlList, position);
                    ImageZoom.show(mContext, position, urlList);//查看大图
                }

                @Override
                public void onFix(XRichText.ImageHolder holder) {
                    super.onFix(holder);
                    if (holder.getPosition() % 3 == 0) {
                        holder.setStyle(XRichText.Style.CENTER);//可设置显示的位置
                    } else if (holder.getPosition() % 3 == 1) {
                        holder.setStyle(XRichText.Style.CENTER);
                    } else {
                        holder.setStyle(XRichText.Style.CENTER);
                    }
//                    WindowManager wm = mContext.getWindowManager();
//                    int width = wm.getDefaultDisplay().getWidth();
//                    Log.e("JGB","屏幕的宽度："+width);
                    //设置宽度
                //    holder.setWidth(width);
//                    holder.setHeight(1000);

                }
            }).imageDownloader(new cn.droidlover.xrichtext.ImageLoader() {//如果不设置，有默认的下载器
                @Override
                public Bitmap getBitmap(String url) throws IOException {
                    return UILKit.getLoader().loadImageSync(url);
                }
            }).text(mDtlPostsList.get(position).getCONTENT());
        }
    }

}
