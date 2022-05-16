package com.hyundai.service;

import java.util.List;

import com.hyundai.entity.BoardDTO;
import com.hyundai.entity.UploadResultDTO;

public interface BoardService {
	void insertArticle(BoardDTO boardDTO);
	
	void insertFile(UploadResultDTO uploadResultDTO);

	List<BoardDTO> getArticleList();
	
	BoardDTO getArticle(long bno);

	void deleteArticle(long bno);
	
	void updateArticle(BoardDTO boardDTO);
	
	BoardDTO getDetail(long bno);
	
	long getSeqBoard();
	
	List<UploadResultDTO> getFileList(long bno);
}
