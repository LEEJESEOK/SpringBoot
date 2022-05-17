package com.hyundai.security.handler;

import com.hyundai.security.dto.AuthUserDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LEE JESEOK
 * @deprecated
 */
@Log4j2
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    PasswordEncoder passwordEncoder;
    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public LoginSuccessHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // 인증 성공시 호출
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("Authentication Success");

        AuthUserDTO authUserDTO = (AuthUserDTO) authentication.getPrincipal();
        int social = authUserDTO.getSocial();
        // 기본 비밀번호 1111 비교
        boolean matchResult = passwordEncoder.matches("1111", authUserDTO.getPassword());

        if ((social == 1) && (matchResult == true)) {
            log.info("send redirect");
            redirectStrategy.sendRedirect(request, response, "/modify?from=social");
        }
    }
}
