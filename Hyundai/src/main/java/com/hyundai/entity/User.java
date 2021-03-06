package com.hyundai.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 사용자 정보 저장하는 클래스<br>
 * 이메일(ID), 비밀번호, 이름, 생성일, 소셜 로그인 구분, 계정 상태<br>
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

    // 생성일
    Date regDate;

    // 소셜 로그인 구분
    int social;

    // 계정 활성 상태
    String state;
}

