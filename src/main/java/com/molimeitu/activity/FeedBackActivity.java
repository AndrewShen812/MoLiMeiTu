package com.molimeitu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.molimeitu.R;
import com.molimeitu.adapter.FeedBackChatAdapter;
import com.molimeitu.model.ChatModel;
import com.molimeitu.util.StringUtils;
import com.molimeitu.util.TimeUtils;

import java.util.ArrayList;


public class FeedBackActivity extends BaseActivity implements View.OnClickListener {

    private ListView mLvChatList;

    private EditText mEtInput;

    private Button mBtnSend;

    private ArrayList<ChatModel> mChatData;

    private FeedBackChatAdapter mChatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayout(R.layout.activity_feed_back);
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    /**
     * 初始化UI组件
     */
    private void initView() {
        setTitle(R.string.title_activity_feed_back);
        mLvChatList = (ListView) findViewById(R.id.lv_fb_chat_list);
        mLvChatList.setDivider(null); // 不显示ListView条目之间的分隔线
        mEtInput = (EditText) findViewById(R.id.et_fb_input);
        mBtnSend = (Button) findViewById(R.id.btn_fb_send);
        mBtnSend.setOnClickListener(this);
    }

    /**
     * 初始化显示数据
     */
    private void initData() {
        mChatData = new ArrayList<ChatModel>();
        ChatModel chatAuto = new ChatModel();
        chatAuto.role = ChatModel.ROLE_LEFT;
        chatAuto.time = TimeUtils.getCurrentDateTime(null);
        chatAuto.content = getResources().getString(R.string.feed_back_auto_reply);
        mChatData.add(chatAuto);
        mChatAdapter = new FeedBackChatAdapter(mContext);
        mChatAdapter.setList(mChatData);
        mLvChatList.setAdapter(mChatAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fb_send:
                String reply = mEtInput.getText().toString().trim();
                if (!StringUtils.isEmpty(reply)) {
                    ChatModel chatReply = new ChatModel();
                    chatReply.role = ChatModel.ROLE_RIGHT;
                    chatReply.time = TimeUtils.getCurrentDateTime(null);
                    chatReply.content = mEtInput.getText().toString().trim();
                    mChatData.add(chatReply);
                    mChatAdapter.notifyDataSetChanged();
                    mEtInput.setText("");
                }
                else {
                    Toast.makeText(mContext, "请输入发送内容", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
