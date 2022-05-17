package com.hyundai.config;

import com.hyundai.entity.UserRole;
import com.hyundai.security.handler.LoginSuccessHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 설정<br>
 * 로그인, 로그아웃, rememberMe<br>
 * {@literal @PreAuthorize 사용 설정}
 *
 * @author LEE JESEOK
 */
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Password Encoder
    // BCryptPasswordEncoder
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // LoginSuccess Handler
    // 미적용
    @Bean
    LoginSuccessHandler successHandler() {
        return new LoginSuccessHandler(passwordEncoder());
    }

    // 테스트용 계정
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().passwordEncoder(encoder)
                .withUser("test")
                .password(encoder.encode("1111"))
                .roles(UserRole.USER.name());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * 로그인 - 로그인 페이지 적용 /login
         *         로그인 요청 url /login - POST
         *         성공시 목록으로 이동
         * oauth2Login - 로그인 페이지 적용 /login
         *               로그인 성공시 목록으로 이동
         * logout - 성공시 목록으로 이동
         * rememberMe - 자동 로그인 적용
         *              1주일 = 60초 * 60분 * 24시간 * 7일
         */
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/board/list")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/board/list")
//                .successHandler(successHandler())
                .and()
                .logout()
                .logoutSuccessUrl("/board/list")
                .and()
                .rememberMe()
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                .userDetailsService(userDetailsService)
                .and()
                .csrf().disable();
    }
}
