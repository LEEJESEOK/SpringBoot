package com.hyundai.repository;

import com.hyundai.entity.User;
import com.hyundai.entity.UserRole;
import com.hyundai.entity.UserRoleSet;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LEE JESEOK
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Log4j2
class UserRepositoryTest {
    static String email;
    static String password;
    static String name;
    static String roleSet;


    @Autowired
    private UserRepository repository;

    @BeforeAll
    static void setUp() {
        email = "test" + new Date().getTime();
        password = "password";
        name = "테스트";
        roleSet = UserRole.ROLE_USER.toString();
    }

    @Order(0)
    @Test
    void repositoryExist() {
        assertNotNull(repository);
    }

    @Order(10)
    @Test
    void insertUser() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        log.info(user);

        assertEquals(1, repository.insertUser(user));
    }

    @Order(11)
    @Test
    void insertUserRoleSet() {
        UserRoleSet userRoleSet = new UserRoleSet();
        userRoleSet.setUserEmail(email);
        userRoleSet.setRoleSet(roleSet);

        assertEquals(1, repository.insertUserRoleSet(userRoleSet));
    }

    @Order(20)
    @Test
    void selectUserByEmail() {
        User user = repository.selectUserByEmail(email);
        log.info(user);
        assertNotNull(user);
    }

    @Order(21)
    @Test
    void selectUserRoleSetByEmail() {
        UserRoleSet userRoleSet = repository.selectUserRoleSetByEmail(email);
        log.info(userRoleSet);
        assertNotNull(userRoleSet);
    }

    @Order(22)
    @Test
    void selectUserByLogin() {
        assertTrue(repository.selectUserByLogin(email, password));
        assertFalse(repository.selectUserByLogin(email, null));
    }

    @Order(30)
    @Test
    void updateUser() {
        User user = repository.selectUserByEmail(email);
        user.setPassword("1111");

        assertEquals(1, repository.updateUser(user));
        log.info(repository.selectUserByEmail(email));
    }

    @Order(31)
    @Test
    void updateUserRoleSet() {
        UserRoleSet userRoleSet = repository.selectUserRoleSetByEmail(email);
        userRoleSet.setRoleSet(UserRole.ROLE_ADMIN.toString());

        assertEquals(1, repository.updateUserRoleSet(userRoleSet));
        log.info(repository.selectUserRoleSetByEmail(email));
    }

//    @Order(98)
//    @Test
//    void deleteUserRoleSet() {
//
//        assertEquals(1, repository.deleteUserRoleSet(email));
//    }

    @Order(99)
    @Test
    void deleteUser() {
        assertEquals(1, repository.deleteUser(email));
    }
}