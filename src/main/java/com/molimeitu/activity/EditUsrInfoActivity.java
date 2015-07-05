package com.molimeitu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.molimeitu.R;
import com.molimeitu.consts.IntentAction;
import com.molimeitu.model.UserModel;
import com.molimeitu.util.StringUtils;
import com.molimeitu.util.ToastUtils;
import com.molimeitu.util.business.ConfigUtils;

public class EditUsrInfoActivity extends BaseActivity implements View.OnClickListener {

    public static final String KEY_USER = "UsrInfo";

    private EditText mEtName;

    private EditText mEtPhone;

    private EditText mEtPwd;

    private EditText mEtConfirm;

    private EditText mEtAddr;

    private Button mBtnSave;

    private UserModel mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayout(R.layout.activity_edit_usr_info);
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initView() {
        setTitle(R.string.title_activity_edit_usr_info);
        mEtName = (EditText) findViewById(R.id.et_edit_usr_info_name);
        mEtPhone = (EditText) findViewById(R.id.et_edit_usr_info_tel);
        mEtPwd = (EditText) findViewById(R.id.et_edit_usr_info_pwd);
        mEtConfirm = (EditText) findViewById(R.id.et_edit_usr_info_confirm);
        mEtAddr = (EditText) findViewById(R.id.et_edit_usr_info_addr);
        mBtnSave = (Button) findViewById(R.id.btn_edit_usr_info_save);

        mBtnSave.setOnClickListener(this);
    }

    private void initData() {
        try {
            mUser = JSON.parseObject(ConfigUtils.getUserInfo(mContext), UserModel.class);
            mEtName.setText(mUser.name);
            mEtPhone.setText(mUser.tel);
            mEtPwd.setText(mUser.pwd);
            mEtConfirm.setText(mUser.pwd);
            mEtAddr.setText(mUser.addr);
        } catch (Exception e) {
            e.printStackTrace();
            mUser = new UserModel();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit_usr_info_save:
                if (checkData()) {
                    try {
                        // 缓存到本地文件
                        ConfigUtils.setUserInfo(mContext, JSON.toJSONString(mUser));
                        // TODO 保存修改信息到服务器
                        Intent intent = new Intent(IntentAction.FINISH_EDIT);
                        intent.putExtra(KEY_USER, mUser);
                        sendBroadcast(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        finish();
                    }
                }
                break;
            default:
                break;
        }
    }

    private boolean checkData() {
        mUser.name = mEtName.getText().toString().trim();
        mUser.tel = mEtPhone.getText().toString().trim();
        mUser.pwd = mEtPwd.getText().toString().trim();
        String confirmPwd = mEtConfirm.getText().toString().trim();
        mUser.addr = mEtAddr.getText().toString().trim();
        if (StringUtils.isEmpty(mUser.name)) {
            ToastUtils.showToast(mContext, "请输入用姓名");
            return false;
        }
        else if (!StringUtils.isValidPhone(mUser.tel)) {
            ToastUtils.showToast(mContext, "请输入正确的电话号码");
            return false;
        }
        else if (StringUtils.isEmpty(mUser.pwd)) {
            ToastUtils.showToast(mContext, "请输入密码");
            return false;
        }
        else if(StringUtils.isEmpty(confirmPwd)) {
            ToastUtils.showToast(mContext, "请输入确认密码");
            return false;
        }
        else if(StringUtils.isEmpty(mUser.addr)) {
            ToastUtils.showToast(mContext, "请输入送货地址");
            return false;
        }
        if (!mUser.pwd.equals(confirmPwd)) {
            ToastUtils.showToast(mContext, "两次输入的密码不一致");
            return false;
        }

        return true;
    }


}
