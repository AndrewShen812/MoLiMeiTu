package com.molimeitu.activity;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.widget.TextView;

import com.molimeitu.R;

import java.util.Timer;
import java.util.TimerTask;

public class CreateOrderSuccessActivity extends BaseActivity {

    private static final int MSG_COUNT = 0;

    private TextView mTvCntDown;

    private int mCntTotal = 10;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_COUNT:
                    mTvCntDown.setText(msg.arg1 + "秒后将自动返回首页");
                    if (msg.arg1 == 0) {
                        finish();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayout(R.layout.activity_create_order_success);
        super.onCreate(savedInstanceState);

        initView();
        startTimer();
    }

    private void initView() {
        setTitle(R.string.title_activity_create_order_success);
        mTvCntDown = (TextView) findViewById(R.id.tv_create_success_count_down);
    }

    private void startTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mCntTotal--;
                Message msg = Message.obtain();
                msg.what = MSG_COUNT;
                msg.arg1 = mCntTotal;
                mHandler.sendMessage(msg);
            }
        }, 1000, 1000);
    }
}
