package com.hyundai.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.entity.BoardDTO;
import com.hyundai.entity.Criteria;
import com.hyundai.entity.UploadResultDTO;

/**
 * 게시판 관련 Service 테스트
 * @author 이지은
 *
 */
@SpringBootTest
public class BoardServiceTests {
	
	@Autowired
	private BoardService boardService;
	
	// 게시물 등록 테스트
	@Test
	public void testInsertArticle() {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBname("service_test");
		boardDTO.setBtitle("service_test");
		boardDTO.setBcontent("service_test");
		boardDTO.setUemail("service_test");
		
		boardService.insertArticle(boardDTO);
	}
	
	// 첨부 파일 등록 테스트
	@Test
	public void testInsertFile() {
		UploadResultDTO file = new UploadResultDTO();
		file.setBno(2);
		file.setFname("service_test");
		file.setUuid("service_test");
		file.setFfolder("service_test");
		
		boardService.insertFile(file);
	}
	
	// 게시판 조회(페이징 처리) 테스트
	@Test
	public void testGetArticleList() {
		boardService.getArticleList(new Criteria(2, 5)).forEach(i->System.out.println(i));
	}
	
	// 전체 게시물 수 조회 테스트
	@Test
	public void testGetTotal() {
		int total = boardService.getTotal();
		System.out.println(total);
	}
	
	// 게시물 조회 테스트
	@Test
	public void testGetArticle() {
		BoardDTO boardDTO = boardService.getArticle(2);
		System.out.println(boardDTO);  // 조회수도 확인
	}
	
	// 게시물 내 첨부 파일 목록 조회 테스트
	@Test
	public void testGetFileList() {
		long bno = 4;
		boardService.getFileList(bno).forEach(i->System.out.println(i));
	}
	
	// 게시물 삭제 테스트
	@Test
	public void testDeleteArticle() {
		boardService.deleteArticle(1);
	}
	
	// 게시물 수정 테스트
	@Test
	public void testUpdateArticle() {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBname("service_test");
		boardDTO.setBtitle("service_test");
		boardDTO.setBcontent("service_test");
		boardDTO.setBno(2);
		
		boardService.updateArticle(boardDTO);
	}
	
	// 게시물 조회 (수정용) 테스트
	@Test
	public void testGetDetail() {
		BoardDTO boardDTO = boardService.getDetail(2);
		System.out.println(boardDTO);
	}
	
	// 현재 글 번호 조회 테스트
	@Test
	public void testGetSeqBoard() {
		long seq = boardService.getSeqBoard();
		System.out.println(seq);
	}
	
	// 첨부 파일 삭제 테스트
	@Test
	public void testDeleteFile() {
		boardService.deleteFile(4);
	}
	
}
