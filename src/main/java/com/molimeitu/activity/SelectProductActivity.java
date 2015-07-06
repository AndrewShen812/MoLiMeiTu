package com.molimeitu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.molimeitu.R;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;

public class SelectProductActivity extends BaseActivity {

    private PtrFrameLayout mPtr;

    private MaterialHeader mHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayout(R.layout.activity_select_product);
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        setTitle(R.string.title_activity_select_product);
        mPtr = (PtrFrameLayout) findViewById(R.id.ptr_test);
        mPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
        mHeader = new MaterialHeader(mContext);
        mHeader.setPtrFrameLayout(mPtr);

    }
}
