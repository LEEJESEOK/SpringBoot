package com.hyundai.service;

import java.util.List;

import com.hyundai.entity.BoardDTO;

public interface BoardService {
	void insertArticle(BoardDTO boardDTO);

	List<BoardDTO> getArticleList();
	
	BoardDTO getArticle(long bno);

	void deleteArticle(long bno);
	
	void updateArticle(BoardDTO boardDTO);
	
	BoardDTO getDetail(long bno);
}
