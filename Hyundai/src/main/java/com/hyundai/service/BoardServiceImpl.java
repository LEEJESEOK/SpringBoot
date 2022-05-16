package com.hyundai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundai.entity.BoardDTO;
import com.hyundai.entity.UploadResultDTO;
import com.hyundai.repository.BoardDAO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public void insertArticle(BoardDTO boardDTO){
		boardDAO.insertArticle(boardDTO);
	}

	@Override
	public List<BoardDTO> getArticleList(){
		return boardDAO.getArticleList();
	}
	
	@Transactional // 게시물 조회와 조회수 증가를 함께 처리
	@Override
	public BoardDTO getArticle(long bno) {
		BoardDTO boardDTO = boardDAO.getArticle(bno);
		boardDAO.updateReadCount(bno);
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

	@Override
	public BoardDTO getDetail(long bno) {
		BoardDTO boardDTO = boardDAO.getArticle(bno);
		return boardDTO;
	}

	@Override
	public void insertFile(UploadResultDTO uploadResultDTO) {
		boardDAO.insertFile(uploadResultDTO);
	}
	
	@Override
	public long getSeqBoard() {
		return boardDAO.getSeqBoard();
	}

	@Override
	public List<UploadResultDTO> getFileList(long bno) {
		return boardDAO.getFileList(bno);
	}

}
