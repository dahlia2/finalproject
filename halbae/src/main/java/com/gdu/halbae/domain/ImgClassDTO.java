package com.gdu.halbae.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImgClassDTO {
	
	private int imgNo;
	private int classNo;
	private int userNo;
	private String imgName;
	private String imgPath;
	private int imgThumb;

}
