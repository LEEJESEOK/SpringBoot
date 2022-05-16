package com.hyundai.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.entity.BoardDTO;
import com.hyundai.entity.Criteria;
import com.hyundai.entity.UploadResultDTO;

/**
 * 게시판 관련 DAO
 * @author jleeun
 *
 */
@Mapper
public interface BoardDAO {
	
	void insertArticle(BoardDTO boardDTO);  // 게시물 등록 
	
	void insertFile(UploadResultDTO uploadResultDTO);  // 첨부 파일 등록

	List<BoardDTO> getArticleList();  // 게시판 조회(페이징 미처리)
	
	List<BoardDTO> getArticleListWithPaging(Criteria cri);  // 게시판 조회(페이징 처리) 
	
	int countTotal();  // 전체 게시물 수 조회
	
	BoardDTO getArticle(long bno);  // 게시물 조회					

	void deleteArticle(long bno);  // 게시물 삭제

	void updateArticle(BoardDTO boardDTO);  // 게시물 수정
	
	void updateReadCount(long bno);  // 조회수 증가
	
	long getSeqBoard();  // 현재 글 번호 조회
	
	List<UploadResultDTO> getFileList(long bno);  // 게시물 내 첨부 파일 목록 조회
}