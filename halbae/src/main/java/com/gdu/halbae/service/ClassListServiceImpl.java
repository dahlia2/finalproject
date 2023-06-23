package com.gdu.halbae.service;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.halbae.domain.ClassListDTO;
import com.gdu.halbae.domain.ImgClassDTO;
import com.gdu.halbae.domain.UserDTO;
import com.gdu.halbae.mapper.ClassListMapper;
import com.gdu.halbae.mapper.WishMapper;
import com.gdu.halbae.util.MyFileUtil;
import com.gdu.halbae.util.PageUtil;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

@RequiredArgsConstructor
@Service
public class ClassListServiceImpl implements ClassListService {
	
	// field
	private final ClassListMapper classListMapper;
	private final WishMapper wishMapper;
	private final PageUtil pageUtil;
	private final MyFileUtil myFileUtil;
	
	// 전체,카테고리 클래스
	@Override
	public void getClassList(HttpServletRequest request, Model model) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		String classCategory = request.getParameter("classCategory");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("classCategory", classCategory);
		
		int totalRecord = classListMapper.getClassListCount(classCategory);
		
		int recordPerPage = 12;
		
		pageUtil.setPageUtil(page, totalRecord, recordPerPage);
		
		map.put("begin", pageUtil.getBegin());
		map.put("recordPerPage", recordPerPage);
		
