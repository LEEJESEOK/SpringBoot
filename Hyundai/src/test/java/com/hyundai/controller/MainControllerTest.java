package com.hyundai.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author LEE JESEOK
 */
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Log4j2
class MainControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void root() throws Exception {
        String url = "/";
        String viewName = mockMvc.perform(get(url)).andReturn().getModelAndView().getViewName();

        assertEquals("main", viewName);
    }

    @Test
    void main() throws Exception {
        String url = "/main";
        String viewName = mockMvc.perform(get(url)).andReturn().getModelAndView().getViewName();

        assertEquals("redirect:/", viewName);
    }

    @WithMockUser(username = "test", password = "1111", roles = "USER")
    @Test
    void profile() throws Exception {
        String url = "/profile";

        MvcResult resultActions = mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk()).andReturn();

//        mockMvc.perform(formLogin().user("test").password("1111")).andDo(print());
//                get(url)).andDo(print()).andReturn().getModelAndView();

//        assertEquals("profile", viewName);
    }

    @Test
    void loginForm() throws Exception {
        String url = "/login";
        String viewName = mockMvc.perform(get(url)).andReturn().getModelAndView().getViewName();

        assertEquals("login", viewName);
    }

    @Test
    void signupForm() throws Exception {
        String url = "/signup";
        String viewName = mockMvc.perform(get(url)).andReturn().getModelAndView().getViewName();

        assertEquals("signup", viewName);
    }

    @Test
    void signupComplete() throws Exception {
        String url = "/signupComplete";
        String viewName = mockMvc.perform(get(url)).andReturn().getModelAndView().getViewName();

        assertEquals("signupComplete", viewName);
    }

//    @Getter
//    @Setter
//    public static class MockSecurityContext implements SecurityContext {
//        private Authentication authentication;
//
//        public MockSecurityContext(Authentication authentication) {
//            this.authentication = authentication;
//        }
//    }
}