package com.hyundai.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.entity.BoardDTO;

@SpringBootTest
public class BoardServiceTests {
	
	@Autowired
	private BoardService boardService;
	
	@Test
	public void testGetArticleList() {
		boardService.getArticleList().forEach(i->System.out.println(i));
	}
	
	@Test
	public void testInsertArticle() {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBname("service insert test");
		boardDTO.setBtitle("service insert test");
		boardDTO.setBcontent("service insert test");
		boardService.insertArticle(boardDTO);
	}
	
	@Test
	public void testGetArticle() {
		BoardDTO boardDTO = boardService.getArticle(2);
		System.out.println(boardDTO);
	}
	
	@Test
	public void testUpdateArticle() {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBname("service update test");
		boardDTO.setBtitle("service update test");
		boardDTO.setBcontent("service update test");
		boardDTO.setBno(2);
		boardService.updateArticle(boardDTO);
	}
	
	@Test
	public void testDeleteArticle() {
		boardService.deleteArticle(1);
	}
		
}
