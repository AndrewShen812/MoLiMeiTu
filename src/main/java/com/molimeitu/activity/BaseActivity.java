package com.molimeitu.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.molimeitu.R;
import com.molimeitu.util.StringUtils;

/**
 * 项目名称：LianfengApp
 * 类描述：BaseActivity
 * 创建人：lianfeng
 * 创建时间：2015/6/30 14:06
 * 修改人：lianfeng
 * 修改时间：2015/6/30 14:06
 * 修改备注：
 * 版本：V1.0
 */
public class BaseActivity extends Activity {

    public Context mContext;

    private int mLayoutResId;

    private boolean isTitleVisible = true;

    private LinearLayout mLayoutBack;

    private ImageView mIvNavBack;

    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isTitleVisible) {
            //自定义标题
            requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
            setContentView(mLayoutResId);
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.layout_title_bar);
            initView();
        }
        else {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(mLayoutResId);
        }

        mContext = this;
    }

    /**
     * 初始化标题栏控件
     */
    private void initView() {
        mLayoutBack = (LinearLayout) findViewById(R.id.ll_title_bar_back);
        mIvNavBack = (ImageView) findViewById(R.id.iv_title_bar_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title_bar_title);

        mLayoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavBack();
            }
        });
    }

    /**
     * 获取布局资源ID，必须在super.onCreate()之前调用
     * @param resId 布局资源ID
     */
    protected void setLayout(int resId) {
        mLayoutResId = resId;
    }

    /**
     * 是否显示标题栏（默认显示），必须在setLayout()之前调用
     * @param isVisible
     */
    protected void setTitleVisible(boolean isVisible) {
        isTitleVisible = isVisible;
    }

    /**
     * 设置页面标题
     * @param titleId
     */
    public void setTitle(int titleId) {
        if (null != mTvTitle) {
            mTvTitle.setText(titleId);
        }
    }

    /**
     * 设置页面标题
     * @param title
     */
    public void setTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            title = "";
        }
        if (null != mTvTitle) {
            mTvTitle.setText(title);
        }
    }

    /**
     * 设置标题栏回退按钮图片
     * @param resId
     */
    public void setNavBackIcon(int resId) {
        if (null != mIvNavBack) {
            mIvNavBack.setImageResource(resId);
        }
    }

    public void setNavBackIconBitMap(Bitmap bitmap) {
        if (null != mIvNavBack && null != bitmap) {
            mIvNavBack.setImageBitmap(bitmap);
        }
    }

    /**
     * 标题栏回退按钮事件
     */
    public void onNavBack() {
        finish();
    }
}
