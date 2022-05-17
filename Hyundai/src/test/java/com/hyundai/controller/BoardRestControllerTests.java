package com.hyundai.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundai.service.BoardService;

/**
 * 게시판 페이지 REST 컨트롤러 테스트
 * @author 이지은
 *
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.hyundai"})
@SpringBootTest
@AutoConfigureMockMvc
public class BoardRestControllerTests {
	
	@Autowired private MockMvc mvc;
	@MockBean private BoardService boardService;
	@Autowired private ObjectMapper objectMapper;
	
	@DisplayName("게시판 조회 테스트")
	@Test
	public void testList() throws Exception {
		
		mvc.perform(get("/board/1"))
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	@DisplayName("게시물 등록 테스트")
	@Test
	public void testRegister() throws Exception {
		
		Map<String, String> input = new HashMap<>();
		input.put("bname", "이지은");
		input.put("btitle", "글 제목");
		input.put("bcontent", "글 내용");
		
		mvc.perform(post("/articles")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input)))
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	@DisplayName("게시물 조회 테스트")
	@Test
	public void testDetail() throws Exception {
		
		mvc.perform(get("/articles/4"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@DisplayName("게시물 수정 테스트")
	@Test
	public void testUpdate() throws Exception{
		
		Map<String, String> input = new HashMap<>();
		input.put("bname", "이지은_수정");
		input.put("btitle", "글 제목_수정");
		input.put("bcontent", "글 내용_수정");
		
		mvc.perform(put("/articles/4")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input)))
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	@DisplayName("게시물 삭제 테스트")
	@Test
	public void testDelete() throws Exception{
		
		mvc.perform(delete("/articles/2"))
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	@DisplayName("게시물 조회(수정용) 테스트")
	@Test
	public void testReload() throws Exception{
		
		mvc.perform(get("/details/2"))
			.andExpect(status().isOk())
			.andDo(print());
	}
}
