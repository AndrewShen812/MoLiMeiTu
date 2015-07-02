package com.molimeitu.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * 项目名称：LianfengApp
 * 类描述：BaseArrayAdapter
 * 创建人：lianfeng
 * 创建时间：2015/7/2 14:41
 * 修改人：lianfeng
 * 修改时间：2015/7/2 14:41
 * 修改备注：
 * 版本：V1.0
 */
public abstract class BaseArrayAdapter<T> extends BaseAdapter {

    public ArrayList<T> mList;

    public BaseArrayAdapter() {

    }

    public void setList(ArrayList<T> list) {
        mList = list;
    }

    public ArrayList<T> getList() {
        return mList;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
