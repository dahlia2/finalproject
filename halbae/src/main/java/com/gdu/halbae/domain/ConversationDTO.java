package com.gdu.halbae.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationDTO {

	private int conId;
	private ClassListDTO classListDTO;
	private int userNO;
	private Date conCreate;
	private int conState;
	
}