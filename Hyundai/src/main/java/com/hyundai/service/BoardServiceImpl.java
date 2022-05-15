package com.hyundai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.entity.BoardDTO;
import com.hyundai.repository.BoardDAO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public void insertArticle(BoardDTO boardDTO){
		log.info("insert...");
		boardDAO.insertArticle(boardDTO);
	}

	@Override
	public List<BoardDTO> getArticleList(){
		return boardDAO.getArticleList();
	}

	@Override
	public BoardDTO getArticle(long bno) {
		BoardDTO boardDTO = boardDAO.getArticle(bno);
		return boardDTO;
	}

	@Override
	public void deleteArticle(long bno) {
		boardDAO.deleteArticle(bno);
	}
	
	@Override
	public void updateArticle(BoardDTO boardDTO) {
		boardDAO.updateArticle(boardDTO);
	}
}
