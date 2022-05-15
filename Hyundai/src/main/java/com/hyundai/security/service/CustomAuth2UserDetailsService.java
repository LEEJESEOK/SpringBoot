package com.hyundai.security.service;

import com.hyundai.entity.User;
import com.hyundai.entity.UserRole;
import com.hyundai.entity.UserRoleSet;
import com.hyundai.repository.UserRepository;
import com.hyundai.security.dto.AuthUserDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LEE JESEOK
 */
@Service
@Log4j2
public class CustomAuth2UserDetailsService extends DefaultOAuth2UserService {

    final UserRepository userRepository;

    final PasswordEncoder passwordEncoder;

    public CustomAuth2UserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("CustomAuth2UserDetailsService.loadUser");
        log.info("userRequest : " + userRequest);

        // oauth2 서비스 client
        String clientName = userRequest.getClientRegistration().getClientName();
        log.info("clientName : " + clientName);

        // oauth client 에서 제공하는 정보
        OAuth2User oAuth2User = super.loadUser(userRequest);
        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info(k + " : " + v);
        });

        String email = null;
        switch (clientName) {
            case "Google":
                email = oAuth2User.getAttribute("email");
                break;
        }
        log.info(email);

        // 신규 사용자 추가
        User user = saveSocialUser(email);
        List<UserRoleSet> userRoleSets = userRepository.selectUserRoleSetsByEmail(email);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRoleSet userRoleSet : userRoleSets) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRoleSet.getRoleSet()));
        }

        AuthUserDTO authUserDTO = new AuthUserDTO(user.getEmail(), user.getPassword(), user.getSocial(), authorities, oAuth2User.getAttributes());
        authUserDTO.setPassword(user.getPassword());
        authUserDTO.setName(user.getName());


        return authUserDTO;
    }

    User saveSocialUser(String email) {
        User result = userRepository.selectUserByEmail(email);

        if (result != null) return result;


        // 소셜 로그인 계정, 권한 생성
        User user = new User();
        user.setEmail(email);
        // 기본 비밀번호 1111
        user.setPassword(passwordEncoder.encode("1111"));
        user.setName(email);
        user.setSocial(1);
        userRepository.insertUser(user);

        UserRoleSet userRoleSet = new UserRoleSet();
        userRoleSet.setUserEmail(email);
        userRoleSet.setRoleSet(UserRole.USER.name());
        userRepository.insertUserRoleSet(userRoleSet);


        result = userRepository.selectUserByEmail(email);

        return result;
    }

}
