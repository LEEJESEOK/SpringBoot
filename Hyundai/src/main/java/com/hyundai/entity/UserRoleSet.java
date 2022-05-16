package com.hyundai.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 사용자 권한을 저장하는 클래스
 *
 * @author LEE JESEOK
 */
@Setter
@Getter
public class UserRoleSet implements Serializable {
    String userEmail;
    String roleSet;
}
