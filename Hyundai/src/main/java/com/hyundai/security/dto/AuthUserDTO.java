package com.hyundai.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

/**
 * @author LEE JESEOK
 */
@Getter
@Setter
@ToString
public class AuthUserDTO extends User {

    String email;
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
}
