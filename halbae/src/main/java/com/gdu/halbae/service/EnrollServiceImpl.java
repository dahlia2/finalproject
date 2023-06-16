package com.gdu.halbae.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.halbae.domain.ClassListDTO;
import com.gdu.halbae.domain.CouponDTO;
import com.gdu.halbae.domain.EnrollDTO;
import com.gdu.halbae.domain.ScheduleDTO;
import com.gdu.halbae.domain.UserDTO;
import com.gdu.halbae.mapper.EnrollMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollServiceImpl implements EnrollService {

	public final EnrollMapper enrollMapper;

	/* 단순 강의 조회 */
	@Override
	public ClassListDTO classListByNo(HttpServletRequest request) {
		String strclassNo = request.getParameter("classNo");
		int classNo = 0;

		if (strclassNo != null && strclassNo.isEmpty() == false) {
			classNo = Integer.parseInt(strclassNo);
		}
		return enrollMapper.selectClassByNo(classNo);
	}

	/* 스케줄 조회 */
	@Override
	public List<ScheduleDTO> scheduleByClassNo(HttpServletRequest request) {
		String strclassNo = request.getParameter("classNo");
		int classNo = 0;

		if (strclassNo != null && strclassNo.isEmpty() == false) {
			classNo = Integer.parseInt(strclassNo);
		}
		
		return enrollMapper.scheduleByClassNo(classNo);
		
	}
	
	/* 사용자 조회 */
	@Override
	public UserDTO selectUserByNo(HttpServletRequest request) {
		int userNo =  Integer.parseInt(request.getParameter("userNo"));
		
		return enrollMapper.selectUserByNo(userNo);
	}

	/* 수강 신청 */
	@Transactional
	@Override
	public int applyClass(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		// 파라미터1: userID 통해서 UserDTO 가져오기
		int userNo =  Integer.parseInt(request.getParameter("userNo"));
		UserDTO userDTO = enrollMapper.selectUserByNo(userNo);
		
		// 파라미터3: schNo 통해서 ScheduleDTO 가져오기
		int schNo = Integer.parseInt(request.getParameter("schNo"));
		ScheduleDTO schduleDTO = enrollMapper.selectScheduleByNo(schNo);
		
		// 파라미터4: 신청인원 enrollPerson
		int enrollPerson = Integer.parseInt(request.getParameter("enrollPerson"));
		
		// 파라미터5: 요청사항 enrollRequest
		String enrollRequest = request.getParameter("enrollRequest");
		
		// EnrollDTO에 담기
		EnrollDTO enrollDTO = new EnrollDTO();
		enrollDTO.setUserDTO(userDTO);
		enrollDTO.setScheduleDTO(schduleDTO);
		enrollDTO.setEnrollPerson(enrollPerson);
		enrollDTO.setEnrollRequest(enrollRequest);
		 
		// 스케줄 인원 plus
		int updateResult = enrollMapper.updateSchNowNum(enrollDTO);
		// 스케줄 상태 UPDATE
		updateResult += enrollMapper.updateSchState(enrollDTO);
		System.out.println("결과 : " + updateResult);
		if(updateResult == 2) {
			// 수강신청 INSERT
			return enrollMapper.applyClass(enrollDTO);
		} else {
			return 0;
			
		}
	}
	
	/* 사용자 쿠폰 조회 */
	@Override
	public List<CouponDTO> couponListByNo(HttpServletRequest request) {
		
		// userID 통해서 UserNo 가져오기
		int userNo =  Integer.parseInt(request.getParameter("userNo"));
		
		return enrollMapper.couponListByNo(userNo);
		
	}

}
