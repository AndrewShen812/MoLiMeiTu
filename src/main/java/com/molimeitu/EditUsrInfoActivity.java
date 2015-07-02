package com.molimeitu;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.molimeitu.R;
import com.molimeitu.consts.IntentAction;

public class EditUsrInfoActivity extends BaseActivity implements View.OnClickListener {

    public static final String KEY_NAME = "UsrName";

    public static final String KEY_TEL = "UsrTel";

    public static final String KEY_ADDR = "UsrAddr";

    private Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayout(R.layout.activity_edit_usr_info);
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        setTitle(R.string.title_activity_edit_usr_info);
        mBtnSave = (Button) findViewById(R.id.btn_edit_usr_info_save);

        mBtnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit_usr_info_save:
                String name = "申勇";
                String tel = "13548111470";
                String addr = "成都市金牛区一环路北一段99号";
                // TODO 保存修改信息到服务器
                // TODO 缓存到本地文件
                Intent intent = new Intent(IntentAction.FINISH_EDIT);
                intent.putExtra(KEY_NAME, name);
                intent.putExtra(KEY_TEL, tel);
                intent.putExtra(KEY_ADDR, addr);
                sendBroadcast(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
