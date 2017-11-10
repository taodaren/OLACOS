package net.osplay.ui.activity.sub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import net.osplay.olacos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LabelActivity extends Activity {

    @BindView(R.id.info_add_ed)
    EditText infoAddEd;
    @BindView(R.id.change_qm_iv)
    ImageView changeQmIv;
    @BindView(R.id.change_save)
    TextView changeSave;
    private String editInfoAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        editInfoAdd = intent.getStringExtra("editInfoAdd");
        infoAddEd.setHint(editInfoAdd);
    }

    @OnClick({R.id.change_qm_iv, R.id.change_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_qm_iv:
                finish();
                break;
            case R.id.change_save:
                Intent returnInfoAdd = new Intent();
                returnInfoAdd.putExtra("returnInfoAdd", infoAddEd.getText().toString());
                setResult(3, returnInfoAdd);
                finish();
                break;
        }
    }
}
