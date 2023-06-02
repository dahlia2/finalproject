package com.gdu.halbae.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassListDTO {
	
	private int classNo;
	private int userNo;
	private String classTitle;
	private String classCategory;
	private String classArea;
	private String classTime;
	private String classMoney;
	private double classGrade;
	

}