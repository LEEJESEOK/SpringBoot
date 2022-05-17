package com.hyundai.controller;

import com.hyundai.security.dto.AuthUserDTO;
import com.hyundai.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LEE JESEOK
 */
@RequestMapping("/")
@Controller
@Log4j2
public class MainController {
    @Autowired
    UserService userService;

    // 메인화면
    @RequestMapping("")
    public String root() {
        return "main";
    }

    // 메인화면으로 리다이렉트
    @RequestMapping("/main")
    public String main() {
        return "redirect:/";
    }

    // 사용자 정보 페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal AuthUserDTO authUserDTO, Model model) {
        model.addAttribute("authUser", authUserDTO);
        log.info("model : " + model);
        model.addAttribute("user", userService.getUserInfoByEmail(authUserDTO.getUsername()));
        return "profile";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    // 회원가입 완료 페이지
    @RequestMapping("/signupComplete")
    public String signupComplete() {
        return "signupComplete";
    }
}
