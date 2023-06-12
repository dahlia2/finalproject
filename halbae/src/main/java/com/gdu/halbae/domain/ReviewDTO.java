package com.gdu.halbae.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private int reviewNo;
    private int userNo;
    private int classNo;
    private String reviewContent;
    private double reviewGrade;
    private Date writeDate;
    private List<ReviewAttachDTO> reviewAttachList;
    private UserDTO userDTO;
}