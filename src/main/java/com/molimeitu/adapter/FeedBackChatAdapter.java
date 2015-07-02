package com.molimeitu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.molimeitu.R;
import com.molimeitu.model.ChatModel;

/**
 * 项目名称：LianfengApp
 * 类描述：FeedBackChatAdapter
 * 创建人：lianfeng
 * 创建时间：2015/7/2 14:45
 * 修改人：lianfeng
 * 修改时间：2015/7/2 14:45
 * 修改备注：
 * 版本：V1.0
 */
public class FeedBackChatAdapter extends BaseArrayAdapter<ChatModel> {

    private Context mContext;

    public FeedBackChatAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder item = null;
        if (null == convertView) {
            item = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_chat_item, null);
            item.mTvLTime = (TextView) convertView.findViewById(R.id.tv_fb_left_time);
            item.mTvLContent = (TextView) convertView.findViewById(R.id.tv_fb_left_content);
            item.mLayoutL = (LinearLayout) convertView.findViewById(R.id.ll_fb_left);
            item.mTvRTime = (TextView) convertView.findViewById(R.id.tv_fb_right_time);
            item.mTvRContent = (TextView) convertView.findViewById(R.id.tv_fb_right_content);
            item.mLayoutR = (LinearLayout) convertView.findViewById(R.id.ll_fb_right);

            convertView.setTag(item);
        }
        else {
            item = (ViewHolder) convertView.getTag();
        }
        // init data
        ChatModel chat = mList.get(position);
        if (ChatModel.ROLE_LEFT == chat.role) { // 显示左侧消息
            item.mTvRTime.setVisibility(View.GONE);
            item.mLayoutR.setVisibility(View.GONE);
            item.mTvLTime.setText(chat.time);
            item.mTvLContent.setText(chat.content);
        }
        else if (ChatModel.ROLE_RIGHT == chat.role) { // 显示右侧消息
            item.mTvLTime.setVisibility(View.GONE);
            item.mLayoutL.setVisibility(View.GONE);
            item.mTvRTime.setText(chat.time);
            item.mTvRContent.setText(chat.content);
        }

        return convertView;
    }

    /**
     * 聊天视图容器
     */
    private class ViewHolder {
        /** 左侧消息时间 */
        TextView mTvLTime;
        /** 左侧消息内容 */
        TextView mTvLContent;
        /** 左侧消息布局 */
        LinearLayout mLayoutL;
        /** 右侧消息时间 */
        TextView mTvRTime;
        /** 右侧消息内容 */
        TextView mTvRContent;
        /** 右侧消息布局 */
        LinearLayout mLayoutR;
    }
}
