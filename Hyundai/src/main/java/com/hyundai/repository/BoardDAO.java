package com.hyundai.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.entity.BoardDTO;
import com.hyundai.entity.Criteria;
import com.hyundai.entity.UploadResultDTO;

@Mapper
public interface BoardDAO {
	void insertArticle(BoardDTO boardDTO);
	
	void insertFile(UploadResultDTO uploadResultDTO);

	List<BoardDTO> getArticleList();
	
	List<BoardDTO> getArticleListWithPaging(Criteria cri);
	
	int countTotal();
	
	BoardDTO getArticle(long bno);

	void deleteArticle(long bno);

	void updateArticle(BoardDTO boardDTO);
	
	void updateReadCount(long bno);
	
	long getSeqBoard();
	
	List<UploadResultDTO> getFileList(long bno);
}