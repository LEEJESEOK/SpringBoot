package com.hyundai.controller;

import com.hyundai.security.dto.AuthUserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LEE JESEOK
 */
@RequestMapping("/")
@Controller
public class MainController {
    @RequestMapping("")
    public String root() {
        return "main";
    }

    @RequestMapping("/main")
    public String main() {
        return "redirect:/";
    }

    @RequestMapping("/mypage")
    public String mypage(@AuthenticationPrincipal AuthUserDTO authUserDTO) {
        return "mypage";
    }

    @PreAuthorize("has")
    @GetMapping("/modify")
    public void modify(@AuthenticationPrincipal AuthUserDTO authUserDTO) {

    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("login")
    public void login() {

    }
}
