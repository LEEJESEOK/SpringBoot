package com.hyundai.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundai.service.BoardService;

/**
 * 파일 업로드 컨트롤러 테스트
 * @author 이지은
 *
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.hyundai"})
@SpringBootTest
@AutoConfigureMockMvc
public class UploadControllerTests {
	
	@Autowired private MockMvc mvc;
	@MockBean private BoardService boardService;
	@Autowired private ObjectMapper objectMapper;
	
	@DisplayName("파일 업로드 테스트")
	@Test
	public void uploadFiles() throws Exception {
		
		Map<String, String> input = new HashMap<>();
		input.put("bno", "4");
		input.put("fname", "파일 이름");
		input.put("uuid", "UUID");
		input.put("ffolder", "저장할 폴더");
		
		mvc.perform(post("/uploadAjax/4")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input)))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn()
			.getResponse()
			.getContentAsString();
	}
	
	@DisplayName("파일 목록 조회 테스트")
	@Test
	public void testFileList() throws Exception {
		
		mvc.perform(get("/files/4"))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn()
			.getResponse()
			.getContentAsString();
	}
	
	@DisplayName("이미지 출력 테스트")
	@Test
	public void testGetFile() throws Exception {
		String fileName = "2022\\05\\17/1e152972-7f8f-402a-9dce-54b651319b59_캡처2.PNG";
		
		mvc.perform(get("/display").param("fileName", fileName))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn()
			.getResponse()
			.getContentAsString();
	}
	
	@DisplayName("파일 삭제 테스트")
	@Test
	public void testRemoveFile() throws Exception {
		
		MultiValueMap<String, String> input = new LinkedMultiValueMap<>();
		String fileName = "2022\\05\\17/1e152972-7f8f-402a-9dce-54b651319b59_캡처2.PNG";
		input.add("fileName", fileName);
		input.add("fno", "9");
		
		mvc.perform(post("/removeFile").params(input))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn()
			.getResponse()
			.getContentAsString();
	}
}
