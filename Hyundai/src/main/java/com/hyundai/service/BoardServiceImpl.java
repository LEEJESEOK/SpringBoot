package com.hyundai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundai.entity.BoardDTO;
import com.hyundai.entity.Criteria;
import com.hyundai.entity.UploadResultDTO;
import com.hyundai.repository.BoardRepository;

/**
 * 게시판 관련 Service
 * @author 이지은
 *
 */
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository board;
	
	// 게시물 등록
	@Override
	public void insertArticle(BoardDTO boardDTO){
		board.insertArticle(boardDTO);
	}
	
	// 첨부 파일 등록
	@Override
	public void insertFile(UploadResultDTO uploadResultDTO) {
		board.insertFile(uploadResultDTO);
	}
	
	// 게시판 조회(페이징 처리)
	@Override
	public List<BoardDTO> getArticleList(Criteria cri){
		return board.getArticleListWithPaging(cri);
	}
	
	// 전체 게시물 수 조회
	@Override
	public int getTotal() {
		int count = board.countTotal();
		return count;
	}
		
	// 게시물 조회
	@Transactional // 게시물 조회와 조회수 증가를 함께 처리
	@Override
	public BoardDTO getArticle(long bno) {
		BoardDTO boardDTO = board.getArticle(bno);
		board.updateReadCount(bno);  // 조회수 증가
		return boardDTO;
	}
	
	// 게시물 내 첨부 파일 목록 조회
	@Override
	public List<UploadResultDTO> getFileList(long bno) {
		return board.getFileList(bno);
	}
		
	// 게시물 삭제
	@Override
	public void deleteArticle(long bno) {
		board.deleteArticle(bno);
	}
	
	// 게시물 수정
	@Override
	public void updateArticle(BoardDTO boardDTO) {
		board.updateArticle(boardDTO);
	}
	
	// 게시물 조회 (수정용)
	@Override
	public BoardDTO getDetail(long bno) {
		BoardDTO boardDTO = board.getArticle(bno);
		return boardDTO;
	}
	
	// 현재 글 번호 조회
	@Override
	public long getSeqBoard() {
		return board.getSeqBoard();
	}
	
	// 첨부 파일 삭제
	@Override
	public void deleteFile(long fno) {
		board.deleteFile(fno);
	}
}
