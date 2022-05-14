package com.hyundai.config;

import com.hyundai.entity.UserRole;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author LEE JESEOK
 */
@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    /**
//     * UserRole 권한 계층 적용
//     *
//     * @return
//     */
//    @Bean
//    public RoleHierarchyImpl roleHierarchyImpl() {
//        RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
//        roleHierarchyImpl.setHierarchy("ROLE_ADMIN > ROLE_USER");
//
//        return roleHierarchyImpl;
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // page role 지정
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/mypage").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name())
                .antMatchers("/admin").hasRole(UserRole.ADMIN.name());

        // 로그인 성공시 루트로 이동
        http.formLogin();

        // csrf 설정
        http.csrf().disable();

        // 로그아웃 설정
        http.logout();
    }
}
