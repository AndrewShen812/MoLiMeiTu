package com.molimeitu;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class AboutAppActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayout(R.layout.activity_about_app);
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        setTitle(getResources().getString(R.string.title_activity_about_app));
    }
}
