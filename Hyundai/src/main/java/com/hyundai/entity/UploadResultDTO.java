package com.hyundai.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 파일 업로드를 위한 데이터
 * @author 이지은
 *
 */
@Getter
@Setter
@ToString
public class UploadResultDTO {
	private long fno;  		// 파일 번호
	private long bno;  		// 글 번호
	private String fname;  	// 파일 이름
	private String uuid;  	// UUID
	private String ffolder;	// 파일이 저장될 폴더
}
