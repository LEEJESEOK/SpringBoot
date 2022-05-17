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

    @RequestMapping("")
    public String root() {
        return "main";
    }

    @RequestMapping("/main")
    public String main() {
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal AuthUserDTO authUserDTO, Model model) {
        model.addAttribute("authUser", authUserDTO);
        log.info("model : " + model);
        model.addAttribute("user", userService.getUserInfoByEmail(authUserDTO.getUsername()));
        return "profile";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @RequestMapping("/signupComplete")
    public String signupComplete() {
        return "signupComplete";
    }
}
