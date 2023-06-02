package com.gdu.halbae.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
	
	private int msgId;
	private int conId;
	private int userNo;
	private String message;
	private Date sendTime;

}