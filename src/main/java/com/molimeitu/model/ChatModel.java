package com.molimeitu.model;

import java.io.Serializable;

/**
 * 项目名称：LianfengApp
 * 类描述：ChatModel
 * 创建人：lianfeng
 * 创建时间：2015/7/2 14:57
 * 修改人：lianfeng
 * 修改时间：2015/7/2 14:57
 * 修改备注：
 * 版本：V1.0
 */
public class ChatModel implements Serializable {

    public static final int ROLE_LEFT = 1;

    public static final int ROLE_RIGHT = 2;

    public String content;

    public String time;

    /** 发送聊天内容的角色：1-对方发送；2-用户发送 */
    public int role;
}
