package com.molimeitu;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.molimeitu.adapter.OrderListAdapter;
import com.molimeitu.model.OrderModel;

import java.util.ArrayList;


public class OrderListActivity extends BaseActivity implements View.OnClickListener {

    /** 订单类型：未处理订单 */
    public static final int ORDER_UNSETTLE = 1;

    /** 订单类型：已处理订单 */
    public static final int ORDER_SETTLED = 2;

    private TextView mTvUnSettle;

    private TextView mTvSettled;

    private ListView mLvOrders;

    private OrderListAdapter mListAdapter;

    private ArrayList<OrderModel> mOrderData;

    private int mOrderType = ORDER_UNSETTLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayout(R.layout.activity_order_list);
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initView() {
        setTitle(getResources().getString(R.string.title_activity_order_list));
        mTvUnSettle = (TextView) findViewById(R.id.tv_order_list_unsettled);
        mTvSettled = (TextView) findViewById(R.id.tv_order_list_settled);
        mLvOrders = (ListView) findViewById(R.id.lv_order_list);
        mLvOrders.setDivider(null);
        mTvUnSettle.setOnClickListener(this);
        mTvSettled.setOnClickListener(this);
    }

    private void initData() {
        mOrderData = new ArrayList<OrderModel>();

        for (int i=0; i<5; i++) {
            OrderModel model = new OrderModel();
            model.contact.name = "申勇";
            model.contact.tel = "13548111470";
            model.createTime = "2015-07-02 18:33";
            model.orderDetail = "这里是订单详细信息" + (i+1);
            model.orderNo = "20150702183300" + (i+1);
            mOrderData.add(model);
        }

        mListAdapter = new OrderListAdapter(mContext);
        mListAdapter.setList(mOrderData);
        mLvOrders.setAdapter(mListAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_order_list_unsettled:
                mOrderType = ORDER_UNSETTLE;
                setOrderTag(mOrderType);
                break;
            case R.id.tv_order_list_settled:
                mOrderType = ORDER_SETTLED;
                setOrderTag(mOrderType);
                break;
            default:
                break;
        }
    }

    /**
     * 设置顶部订单按钮状态
     * @param orderType
     */
    private void setOrderTag(int orderType) {
        if (ORDER_UNSETTLE == orderType) {
            mTvUnSettle.setBackgroundResource(R.drawable.shape_rectangle_pressed);
            mTvSettled.setBackgroundResource(R.drawable.shape_rectangle);
        }
        else if (ORDER_SETTLED == orderType) {
            mTvUnSettle.setBackgroundResource(R.drawable.shape_rectangle);
            mTvSettled.setBackgroundResource(R.drawable.shape_rectangle_pressed);
        }
    }
}
