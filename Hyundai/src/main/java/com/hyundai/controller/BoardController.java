package com.hyundai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	
	@GetMapping("/board/list")
	public String list(){
		return "board/list";
	}
	
	@GetMapping("/board/insert")
	public String insert(){
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
}