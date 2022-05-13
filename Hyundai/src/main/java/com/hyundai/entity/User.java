package com.hyundai.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class User implements Serializable {
    String email;
    String password;
    String name;

    Date regDate;

    String role_set;

    int socialIdx;
}

