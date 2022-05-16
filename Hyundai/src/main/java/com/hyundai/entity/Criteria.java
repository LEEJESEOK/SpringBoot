package com.hyundai.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 페이징 처리를 위한 데이터
 * @author 이지은
 *
 */
@Getter
@Setter
@ToString
public class Criteria {

	private int pageNum;	// 페이지 번호
	private int amount;		// 한 페이지에 보여줄 글 개수
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public Criteria() {
		this(1, 5);
	}
}
