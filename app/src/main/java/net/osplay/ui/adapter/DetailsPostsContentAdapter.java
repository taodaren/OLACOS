package net.osplay.ui.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import net.osplay.olacos.R;
import net.osplay.service.entity.WordDetailsPostsBean;

import java.util.ArrayList;
import java.util.List;

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
        ((PostsContentHolder) holder).bindData();
    }

    @Override
    public int getItemCount() {
        return mDtlPostsList == null ? 0 : mDtlPostsList.size();
    }

    private class PostsContentHolder extends RecyclerView.ViewHolder {
        private TextView tvHtml;
        private final String html;

        private PostsContentHolder(View itemView) {
            super(itemView);
            tvHtml = (TextView) itemView.findViewById(R.id.tv_dtl_posts_html);
            html = "<p><br/>图片来源：Moorea Seal 不少人发现胶片照的质感后，就自然会深深爱上，因为数码摄影确实依然无法取而代之；而且，即使iPhone App有无数的滤镜特效，但还远不及使用不同胶片相机所带来的真实感动；再者，其实很多不错的胶片相机物美价廉，二手巿场也一直有供有求，所以喜欢玩不同相机与镜头的朋友，其实胶片老相机是一个很好的选择。问题是，我们购买二手相机，有什么地方需要留意？以下几点需要留意，先从镜头说起。<\\/p>\n<p>" +
                    "<p><img src=\"http://www.olacos.net/upLoadResource/image/20170916/1505558751961084713.png\" title=\"1505558751961084713.png\"/></p>" +
                    "<p><img src=\"http://www.olacos.net/upLoadResource/image/20170916/1505558760633039134.png\" title=\"1505558760633039134.png\"/></p>" +
                    "<p><img src=\"http://www.olacos.net/upLoadResource/image/20170916/1505558763821003594.png\" title=\"1505558763821003594.png\"/></p>" +
                    "<p><img src=\"http://www.olacos.net/upLoadResource/image/20170916/1505558760211003942.png\" title=\"1505558760211003942.png\"/></p>" +
                    "<p><img src=\"http://www.olacos.net/upLoadResource/image/20170916/1505558767352052642.png\" title=\"1505558767352052642.png\"/></p>" +
                    "<p><img src=\"http://www.olacos.net/upLoadResource/image/20170916/1505558768992037086.png\" title=\"1505558768992037086.png\"/></p>";
        }

        public void bindData() {
            tvHtml.setText(Html.fromHtml(html, new MyImgGetter(), null));
        }

        private class MyImgGetter implements Html.ImageGetter {
            private URLDrawable urlDrawable = null;

            @Override
            public Drawable getDrawable(String source) {
                urlDrawable = new URLDrawable();
                Picasso.with(mContext).load(source).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        urlDrawable.bitmap = bitmap;
                        urlDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                        tvHtml.setText(tvHtml.getText());//不加这句显示不出来图片，原因不详
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
                return urlDrawable;
            }
        }

        private class URLDrawable extends BitmapDrawable {
            private Bitmap bitmap;

            @Override
            public void draw(Canvas canvas) {
                super.draw(canvas);
                if (bitmap != null) {
                    canvas.drawBitmap(bitmap, 0, 0, getPaint());
                }
            }
        }
    }

}
