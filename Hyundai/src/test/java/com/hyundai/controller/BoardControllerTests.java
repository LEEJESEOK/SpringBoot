package com.hyundai.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * 게시판 페이지 매핑 컨트롤러 테스트
 * @author 이지은
 *
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.hyundai"})
@SpringBootTest
@AutoConfigureMockMvc
public class BoardControllerTests {
	
	@Autowired private MockMvc mvc;
	
	@DisplayName("게시판 조회 페이지 테스트")
	@Test
	public void testList() throws Exception {
		mvc.perform(get("/board/list"))
			.andExpect(status().isOk())
			.andExpect(view().name("board/list"))
			.andDo(print());
	}
	
	@DisplayName("게시물 등록 페이지 테스트")
	@Test
	public void testInsert() throws Exception {
		mvc.perform(get("/board/insert"))
			.andExpect(status().isOk())
			.andExpect(view().name("board/insert"))
			.andDo(print());
	}
	
	@DisplayName("게시물 조회 페이지 테스트")
	@Test
	public void testDetail() throws Exception {
		mvc.perform(get("/board/detail").param("bno", "12"))
			.andExpect(status().isOk())
			.andExpect(view().name("board/detail"))
			.andDo(print());
	}
	
	@DisplayName("게시판 수정 페이지 테스트")
	@Test
	public void testUpdate() throws Exception {
		mvc.perform(get("/board/update").param("bno", "4"))
			.andExpect(status().isOk())
			.andExpect(view().name("board/update"))
			.andDo(print());
	}
	
}
