package com.molimeitu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.molimeitu.R;
import com.molimeitu.model.OrderModel;

/**
 * 项目名称：LianfengApp
 * 类描述：OrderListAdapter
 * 创建人：lianfeng
 * 创建时间：2015/7/2 17:56
 * 修改人：lianfeng
 * 修改时间：2015/7/2 17:56
 * 修改备注：
 * 版本：V1.0
 */
public class OrderListAdapter extends BaseArrayAdapter<OrderModel> {

    private Context mContext;

    public OrderListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder item = null;
        if (null == convertView) {
            item = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_order_item, null);
            item.mTvCreateTime = (TextView) convertView.findViewById(R.id.tv_order_item_time);
            item.mTvOrderNo = (TextView) convertView.findViewById(R.id.tv_order_item_order_no);
            item.mTvContact = (TextView) convertView.findViewById(R.id.tv_order_item_contact);
            item.mTvTel = (TextView) convertView.findViewById(R.id.tv_order_item_tel);
            item.mTvDetail = (TextView) convertView.findViewById(R.id.tv_order_item_order_detail);

            convertView.setTag(item);
        }
        else {
            item = (ViewHolder) convertView.getTag();
        }
        OrderModel order = mList.get(position);
        item.mTvCreateTime.setText("下单时间：" + order.createTime);
        item.mTvOrderNo.setText("订单号：" + order.orderNo);
        item.mTvContact.setText(order.contact.name);
        item.mTvTel.setText(order.contact.tel);
        item.mTvDetail.setText(order.orderDetail);

        return convertView;
    }

    private class ViewHolder {
        TextView mTvCreateTime;
        TextView mTvOrderNo;
        TextView mTvContact;
        TextView mTvTel;
        TextView mTvDetail;
    }
}
