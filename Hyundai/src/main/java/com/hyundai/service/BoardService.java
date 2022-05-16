package com.hyundai.service;

import java.util.List;

import com.hyundai.entity.BoardDTO;
import com.hyundai.entity.Criteria;
import com.hyundai.entity.UploadResultDTO;

/**
 * 게시판 관련 Service
 * @author 이지은
 *
 */
public interface BoardService {
	
	void insertArticle(BoardDTO boardDTO);  // 게시물 등록
	void insertFile(UploadResultDTO uploadResultDTO);  // 첨부 파일 등록

	List<BoardDTO> getArticleList(Criteria cri);  // 게시판 조회(페이징 처리)
	int getTotal();  // 전체 게시물 수 조회
	
	BoardDTO getArticle(long bno);  // 게시물 조회
	List<UploadResultDTO> getFileList(long bno);  // 게시물 내 첨부 파일 목록 조회

	void deleteArticle(long bno);  // 게시물 삭제
	
	void updateArticle(BoardDTO boardDTO);  // 게시물 수정
	BoardDTO getDetail(long bno);  // 게시물 조회 (수정용)
	long getSeqBoard();  // 현재 글 번호 조회
}
