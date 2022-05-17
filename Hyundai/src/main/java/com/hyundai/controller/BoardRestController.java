package com.hyundai.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.entity.BoardDTO;
import com.hyundai.entity.Criteria;
import com.hyundai.service.BoardService;

/**
 * 게시판 페이지 REST 컨트롤러
 * @author 이지은
 *
 */
@RestController
public class BoardRestController {
	
	@Autowired
	private BoardService boardService;
	
	/**
	 * 이지은 작성
	 * 게시판 조회
	 * @param pageNum: 게시판 페이지 번호
	 */
	@GetMapping("/board/{pageNum}")
	public ResponseEntity<List<BoardDTO>> list(@PathVariable("pageNum") Optional<Integer> pageNum){

		int pageNumber = pageNum.orElse(1);  // 페이지 기본값: 1
		
		ResponseEntity<List<BoardDTO>> entry = null;
		try {  // 해당 페이지의 게시물 목록 데이터 조회
			entry = new ResponseEntity<List<BoardDTO>>(boardService.getArticleList(new Criteria(pageNumber, 5)), HttpStatus.OK);
			
		} catch(Exception e) {  // 목록 조회 실패할 경우
			e.printStackTrace();
			entry = new ResponseEntity<List<BoardDTO>>(HttpStatus.BAD_REQUEST);
		}
		return entry;
	}
	
	/**
	 * 이지은 작성
	 * 게시물 등록
	 * @param boardDTO: 등록할 게시물 내용
	 */
	@PostMapping("/articles")
	public ResponseEntity<String> register(@RequestBody BoardDTO boardDTO){
		
		ResponseEntity<String> entry = null;
		try {  // 전달받은 게시물 데이터 등록
			boardService.insertArticle(boardDTO);
			entry = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch(Exception e) {  // 등록 실패할 경우
			e.printStackTrace();
			entry = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entry;
	}
	
	/**
	 * 이지은 작성
	 * 게시물 조회
	 * @param bno: 조회할 게시물 번호
	 */
	@GetMapping("/articles/{bno}")
	public ResponseEntity<BoardDTO> detail(@PathVariable("bno") int bno){
		
		ResponseEntity<BoardDTO> entry = null;
		try {  // 해당 번호의 게시물 조회
			entry = new ResponseEntity<BoardDTO>(boardService.getArticle(bno), HttpStatus.OK);
			
		} catch(Exception e) {  // 조회 실패할 경우
			e.printStackTrace();
			entry = new ResponseEntity<BoardDTO>(HttpStatus.BAD_REQUEST);
		}
		return entry;
	}
	
	/**
	 * 이지은 작성
	 * 게시물 수정
	 * @param bno: 수정할 게시물 번호
	 * @param boardDTO: 수정할 게시물 내용
	 */
	@PutMapping("/articles/{bno}")
	public ResponseEntity<String> update(@PathVariable("bno") int bno, @RequestBody BoardDTO boardDTO){
		
		ResponseEntity<String> entry = null;
		try {  // 해당 번호의 게시물 수정
			boardDTO.setBno(bno);
			boardService.updateArticle(boardDTO);
			entry = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch(Exception e) {  // 수정 실패할 경우
			e.printStackTrace();
			entry = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entry;
	}
	
	/**
	 * 이지은 작성
	 * 게시물 삭제
	 * @param bno: 삭제할 게시물 번호
	 */
	@DeleteMapping("/articles/{bno}")
	public ResponseEntity<String> delete(@PathVariable("bno") int bno){
		
		ResponseEntity<String> entry = null;
		try {  // 해당 번호의 게시물 삭제
			boardService.deleteArticle(bno);
			entry = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch(Exception e) { // 삭제 실패할 경우
			e.printStackTrace();
			entry = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entry;
	}
	
	/**
	 * 이지은 작성
	 * 게시물 내용 조회
	 * 수정 시 조회수 증가 방지
	 * @param bno: 수정할 게시물 기존 내용을 부르기 위한 글 번호
	 */
	@GetMapping("/details/{bno}")
	public ResponseEntity<BoardDTO> reload(@PathVariable("bno") int bno){
		
		ResponseEntity<BoardDTO> entry = null;
		try {  // 수정할 게시물 기존 내용 조회
			entry = new ResponseEntity<BoardDTO>(boardService.getDetail(bno), HttpStatus.OK);
			
		} catch(Exception e) {  // 수정 실패할 경우
			e.printStackTrace();
			entry = new ResponseEntity<BoardDTO>(HttpStatus.BAD_REQUEST);
		}
		return entry;
	}
}