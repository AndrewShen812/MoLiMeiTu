package com.molimeitu.adapter;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

/**
 * 项目名称：LianfengApp
 * 类描述：AdPageAdapter
 * 创建人：lianfeng
 * 创建时间：2015/7/1 11:45
 * 修改人：lianfeng
 * 修改时间：2015/7/1 11:45
 * 修改备注：
 * 版本：V1.0
 */
public class AdPageAdapter extends PagerAdapter {

    private ArrayList<View> mPageViews;

    public AdPageAdapter(ArrayList<View> pageViews) {
        mPageViews = pageViews;
    }

    @Override
    public int getCount() {
        return mPageViews.size();
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(mPageViews.get(arg1));
    }
    @Override
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewPager) arg0).addView(mPageViews.get(arg1));
        return mPageViews.get(arg1);
    }
    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }
    @Override
    public Parcelable saveState() {
        return null;
    }
    @Override
    public void startUpdate(View arg0) {
    }
    @Override
    public void finishUpdate(View arg0) {
    }
}
