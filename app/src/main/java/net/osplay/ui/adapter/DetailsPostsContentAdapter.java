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
        ((PostsContentHolder) holder).bindData(position);
    }

    @Override
    public int getItemCount() {
        return mDtlPostsList == null ? 0 : mDtlPostsList.size();
    }

    private class PostsContentHolder extends RecyclerView.ViewHolder {
        private TextView tvHtml;
        private String html;

        private PostsContentHolder(View itemView) {
            super(itemView);
            tvHtml = (TextView) itemView.findViewById(R.id.tv_dtl_posts_html);
        }

        public void bindData(int position) {
            html = mDtlPostsList.get(position).getCONTENT();
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
