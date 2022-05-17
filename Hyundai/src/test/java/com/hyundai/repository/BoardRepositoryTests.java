package com.hyundai.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.entity.BoardDTO;
import com.hyundai.entity.Criteria;
import com.hyundai.entity.UploadResultDTO;

/**
 * 게시판 관련 Repository 테스트
 * @author 이지은
 *
 */
@SpringBootTest
public class BoardRepositoryTests {
	@Autowired
	private BoardRepository board;
	
	// 게시물 등록 테스트
	@Test
	public void testInsertArticle() {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBname("repository_test");
		boardDTO.setBtitle("repository_test");
		boardDTO.setBcontent("repository_test");
		boardDTO.setUemail("repository_test");
		
		board.insertArticle(boardDTO);
	}
	
	// 첨부 파일 등록 테스트
	@Test
	public void testInsertFile() {
		UploadResultDTO file = new UploadResultDTO();
		file.setBno(2);
		file.setFname("repository_test");
		file.setUuid("repository_test");
		file.setFfolder("repository_test");
		
		board.insertFile(file);
	}
	
	// 게시판 조회(페이징 미처리) 테스트
	@Test
	public void testGetArticleList() {
		board.getArticleList().forEach(i->System.out.println(i));
	}
	
	// 게시판 조회(페이징 처리) 테스트
	@Test
	public void testGetArticleListWithPaging() {
		Criteria cri = new Criteria(2, 5);
		board.getArticleListWithPaging(cri).forEach(i->System.out.println(i));
	}
	
	// 전체 게시물 수 조회 테스트
	@Test
	public void countTotal() {
		int total = board.countTotal();
		System.out.println(total);
	}
	
	// 게시물 조회 테스트
	@Test
	public void testGetArticle() {
		BoardDTO boardDTO = board.getArticle(1);
		System.out.println(boardDTO);
	}
	
	// 게시물 내 첨부 파일 테스트
	@Test
	public void testGetFileList() {
		long bno = 1;
		board.getFileList(bno).forEach(i->System.out.println(i));
	}
	
	// 조회수 증가 테스트
	@Test
	public void testUpdateReadCount() {
		board.updateReadCount(1);
		board.updateReadCount(1);
		board.updateReadCount(1);
	}
	
	// 게시물 삭제 테스트
	@Test
	public void testDeleteArticle() {
		board.deleteArticle(1);
	}
	
	// 게시물 수정 테스트
	@Test
	public void testUpdateArticle() {
		BoardDTO boardDTO = new BoardDTO();	
		boardDTO.setBname("repository_test");
		boardDTO.setBtitle("repository_test");
		boardDTO.setBcontent("repository_test");
		boardDTO.setBno(1);
		
		board.updateArticle(boardDTO);
	}
	
	// 현재 글 번호 조회 테스트
	@Test
	public void testSeqBoard() {
		long seq = board.getSeqBoard();
		System.out.println(seq);
	}
	
	// 첨부 파일 삭제 테스트
	@Test
	public void testDeleteFile() {
		board.deleteFile(4);
	}
}
