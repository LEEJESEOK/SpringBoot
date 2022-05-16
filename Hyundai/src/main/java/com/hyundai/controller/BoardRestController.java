package com.hyundai.controller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hyundai.entity.BoardDTO;
import com.hyundai.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class BoardRestController {
	
	@Autowired
	private BoardService boardService;
	
	// 게시물 목록 조회
	@GetMapping("/articles")
	public ResponseEntity<List<BoardDTO>> list(){
		ResponseEntity<List<BoardDTO>> entry = null;
		log.info("BoardRestController: /list.....");
		try {
			entry = new ResponseEntity<List<BoardDTO>>(boardService.getArticleList(), HttpStatus.OK);
			log.info(entry);
		} catch(Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<List<BoardDTO>>(HttpStatus.BAD_REQUEST);
		}
		return entry;
	}
	
	// 게시물 등록
	@PostMapping("/articles")
	public ResponseEntity<String> register(@RequestBody BoardDTO boardDTO){
		ResponseEntity<String> entry = null;
		log.info("BoardRestController: /insert.....");
		try {
			boardService.insertArticle(boardDTO);
			entry = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			log.info(entry);
		} catch(Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			log.info(entry);
		}
		return entry;
	}
	
	// 게시물 상세 조회
	@GetMapping("/articles/{bno}")
	public ResponseEntity<BoardDTO> detail(@PathVariable("bno") int bno){
		ResponseEntity<BoardDTO> entry = null;
		log.info("BoardRestController: /detail.....");
		try {
			entry = new ResponseEntity<BoardDTO>(boardService.getArticle(bno), HttpStatus.OK);
			log.info(entry);
		} catch(Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<BoardDTO>(HttpStatus.BAD_REQUEST);
		}
		return entry;
	}
	
	// 게시물 수정
	@PutMapping("/articles/{bno}")
	public ResponseEntity<String> update(@PathVariable("bno") int bno, @RequestBody BoardDTO boardDTO){
		ResponseEntity<String> entry = null;
		log.info("BoardRestController: /update.....");
		try {
			boardDTO.setBno(bno);
			boardService.updateArticle(boardDTO);
			entry = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			log.info(entry);
		} catch(Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entry;
	}
	
	// 게시물 삭제
	@DeleteMapping("/articles/{bno}")
	public ResponseEntity<String> delete(@PathVariable("bno") int bno){
		ResponseEntity<String> entry = null;
		log.info("BoardRestController: /delete.....");
		try {
			boardService.deleteArticle(bno);
			entry = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			log.info(entry);
		} catch(Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entry;
	}
	
	// 게시물 다시 불러오기: 수정용
	@GetMapping("/details/{bno}")
	public ResponseEntity<BoardDTO> reload(@PathVariable("bno") int bno){
		ResponseEntity<BoardDTO> entry = null;
		log.info("BoardRestController: /reload.....");
		try {
			entry = new ResponseEntity<BoardDTO>(boardService.getDetail(bno), HttpStatus.OK);
			log.info(entry);
		} catch(Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<BoardDTO>(HttpStatus.BAD_REQUEST);
		}
		return entry;
	}
	
}