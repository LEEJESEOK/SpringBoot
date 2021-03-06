package com.hyundai.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 게시판 관련 데이터
 * @author 이지은
 *
 */
@Getter
@Setter
@ToString
public class BoardDTO {
	private long bno;  					// 글 번호
	private String bname;  				// 글 작성자
	private String uemail;				// 작성자 이메일(id)
	private String btitle;  			// 글 제목
	private String bcontent;			// 글 내용
	private LocalDateTime bregdate;  	// 글 작성일
	private int breadcount;  			// 글 조회수
	
}
