package com.hyundai.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author LEE JESEOK
 */
@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void passwordEncoder() {
        String password = "1111";
        String encoded = passwordEncoder.encode(password);
        System.out.println(encoded);
        boolean matchResult = passwordEncoder.matches(password, encoded);
        System.out.println(matchResult);
    }
}