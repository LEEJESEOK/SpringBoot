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

    @RequestMapping(value = "/isDuplicate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> isDuplicate(@RequestBody Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();

        result.put("result", userService.isDuplicatedByEmail((String) params.get("email")) ? "true" : "false");
        return result;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> signin(@RequestBody Map<String, Object> params) {
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

    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUser(@RequestBody Map<String, Object> params) {
        log.info("updateProfile");
        for (String key : params.keySet())
            log.info(key + " : " + params.get(key));

        Map<String, Object> result = new HashMap<>();

        try {
            User user = new User();
            user.setEmail((String) params.get("email"));
            user.setPassword((String) params.get("password"));
            user.setName((String) params.get("name"));

            userService.updateUserInfo(user);
            result.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();

            result.put("result", "fail");
        }

        return result;
    }
}