		List<ClassListDTO> classList = classListMapper.selectClassList(map);
		
		
		HttpSession session = request.getSession();
		Object userNoObj = session.getAttribute("userNo");
		int userNo = 0; // 기본값 설정
		if (userNoObj != null) {
		    userNo = Integer.parseInt(userNoObj.toString());
		}
		
		
		List<Integer> wishList = wishMapper.selectClassNoInWish(userNo);
		
		
		// select -> option 태그에 카테고리 별 개수 구하는 코드
		model.addAttribute("classCountAll", classListMapper.getClassListCountAll());
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("classList", classList);
		model.addAttribute("beginNo", totalRecord - (page - 1) * recordPerPage);
		model.addAttribute("pagination", pageUtil.getPagination(request.getContextPath() + "/class/classList.do?classCategory=" + classCategory));
		model.addAttribute("categoryName", classCategory);
		model.addAttribute("wishList", wishList);
		
	}
	
	
	
	// 최신 클래스
	@Override
	public void getClassListNew(HttpServletRequest request, Model model) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		int totalRecord = classListMapper.getClassListCountNew();
		
		int recordPerPage = 12;
		
		pageUtil.setPageUtil(page, totalRecord, recordPerPage);	// begin이랑 end값을 구해서 mapper에 전달하기위한 코드 필수!
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("recordPerPage", recordPerPage);
		
		List<ClassListDTO> classList = classListMapper.selectClassListNew(map);
		
		HttpSession session = request.getSession();
		Object userNoObj = session.getAttribute("userNo");
		int userNo = 0; // 기본값 설정
		if (userNoObj != null) {
		    userNo = Integer.parseInt(userNoObj.toString());
		}
		
		
		List<Integer> wishList = wishMapper.selectClassNoInWish(userNo);
		
		
		model.addAttribute("newClass", "최신");
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("classList", classList);
		model.addAttribute("beginNo", totalRecord - (page - 1) * recordPerPage);
		model.addAttribute("pagination", pageUtil.getPagination(request.getContextPath() + "/class/classListNew.do"));
		model.addAttribute("wishList", wishList);
		
	}
	
	// 디테일 클래스
	@Override
	public ImgClassDTO getClassByNo(HttpServletRequest request) {
		
		String strClassNo = request.getParameter("classNo");
		int classNo = 0;	// null, 빈문자열이 올때 0값을 사용하기위한 선언이다!
		if(strClassNo != null && strClassNo.isEmpty() == false) { // 자바는 빈문자열 처리시 .isEmpty()를 사용한다. (기본은 비어있다라는 말!)
			classNo = Integer.parseInt(strClassNo);
		}
		return classListMapper.selectClassByNo(classNo);
	}
	
	// 클래스 등록
	@Transactional
	@Override
	public int addClass(MultipartHttpServletRequest multipartHttpServletRequest) {
		
		String classTitle = multipartHttpServletRequest.getParameter("classTitle");
		String classCategory = multipartHttpServletRequest.getParameter("classCategory");
		String classArea = multipartHttpServletRequest.getParameter("classArea");
		String classTime = multipartHttpServletRequest.getParameter("classTime");
		String classMoney = multipartHttpServletRequest.getParameter("classMoney");
		String classGradeStr = multipartHttpServletRequest.getParameter("classGrade");
		double classGrade = Double.parseDouble(classGradeStr);
		
		MultipartFile classMainPath = multipartHttpServletRequest.getFile("classMainPath");
		int uploadResult = 0;
		if(classMainPath != null && classMainPath.isEmpty() == false) {
			
			// 예외 처리
			try {
				
				// 첨부 파일의 저장 경로
				String path = myFileUtil.getPath();
				
				// 첨부 파일의 저장 경로가 없으면 만들기
				File dir = new File(path);
				if(dir.exists() == false) {	// 저장 경로에 파일이 존재하지 않다면 
					dir.mkdirs();
				}
				
				// 첨부 파일의 원래 이름
				String originName = classMainPath.getOriginalFilename();
				System.out.println("첨부 파일의 원래 이름 " + originName);
				originName = originName.substring(originName.lastIndexOf("\\") + 1);
				
				// 첨부 파일의 저장 이름
				String filesystemName = myFileUtil.getFilesystemName(originName);
				System.out.println("첨부 파일의 저장 이름 " + filesystemName);
				
				// 첨부 파일의 File 객체 (HDD에 저장할 첨부 파일)
				File file = new File(dir, filesystemName);
				
				String imgPath = dir + "\\" + filesystemName;
				
				// 첨부 파일을 HDD에 저장
				classMainPath.transferTo(file);  // 실제로 서버에 저장된다.
				
				HttpSession session = multipartHttpServletRequest.getSession();
				int userNo = (int) session.getAttribute("userNo");
				
				UserDTO userDTO = new UserDTO();
				userDTO.setUserNo(userNo);
				
				ClassListDTO classListDTO = new ClassListDTO();
				classListDTO.setClassMainPath(imgPath);
				classListDTO.setClassTitle(classTitle);
				classListDTO.setClassCategory(classCategory);
				classListDTO.setClassArea(classArea);
				classListDTO.setClassTime(classTime);
				classListDTO.setClassMoney(classMoney);
				classListDTO.setClassGrade(classGrade);
				classListDTO.setUserDTO(userDTO);
				
				
				
				System.out.println("클래스 DTO에 담긴 값 : " + classListDTO);
				
				// DB로 보내기
				uploadResult = classListMapper.insertClass(classListDTO);
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return uploadResult;
		
	}
	
	// 클래스 등록하고 HDD에 저장되어 있는 이미지 뽑는 코드
	@Override
	public ResponseEntity<byte[]> display(int classNo) {
		
		ClassListDTO classListDTO = classListMapper.getClassByNo(classNo);
		
		ResponseEntity<byte[]> image = null;
		
		try {
			File imgPath = new File(classListDTO.getClassMainPath());
			image = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(imgPath), HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	// 클래스 등록 목록
	@Override
	public void getClassUploadList(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		int userNo = Integer.parseInt(session.getAttribute("userNo").toString());
		
		// userNo를 mapper에 주고 classNo를 전부 뽑아오는 코드
		List<Integer> uploadList = classListMapper.selectClassUploadList(userNo);
		List<ClassListDTO> classUploadList = null;
		
		if(uploadList.size() == 0) {
			model.addAttribute("classUploadList", classUploadList);			
		}else {
			classUploadList = classListMapper.selectUploadList(uploadList);
			model.addAttribute("classUploadList", classUploadList);
			System.out.println(classUploadList);
		}
		
	}
	
	
	
	
	
	
	
	
	

}
