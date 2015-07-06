package com.molimeitu.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.molimeitu.R;
import com.molimeitu.consts.ReqCode;
import com.molimeitu.model.OrderModel;
import com.molimeitu.util.IntentUtils;
import com.molimeitu.util.StringUtils;
import com.molimeitu.util.ToastUtils;
import com.molimeitu.util.business.ConfigUtils;

public class CreateOrderActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLayoutSelectTip;
    private LinearLayout mLayoutProduct;

    private EditText mEtName;

    private EditText mEtPhone;

    private EditText mEtAddr;

    private EditText mEtRemark;

    private Button mBtnCommit;

    private ImageView mIvContact;

    private OrderModel mOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayout(R.layout.activity_create_order);
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initView() {
        setTitle(R.string.title_activity_create_order);
        mLayoutSelectTip = (LinearLayout) findViewById(R.id.ll_create_order_product_select_tip);
        mLayoutProduct = (LinearLayout) findViewById(R.id.ll_create_order_product_list);
        mEtName = (EditText) findViewById(R.id.et_create_order_name);
        mIvContact = (ImageView) findViewById(R.id.iv_create_order_contact);
        mEtPhone = (EditText) findViewById(R.id.et_create_order_phone);
        mEtAddr = (EditText) findViewById(R.id.et_create_order_address);
        mEtRemark = (EditText) findViewById(R.id.et_create_order_remark);
        mBtnCommit = (Button) findViewById(R.id.btn_create_order_commit);

        mLayoutSelectTip.setOnClickListener(this);
        mIvContact.setOnClickListener(this);
        mBtnCommit.setOnClickListener(this);
    }

    private void initData() {
        try {
            mOrder = JSON.parseObject(ConfigUtils.getOrderInfo(mContext), OrderModel.class);
            if (null == mOrder) {
                mOrder = new OrderModel();
            }
            else {
                mEtName.setText(mOrder.contact.name);
                mEtPhone.setText(mOrder.contact.tel);
                mEtAddr.setText(mOrder.contact.addr);
                mEtRemark.setText(mOrder.remark);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.ll_create_order_product_select_tip:
                intent = new Intent(mContext, SelectProductActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_create_order_contact:
                IntentUtils.openContact(CreateOrderActivity.this, ReqCode.REQ_CONTACT);
                break;
            case R.id.btn_create_order_commit:
                if (checkOrderData()) {
                    // 缓存订单数据到本地
                    saveToLocal();
                    intent = new Intent(mContext, CreateOrderSuccessActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (RESULT_OK == resultCode && null != data) {
            switch (requestCode) {
                case ReqCode.REQ_CONTACT:
                    String username = "";
                    String usernumber = "";
                    //ContentProvider展示数据类似一个单个数据库表
                    //ContentResolver实例带的方法可实现找到指定的ContentProvider并获取到ContentProvider的数据
                    ContentResolver reContentResolverol = getContentResolver();
                    //URI,每个ContentProvider定义一个唯一的公开的URI,用于指定到它的数据集
                    Uri contactData = data.getData();
                    //查询就是输入URI等参数,其中URI是必须的,其他是可选的,如果系统能找到URI对应的ContentProvider将返回一个Cursor对象.
                    Cursor cursor = managedQuery(contactData, null, null, null, null);
                    cursor.moveToFirst();
                    //获得DATA表中的名字
                    username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    //条件为联系人ID
                    String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    // 获得DATA表中的电话号码，条件为联系人ID,因为手机号码可能会有多个
                    Cursor phone = reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                            null,
                            null);
                    while (phone.moveToNext()) {
                        usernumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    mEtName.setText(username);
                    mEtPhone.setText(usernumber);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 校验订单数据
     * @return true：订单数据正确；false：订单数据有误
     */
    private boolean checkOrderData() {
        mOrder.contact.name = mEtName.getText().toString().trim();
        mOrder.contact.tel = mEtPhone.getText().toString().trim();
        mOrder.contact.addr = mEtAddr.getText().toString().trim();
        mOrder.remark = mEtRemark.getText().toString().trim();

        if (StringUtils.isEmpty(mOrder.contact.name)) {
            ToastUtils.showToast(mContext, "请输入联系人姓名");
            return false;
        }
        if (!StringUtils.isValidPhone(mOrder.contact.tel)) {
            ToastUtils.showToast(mContext, "请输入正确的联系人电话");
            return false;
        }
        if (StringUtils.isEmpty(mOrder.contact.addr)) {
            ToastUtils.showToast(mContext, "请输入收货地址");
            return false;
        }

        return true;
    }

    private void saveToLocal() {
        try {
            ConfigUtils.setOrderInfo(mContext, JSON.toJSONString(mOrder));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
