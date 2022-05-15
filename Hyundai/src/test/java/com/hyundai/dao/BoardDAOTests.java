package com.hyundai.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.entity.BoardDTO;
import com.hyundai.repository.BoardDAO;

@SpringBootTest
public class BoardDAOTests {

	@Autowired
	private BoardDAO boardDAO;
	
	@Test
	public void testGetArticleList() {
		boardDAO.getArticleList().forEach(i->System.out.println(i));
	}
	
	@Test
	public void testInsertArticle() {
		BoardDTO boardDTO = new BoardDTO();	
		boardDTO.setBname("DAO insert test");
		boardDTO.setBtitle("DAO insert test");
		boardDTO.setBcontent("DAO insert test");
		boardDAO.insertArticle(boardDTO);
		System.out.println(boardDTO);
	}
	
	@Test
	public void testGetArticle() {
		BoardDTO boardDTO = boardDAO.getArticle(1);
		System.out.println(boardDTO);
	}
	
	@Test
	public void testUpdateArticle() {
		BoardDTO boardDTO = new BoardDTO();	
		boardDTO.setBname("DAO update test");
		boardDTO.setBtitle("DAO update test");
		boardDTO.setBcontent("DAO update test");
		boardDTO.setBno(2);
		boardDAO.updateArticle(boardDTO);
		System.out.println(boardDTO);
	}
	
	@Test
	public void testDeleteArticle() {
		boardDAO.deleteArticle(3);
	}
	
	@Test
	public void testUpdateReadCount() {
		boardDAO.updateReadCount(1);
		boardDAO.updateReadCount(1);
		boardDAO.updateReadCount(1);
	}
		
}
