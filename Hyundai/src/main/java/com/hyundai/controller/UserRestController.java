package com.hyundai.controller;

import com.hyundai.entity.User;
import com.hyundai.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LEE JESEOK
 */
@RestController
@Log4j2
public class UserRestController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> signin(@RequestBody Map<String, Object> params) {
        log.info("signin+++");

        for (String key : params.keySet())
            log.info(key + " : " + params.get(key));

        User user = new User();
        user.setEmail((String) params.get("email"));
        user.setPassword((String) params.get("password"));
        user.setName((String) params.get("name"));


        Map<String, Object> result = new HashMap<>();
        result.put("result", userService.signin(user) ? "success" : "fail");
        return result;
    }
}
