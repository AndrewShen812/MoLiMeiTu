package com.molimeitu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.molimeitu.R;

public class SelectProductActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayout(R.layout.activity_select_product);
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        setTitle(R.string.title_activity_select_product);
    }
}
