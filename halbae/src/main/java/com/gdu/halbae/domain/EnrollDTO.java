package com.gdu.halbae.domain;

import java.util.Date;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollDTO {
	
	private int enrollNo;          // 수강신청식별번호
	private UserDTO userNo;       // 회원식별자
	private ScheduleDTO schNo;      // 일정식별변호
	private Date enrollDate;       // 수강신청일
	private int enrollPerson;      // 신청인원
	private String enrollRequest;  // 요청사항
	private int enrollAmount;      // 결제금액
	private int enrollSale;		   // 할인금액
	private int enrollPay;		   // 결제방식  (카드 0, 무통장 1)

}
