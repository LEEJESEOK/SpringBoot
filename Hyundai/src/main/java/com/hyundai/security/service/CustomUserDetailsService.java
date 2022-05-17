package com.hyundai.security.service;

import com.hyundai.entity.User;
import com.hyundai.entity.UserRoleSet;
import com.hyundai.repository.UserRepository;
import com.hyundai.security.dto.AuthUserDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * DB 등록 사용자 서비스
 *
 * @author LEE JESEOK
 */
@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // email=username 사용자 정보, 권한 정보
        User user = userRepository.selectUserByEmail(username);
        List<UserRoleSet> userRoleSets = userRepository.selectUserRoleSetsByEmail(username);

        // UserRoleSet -> GrantedAuthority 변환
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRoleSet userRoleSet : userRoleSets) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRoleSet.getRoleSet()));
        }

        AuthUserDTO authUserDTO = new AuthUserDTO(user.getEmail(), user.getPassword(), user.getSocial(), authorities);
        authUserDTO.setPassword(user.getPassword());
        authUserDTO.setName(user.getName());


        return authUserDTO;
    }


}
