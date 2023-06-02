package com.gdu.halbae.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
	
	private int schNo;              // 일정식별자
	private ClassListDTO classNo;   // 클래스식별자
	private Date schStart;          // 시작시간
	private Date schEnd;            // 종료시간
	private int enrollPerson;       // 정원
	private String schCity;  		// 지역명
	private int schState;           // 수강가능유무 (불가능 0, 가능 1)

}
