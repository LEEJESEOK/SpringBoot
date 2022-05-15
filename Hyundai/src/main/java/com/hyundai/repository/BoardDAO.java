package com.hyundai.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.entity.BoardDTO;

@Mapper
public interface BoardDAO {
	void insertArticle(BoardDTO boardDTO);

	List<BoardDTO> getArticleList();
	
	BoardDTO getArticle(long bno);

	void deleteArticle(long bno);

	void updateArticle(BoardDTO boardDTO);
	
	void updateReadCount(long bno);
}
