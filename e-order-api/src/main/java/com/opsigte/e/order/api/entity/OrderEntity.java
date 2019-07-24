package com.opsigte.e.order.api.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = -162993931096089292L;

    private String id;

    private String orderNumber;

    private String uid;

    private String userName;

}
