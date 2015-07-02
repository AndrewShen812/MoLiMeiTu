package com.molimeitu.view;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.molimeitu.BaseActivity;
import com.molimeitu.R;

public class CreateOrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayout(R.layout.activity_create_order);
        super.onCreate(savedInstanceState);

        iniiView();
    }

    private void iniiView() {
        setTitle(R.string.title_activity_create_order);
    }
}
