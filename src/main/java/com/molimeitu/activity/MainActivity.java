package com.molimeitu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.molimeitu.R;
import com.molimeitu.adapter.AdPageAdapter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private long mFirstClick = 0;

    private ArrayList<View> mPageViews;

    private ImageView imageView;

    private ImageView[] imageViews;

    // 包裹小圆点
    private ViewGroup group;

    private ViewPager mVpAds;

    private DrawerLayout mDrawerLayout;

    private LinearLayout mLayoutOrder;

    private LinearLayout mLayoutView;

    private LinearLayout mLayoutFeedBack;

    private LinearLayout mLayoutAbout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTitleVisible(false);
        setLayout(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        initView();
        initAd();
    }

    /**
     * 初始化广告
     */
    private void initAd() {
        int[] imgIDs = new int[] {R.drawable.ad1, R.drawable.ad2, R.drawable.ad3, R.drawable.ad4, R.drawable.ad5, R.drawable.ad6, R.drawable.ad7};
        mPageViews = new ArrayList<View>();
        for (int i=0; i<imgIDs.length; i++) {
            LinearLayout layout = new LinearLayout(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            final ImageView img = new ImageView(mContext);
            img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            img.setImageResource(imgIDs[i]);
            img.setPadding(15, 30, 15, 30);
            layout.addView(img, params);
            mPageViews.add(layout);
        }
        imageViews = new ImageView[mPageViews.size()];

        group = (ViewGroup) findViewById(R.id.ad_main_dot_group);
        mVpAds = (ViewPager) findViewById(R.id.ad_main_viewpager);

        /**
         * 有几张图片 下面就显示几个小圆点
         */
        for (int i = 0; i < mPageViews.size(); i++) {
            LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            //设置每个小圆点距离左边的间距
            margin.setMargins(10, 0, 0, 0);
            imageView = new ImageView(MainActivity.this);
            //设置每个小圆点的宽高
            imageView.setLayoutParams(new LinearLayout.LayoutParams(15, 15));
            imageViews[i] = imageView;
            if (i == 0) {
                // 默认选中第一张图片
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                //其他图片都设置未选中状态
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
            group.addView(imageViews[i], margin);
        }
        //给viewpager设置适配器
        mVpAds.setAdapter(new AdPageAdapter(mPageViews));
        //给viewpager设置监听事件
        mVpAds.setOnPageChangeListener(new AdPageChangeListener());
    }

    /**
     * 初始化UI组件
     */
    private void initView() {
        setTitle(R.string.title_activity_main);
        setNavBackIcon(R.drawable.usercenter);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main);
        // 设置锁定，不响应滑动，设置为只有点击触发
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
        mLayoutOrder = (LinearLayout) findViewById(R.id.ll_main_order);
        mLayoutView = (LinearLayout) findViewById(R.id.ll_main_view_order);
        mLayoutFeedBack = (LinearLayout) findViewById(R.id.ll_main_feedback);
        mLayoutAbout = (LinearLayout) findViewById(R.id.ll_main_about);

        mLayoutOrder.setOnClickListener(this);
        mLayoutView.setOnClickListener(this);
        mLayoutFeedBack.setOnClickListener(this);
        mLayoutAbout.setOnClickListener(this);
    }

    @Override
    public void onNavBack() {
        // 将抽屉打开或关闭
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
        else {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondClick = System.currentTimeMillis();
            if (secondClick - mFirstClick > 2000) {
                mFirstClick = secondClick;
                Toast.makeText(mContext, "再次点击退出应用", Toast.LENGTH_SHORT).show();
            }
            else {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()) {
            case R.id.ll_main_order:
//                Toast.makeText(mContext, "下单功能暂未开放", Toast.LENGTH_SHORT).show();
                intent = new Intent(mContext, CreateOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_main_view_order:
//                Toast.makeText(mContext, "订单查看功能暂未开放", Toast.LENGTH_SHORT).show();
                intent = new Intent(mContext, OrderListActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_main_feedback:
//                Toast.makeText(mContext, "信息反馈功能暂未开放", Toast.LENGTH_SHORT).show();
                intent = new Intent(mContext, FeedBackActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_main_about:
//                Toast.makeText(mContext, "关于App功能暂未开放", Toast.LENGTH_SHORT).show();
                intent = new Intent(mContext, AboutAppActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 广告页面更改事件监听器
     */
    class AdPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
        @Override
        public void onPageSelected(int arg0) {
            //遍历数组让当前选中图片下的小圆点设置颜色
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0].setBackgroundResource(R.drawable.page_indicator_focused);
                if (arg0 != i) {
                    imageViews[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
                }
            }
        }
    }
}
