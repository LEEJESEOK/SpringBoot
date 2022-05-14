package com.hyundai.repository;

import com.hyundai.entity.User;
import com.hyundai.entity.UserRole;
import com.hyundai.entity.UserRoleSet;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeAll
    static void setUp() {
        email = "test" + new Date().getTime();
        password = "test";
        name = "테스트";
        roleSet = UserRole.USER.toString();
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
        User user = repository.selectUserByEmail("test");
        log.info(user);
        assertNotNull(user);
    }

    @Order(21)
    @Test
    void selectUserRoleSetsByEmail() {
        List<UserRoleSet> userRoleSet = repository.selectUserRoleSetsByEmail(email);
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
        List<UserRoleSet> userRoleSet = repository.selectUserRoleSetsByEmail(email);
        userRoleSet.get(0).setRoleSet(UserRole.ADMIN.toString());

        assertEquals(1, repository.updateUserRoleSet(userRoleSet.get(0)));
        log.info(repository.selectUserRoleSetsByEmail(email));
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

    @Test
    void insertDummies() {
        for (int i = 1; i <= 100; ++i) {
            String dummyEmail = "test" + i + "@hyundai.com";

            User user = new User();
            user.setEmail(dummyEmail);
            user.setPassword(passwordEncoder.encode(password));
            user.setName("테스트" + i);
            user.setSocial(0);

            repository.insertUser(user);


            UserRoleSet userRoleSet = new UserRoleSet();
            userRoleSet.setUserEmail(dummyEmail);
            String role = UserRole.USER.name();
            if (i > 90)
                role = UserRole.ADMIN.name();
            userRoleSet.setRoleSet(role);

            repository.insertUserRoleSet(userRoleSet);
        }
    }
}