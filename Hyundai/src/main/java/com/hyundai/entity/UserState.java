package com.hyundai.entity;

/**
 * 사용자 계정 상태<br>
 * 미적용
 *
 * @author LEE JESEOK
 */
public enum UserState {
    /**
     * 활성
     */
    ACTIVE,
    /**
     * 비활성, 탈퇴
     */
    INACTIVE,
    /**
     * 휴면
     */
    DORMANCY
}
