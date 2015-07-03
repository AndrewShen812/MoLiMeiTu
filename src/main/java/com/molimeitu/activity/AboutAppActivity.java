package com.molimeitu.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.molimeitu.R;
import com.molimeitu.util.AppUtils;
import com.molimeitu.util.qrcode.QRCodeUtils;


public class AboutAppActivity extends BaseActivity {

    private ImageView mIvQrcode;

    private TextView mTvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayout(R.layout.activity_about_app);
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initView() {
        setTitle(getResources().getString(R.string.title_activity_about_app));
        mIvQrcode = (ImageView) findViewById(R.id.iv_about_qrcode);
        mTvVersion = (TextView) findViewById(R.id.tv_about_app_version);
    }

    private void initData() {
        mIvQrcode.setImageBitmap(QRCodeUtils.createImage("http://www.baidu.com"));
        mTvVersion.setText("摩利美涂App V" + AppUtils.getVersionName());
    }
}
