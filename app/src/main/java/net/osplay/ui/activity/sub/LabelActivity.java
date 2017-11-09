package net.osplay.ui.activity.sub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.osplay.olacos.R;
import net.osplay.ui.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LabelActivity extends BaseActivity {

    @BindView(R.id.info_add_ed)
    EditText infoAddEd;
    @BindView(R.id.info_add_bt)
    Button infoAddBt;
    private String editInfoAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);
        ButterKnife.bind(this);
        setToolbar("设置签名", View.VISIBLE);
        Intent intent = getIntent();
        editInfoAdd = intent.getStringExtra("editInfoAdd");
        infoAddEd.setText(editInfoAdd);
    }

    @OnClick(R.id.info_add_bt)
    public void onViewClicked() {
        Intent returnInfoAdd = new Intent();
        returnInfoAdd.putExtra("returnInfoAdd",infoAddEd.getText().toString());
        setResult(3,returnInfoAdd);
        finish();
    }
}
