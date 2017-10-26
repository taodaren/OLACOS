package net.osplay.ui.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liji.imagezoom.util.ImageZoom;

import net.osplay.olacos.R;
import net.osplay.service.entity.WordDetailsPostsBean;
import net.osplay.utils.UILKit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xrichtext.XRichText;

/**
 * 帖子详情内容适配器
 */

public class DetailsPostsContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "DetailsPostsContentAdapter";
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
                    //Toast.makeText(mContext,"图片下标：："+urlList.get(position),Toast.LENGTH_SHORT).show();//图片地址和下标
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
//                    //获取图片真正的宽高
//                    Glide.with(mContext)
//                            .load()
//                            .asBitmap()//强制Glide返回一个Bitmap对象
//                            .into(new SimpleTarget<Bitmap>() {
//                                @Override
//                                public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
//                                    int width = bitmap.getWidth();
//                                    int height = bitmap.getHeight();
//                                    Log.d(TAG, "width " + width); //200px
//                                    Log.d(TAG, "height " + height); //200px
//                                }
//                            });
                    //设置宽高
                    holder.setWidth(700);
                    holder.setHeight(700);
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
