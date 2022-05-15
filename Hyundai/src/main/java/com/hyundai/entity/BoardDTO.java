package com.hyundai.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
	private long bno;
	private String bname;
	private String btitle;
	private String bcontent;
	private Date bregdate;
	private int breadcount;
}
