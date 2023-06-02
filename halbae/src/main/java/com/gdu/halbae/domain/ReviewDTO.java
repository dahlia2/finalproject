package com.gdu.halbae.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
	
	private int reviewNo;
	private UserDTO userNo;
	private int classNo;
	private String reviewContent;
	private int reviewGrade;

}