package net.osplay.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import net.osplay.olacos.R;

/**
 * 个人中心 → 我发布的
 */

public class MinePublishActivity extends BaseActivity {
    private ImageView imgGoodsDelete, imgPostsDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_publish);
        initView();
    }

    private void initView() {
        setToolbar("我发布的", View.VISIBLE);
        setBtnDelete();
    }

    /**
     * 设置删除按钮
     */
    private void setBtnDelete() {
        imgGoodsDelete = (ImageView) findViewById(R.id.img_goods_delete);
        imgPostsDelete = (ImageView) findViewById(R.id.img_posts_delete);
        imgGoodsDelete.setVisibility(View.VISIBLE);
        imgPostsDelete.setVisibility(View.VISIBLE);
        imgGoodsDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MinePublishActivity.this, "执行删除商品操作", Toast.LENGTH_SHORT).show();
            }
        });
        imgPostsDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MinePublishActivity.this, "执行删除帖子操作", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

}
