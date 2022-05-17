package com.hyundai.service;

import com.hyundai.entity.User;

/**
 * User 관련 서비스 인터페이스
 *
 * @author LEE JESEOK
 */
public interface UserService {

    /**
     * 회원 가입
     *
     * @param user
     * @return 성공 여부
     */
    boolean signup(User user);


    /**
     * 사용자 정보 조회
     *
     * @param email
     * @return 조회한 User Object
     */
    User getUserInfoByEmail(String email);

    /**
     * email 중복 검사
     *
     * @param email
     * @return 중복한 email 존재하면 true
     */
    boolean isDuplicatedByEmail(String email);

    /**
     * 사용자 정보 수정
     *
     * @param user
     * @return
     */
    boolean updateUserInfo(User user);

    /**
     * 사용자 비밀번호 변경
     *
     * @param email
     * @param password
     * @return
     */
    boolean updateUserPassword(String email, String password);

    /**
     * 사용자 정보 삭제
     *
     * @param email
     * @return
     */
    boolean deleteUserByEmail(String email);
}
