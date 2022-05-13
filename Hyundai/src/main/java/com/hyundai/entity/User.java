package com.hyundai.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * User 정보 저장하는 클래스
 *
 * @author LEE JESEOK
 */
@Getter
@Setter
@ToString
public class User implements Serializable {
    String email;
    String password;
    String name;

    Date regDate;

    int socialIdx;
}

