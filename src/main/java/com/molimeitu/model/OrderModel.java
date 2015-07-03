package com.molimeitu.model;

import java.io.Serializable;

/**
 * 项目名称：LianfengApp
 * 类描述：OrderModel
 * 创建人：lianfeng
 * 创建时间：2015/7/2 17:57
 * 修改人：lianfeng
 * 修改时间：2015/7/2 17:57
 * 修改备注：
 * 版本：V1.0
 */
public class OrderModel implements Serializable {

    public String createTime;

    public String orderNo;

    public ContactModel contact;

    public String orderDetail;

    public String remark;

    public OrderModel() {
        contact = new ContactModel();
    }
}
