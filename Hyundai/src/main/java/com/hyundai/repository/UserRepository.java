package com.hyundai.repository;


import com.hyundai.entity.User;
import com.hyundai.entity.UserRoleSet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * User SQL 처리
 *
 * @author LEE JESEOK
 */
@Mapper
public interface UserRepository {

    /**
     * User 정보 추가
     *
     * @param user
     * @return 1 : 정상적으로 추가
     */
    int insertUser(User user);

    /**
     * UserRoleSet 정보 추가
     *
     * @param userRoleSet
     * @return -1 : 정상적으로 추가
     */
    int insertUserRoleSet(UserRoleSet userRoleSet);

    /**
     * email에 해당하는 User 정보 조회
     *
     * @param email
     * @return 조회한 User
     */
    User selectUserByEmail(String email);

    /**
     * email에 해당하는 UserRoleSet 정보 조회
     *
     * @param email
     * @return 조회한 UserRoleSet
     */
    List<UserRoleSet> selectUserRoleSetsByEmail(String email);

    /**
     * email, password 해당하는 User 확인
     *
     * @param email
     * @param password
     * @return true : email, password 해당하는 회원이 있음
     */
    boolean selectUserByLogin(String email, String password);

    /**
     * user의 email 에 해당하는 User 정보 수정
     *
     * @param user
     * @return 1 : 정상적으로 수정
     */
    int updateUser(User user);

    /**
     * user의 email에 해당하는 UserRoleSet 정보 수정
     *
     * @param userRoleSet
     * @return 1 : 정상적으로 수정
     */
    int updateUserRoleSet(UserRoleSet userRoleSet);

    /**
     * email에 해당하는 User 삭제
     *
     * @param email
     * @return 1: 정상적으로 삭제
     */
    int deleteUser(String email);

//    /**
//     * email에 해당하는 UserRoleSet 삭제
//     *
//     * @param email
//     * @return 1 : 정상적으로 삭제
//     */
//    int deleteUserRoleSet (String email);
}
