package com.opsigte.eat.order.api.entity;

import java.io.Serializable;

public class OrderEntity implements Serializable {
    private static final long serialVersionUID = -162993931096089292L;

    private String id;

    private String orderNumber;

    private String uid;

    private String userName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
