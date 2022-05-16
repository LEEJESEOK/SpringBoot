package com.hyundai.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.entity.BoardDTO;
import com.hyundai.entity.UploadResultDTO;

@Mapper
public interface BoardDAO {
	void insertArticle(BoardDTO boardDTO);
	
	void insertFile(UploadResultDTO uploadResultDTO);

	List<BoardDTO> getArticleList();
	
	BoardDTO getArticle(long bno);

	void deleteArticle(long bno);

	void updateArticle(BoardDTO boardDTO);
	
	void updateReadCount(long bno);
	
	long getSeqBoard();
	
	List<UploadResultDTO> getFileList(long bno);
}