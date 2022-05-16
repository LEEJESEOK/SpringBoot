package com.hyundai.service;

import com.hyundai.entity.User;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.event.annotation.AfterTestClass;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LEE JESEOK
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Log4j2
class UserServiceTest {

    static String email;
    static String password;
    static String name;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeAll
    static void setupTests() {
        email = "test" + new Date().getTime();
        password = "test";
        name = "test";
    }


    @Order(0)
    @Test
    void signin() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setSocial(0);

        assertTrue(userService.signin(user));
    }

    @Order(10)
    @Test
    void getUserInfoByEmail() {
        User user = userService.getUserInfoByEmail(email);
        assertNotNull(user);
    }

    @Order(11)
    @Test
    void isDuplicatedByEmail() {
        assertTrue(userService.isDuplicatedByEmail(email));
    }

    @Order(20)
    @Test
    void updateUserInfo() {
        String newName = "새이름";

        User oldUser = userService.getUserInfoByEmail(email);
        oldUser.setName(newName);
        userService.updateUserInfo(oldUser);

        User updatedUser = userService.getUserInfoByEmail(email);
        assertEquals(newName, updatedUser.getName());
    }

    @Order(21)
    @Test
    void updateUserPassword() {
        String newPassword = "newpass";

        User oldUser = userService.getUserInfoByEmail(email);
        oldUser.setEmail(newPassword);
        userService.updateUserPassword(email, newPassword);

        User updatedUser = userService.getUserInfoByEmail(email);
        assertTrue(passwordEncoder.matches(newPassword, updatedUser.getPassword()));
    }

    @AfterTestClass
    void clean() {
        userService.deleteUserByEmail(email);
    }
}