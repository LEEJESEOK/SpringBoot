package com.hyundai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hyundai.entity.Criteria;
import com.hyundai.entity.PageDTO;
import com.hyundai.service.BoardService;

/**
 * 게시판 페이지 매핑 컨트롤러
 * @author 이지은
 *
 */
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/**
	 * 이지은 작성
	 * 게시판 조회 페이지
	 */
	@GetMapping("/board/list")
	public String list(Criteria cri, Model model){
		
		int total = boardService.getTotal();  // 페이징 처리를 위한 전체 게시물 수 계산
		model.addAttribute("pageMaker", new PageDTO(cri, total));  // 페이징 처리를 위한 데이터 추가
		
		return "board/list";
	}

	/**
	 * 이지은 작성
	 * 게시물 등록 페이지
	 */
	@GetMapping("/board/insert")
	public String insert(Model model){
		
		long bno = boardService.getSeqBoard();
		model.addAttribute("bno", bno);  // 현재 등록할 게시물의 글 번호
		
		return "board/insert";
	}
	
	/**
	 * 이지은 작성
	 * 게시물 조회 페이지
	 */
	@GetMapping("/board/detail")
	public String detail(@RequestParam("bno") long bno, Model model) {
		
		model.addAttribute("bno", bno);
		return "board/detail";
	}
	
	/**
	 * 이지은 작성
	 * 게시물 수정 페이지
	 */
	@GetMapping("/board/update")
	public String update(@RequestParam("bno") long bno, Model model) {
		
		model.addAttribute("bno", bno);
		return "board/update";
	}
}
