package com.hyundai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hyundai.entity.Criteria;
import com.hyundai.entity.PageDTO;
import com.hyundai.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/list")
	public String list(@RequestParam("pageNum") long pageNum, Criteria cri, Model model){
		
		int total = boardService.getTotal();
		model.addAttribute("pageMaker", new PageDTO(cri, total));  // 페이징 처리를 위한 데이터 추가
		
		return "board/list";
	}
	
	@GetMapping("/board/insert")
	public String insert(Model model){
		long bno = boardService.getSeqBoard();
		model.addAttribute("bno", bno); // 현재 등록할 게시물의 글 번호
		return "board/insert";
	}
	
	@GetMapping("/board/detail")
	public String detail(@RequestParam("bno") long bno, Model model) {
		model.addAttribute("bno", bno);
		return "board/detail";
	}
	
	@GetMapping("/board/update")
	public String update(@RequestParam("bno") long bno, Model model) {
		model.addAttribute("bno", bno);
		return "board/update";
	}
	
	@GetMapping("/board/uploadEx")
	public void uploadEx() {
		
	}
}
