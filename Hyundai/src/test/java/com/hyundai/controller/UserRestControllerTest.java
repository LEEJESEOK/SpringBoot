package com.hyundai.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author LEE JESEOK
 */
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Log4j2
class UserRestControllerTest {

    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = Jackson2ObjectMapperBuilder.json().build();
    }

    @Test
    void isDuplicate() throws Exception {
        String url = "/isDuplicate";
        String email = "test1@hyundai.com";

        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        String paramsBody = objectMapper.valueToTree(params).toString();

        String contentsBody = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(paramsBody))
                .andExpect((status().isOk()))
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(contentsBody);
        Map<String, Object> resultMap = objectMapper.treeToValue(jsonNode, Map.class);

        assertEquals("true", resultMap.get("result"));
    }

    @Test
    void signup() {

    }

    @WithMockUser(username = "test1@hyundai.com", password = "1111", roles = {"USER"})
    @Test
    void updateUser() throws Exception {
        String url = "/userInfo";
        String email = "test1@hyundai.com";
        String password = "1111";
        String name = "업데이트";

        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        params.put("name", name);
        String paramsBody = objectMapper.valueToTree(params).toString();

        String contentsBody = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(paramsBody))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(contentsBody);
        Map<String, Object> resultMap = objectMapper.treeToValue(jsonNode, Map.class);

        assertEquals("success", (String) resultMap.get("result"));
    }
}