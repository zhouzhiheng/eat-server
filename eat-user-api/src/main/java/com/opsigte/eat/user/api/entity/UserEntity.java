package com.opsigte.eat.user.api.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -7815052614084137168L;

    private Integer id;

    private String name;

    private Integer age;

}
