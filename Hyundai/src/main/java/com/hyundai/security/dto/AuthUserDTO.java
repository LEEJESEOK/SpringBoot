package com.hyundai.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

/**
 * 사용자 인증 DTO<br>
 * 이메일, 비밀번호, 이름, 소셜 로그인 구분, OAuth2 속성
 *
 * @author LEE JESEOK
 */
@Getter
@Setter
@ToString
public class AuthUserDTO extends User implements OAuth2User {

    String email;
    String password;
    String name;
    int social;

    Map<String, Object> OA2Attributes;

    public AuthUserDTO(String username, String password, int social, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.email = username;
        this.social = social;
    }

    public AuthUserDTO(String username, String password, int social, Collection<? extends GrantedAuthority> authorities, Map<String, Object> OA2Attributes) {
        this(username, password, social, authorities);

        this.OA2Attributes = OA2Attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return OA2Attributes;
    }
}
