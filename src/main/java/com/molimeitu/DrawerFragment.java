package com.molimeitu;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.molimeitu.consts.IntentAction;
import com.molimeitu.util.ImageUtils;

/**
 * 项目名称：LianfengApp
 * 类描述：DrawerFragment 侧边栏-用户相关信息
 * 创建人：lianfeng
 * 创建时间：2015/7/2 10:35
 * 修改人：lianfeng
 * 修改时间：2015/7/2 10:35
 * 修改备注：
 * 版本：V1.0
 */
public class DrawerFragment extends Fragment implements View.OnClickListener {

    private ImageView mIvHead;

    private TextView mTvName;

    private TextView mTvPhone;

    private TextView mTvAddr;

    private Button mBtnEditUsrInfo;

    private EditUsrInfoReceiver mEditReceiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.layout_drawer_left, container, false);
        initView(layout);

        return layout;
    }

    private void initView(View layout) {
        mIvHead = (ImageView) layout.findViewById(R.id.iv_main_drawer_left_head);
        mIvHead.setImageBitmap(ImageUtils.getRoundBitmap(((BitmapDrawable) this.getResources().getDrawable(R.drawable.userhead)).getBitmap()));
        mTvName = (TextView) layout.findViewById(R.id.tv_drawer_name);
        mTvPhone = (TextView) layout.findViewById(R.id.tv_drawer_tel);
        mTvAddr = (TextView) layout.findViewById(R.id.tv_drawer_addr);
        mBtnEditUsrInfo = (Button) layout.findViewById(R.id.btn_drawer_edit_usr_info);
        mBtnEditUsrInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_drawer_edit_usr_info:
                // 注册广播
                mEditReceiver = new EditUsrInfoReceiver();
                IntentFilter filter = new IntentFilter(IntentAction.FINISH_EDIT);
                getActivity().registerReceiver(mEditReceiver, filter);
                // 进入编辑
                Intent intent = new Intent(getActivity(), EditUsrInfoActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 完成用户信息编辑的广播接收器
     */
    private class EditUsrInfoReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (IntentAction.FINISH_EDIT.equals(action)) {
                mTvName.setText(intent.getStringExtra(EditUsrInfoActivity.KEY_NAME));
                mTvPhone.setText(intent.getStringExtra(EditUsrInfoActivity.KEY_TEL));
                mTvAddr.setText(intent.getStringExtra(EditUsrInfoActivity.KEY_ADDR));
                mTvName.setVisibility(View.VISIBLE);
                mTvPhone.setVisibility(View.VISIBLE);
                mTvAddr.setVisibility(View.VISIBLE);
                mBtnEditUsrInfo.setText("编辑信息");
            }
        }
    }
}
