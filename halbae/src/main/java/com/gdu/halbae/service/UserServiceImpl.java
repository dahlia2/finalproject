package com.gdu.halbae.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gdu.halbae.domain.UserDTO;
import com.gdu.halbae.mapper.UserMapper;
import com.gdu.halbae.util.JavaMailUtil;
import com.gdu.halbae.util.ProfileUtil;
import com.gdu.halbae.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
	private final UserMapper userMapper;
	private final SecurityUtil securityUtil;
	private final JavaMailUtil javaMailUtil;
	private final ProfileUtil profileUtil;
	
	
	// 회원가입
	@Override
	public String checkUniqueId(UserDTO userDTO) {
		String msg = "";
		if(userMapper.checkUniqueId(userDTO.getUserId()) != null) {
			msg="이미 사용중인 이메일입니다.";
			return msg;
		}
		return msg;
	}
	@Override
	public String checkIdCountByTel(UserDTO userDTO) {
		String msg = "";
		if(userMapper.checkIdCountByTel(userDTO.getUserTel()) >= 3) {
			msg="입력하신 연락처로 더 이상 계정 생성이 불가능합니다. (최대 3개)";
			return msg;
		}
		return msg;
	}
	
	@Override
	public int insertUser(UserDTO userDTO) {
		String userName = userDTO.getUserName();
		String userTel = userDTO.getUserTel();
		String userId = userDTO.getUserId();
		String userPw = userDTO.getUserPw();
		
		// 스크립트 방지
		userName = securityUtil.preventXSS(userName);
		userTel = securityUtil.preventXSS(userTel);
		userId = securityUtil.preventXSS(userId);
		userPw = securityUtil.preventXSS(userPw);
		// 비번 암호화
		userPw = securityUtil.getSha256(userPw);
		// 보안 처리 완료한 정보로 바꾸기
		userDTO.setUserName(userName);
		userDTO.setUserTel(userTel);
		userDTO.setUserId(userId);
		userDTO.setUserPw(userPw);
		userDTO.setUserImgPath("/images/main/default_user.png");
		
		return userMapper.insertUser(userDTO);
	}
	
	// 로그인
	@Override
	public void login(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		userPw = securityUtil.getSha256(userPw);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("userId", userId);
		map.put("userPw", userPw);
		
		UserDTO userDTO = userMapper.selectLoginInfo(map);

		if(userDTO != null) {
			// 자동 로그인 처리하기
			autoLogin(request, response);
			HttpSession session = request.getSession();
			// 세션에 정보 저장
			session.setAttribute("loginId", userId);
			session.setAttribute("userNo", userDTO.getUserNo());
			session.setAttribute("userName", userDTO.getUserName());
			// 로그인 후 메인으로 이동
			try {
				response.sendRedirect("/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			response.setContentType("text/html; charset=UTF-8");
			try {
		    PrintWriter out = response.getWriter();
		    out.println("<script>");
		    out.println("alert('존재하지 않는 계정입니다.');");
		    out.println("location.href='" + request.getContextPath() + "/user/login.html'");
		    out.println("</script>");
		    out.flush();
		    out.close();
				} catch (IOException e) {
				    e.printStackTrace();
				}
		}
		
	}
	
	// 자동 로그인
	@Override
	public void autoLogin(HttpServletRequest request, HttpServletResponse response) {
		
		String userId = request.getParameter("userId");
		String chkAutoLogin = request.getParameter("chkAutoLogin");
		
		if(chkAutoLogin != null) {
			HttpSession session = request.getSession();
			String sessionId = session.getId();
			
			UserDTO userDTO = new UserDTO();
			userDTO.setUserId(userId);
			userDTO.setUserAutoLoginId(sessionId);
			userDTO.setUserAutoLoginExpired(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 15))); // 15일
			
			userMapper.insertAutoLogin(userDTO);
			
			Cookie cookie = new Cookie("autoLoginId", sessionId);
			cookie.setMaxAge(60 * 60 * 24 * 15); // 15일
			cookie.setPath("/");
			response.addCookie(cookie);
			
		}else {
			userMapper.deleteAutoLogin(userId);
			Cookie cookie = new Cookie("autoLoginId", "");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}
	
	// 로그아웃
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		String loginId = request.getParameter("loginId");
		userMapper.deleteAutoLogin(loginId);
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		Cookie cookie = new Cookie("autoLoginId", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 인증코드 보내기
	@Override
	public Map<String, Object> sendCode(String email) {
		String authCode = securityUtil.getRandomString(5, true, true);
		
		String content = "";
					content += "<h1>하루배움에서 인증코드를 발송했습니다</h1>";
					content += "<div>인증코드</div>";
					content += "<input readonly value='" + authCode + "'>";
		
		javaMailUtil.sendJavaMail(email, "[하루배움] 인증코드 발송해드립니다.", content);
		
		Map<String, Object> map = new HashMap<>();
		map.put("authCode", authCode);
		return map;
	}
	// 임시 비번 보내기
	@Override
	public Map<String, Object> sendTempPw(String email) {
		String tempPw = securityUtil.getRandomString(10, true, true);
		
		String content = "";
		content += "<h1>하루배움에서 임시 비밀번호를 발송했습니다</h1>";
		content += "<div>임시 비밀번호</div>";
		content += "<input readonly value='" + tempPw + "'>";

		javaMailUtil.sendJavaMail(email, "[하루배움] 임시 비밀번호 발송해드립니다.", content);
		
		System.out.println("비밀번호 전송 완료");
		
		Map<String, Object> map = new HashMap<>();
		map.put("tempPw", tempPw);
		return map;
	}
	// 임시 비번으로 비번 바꾸기
	@Override
	public void updateTempPw(UserDTO userDTO, HttpServletResponse response) {
		String tempPw = securityUtil.getSha256(userDTO.getUserPw());
		userDTO.setUserPw(tempPw);
		int updateResult = userMapper.updateTempPw(userDTO);
		System.out.println("비밀번호 변경 완료");
		response.setContentType("text/html; charset=UTF-8");
		
		if(updateResult == 1) {
			
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("if(confirm('로그인하러 가시겠습니까?')) {");
				out.println("location.href='/user/login.html';");
				out.println("} else {");
				out.println(" location.href='/';");
				out.println("}");
				out.println("</script>");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('임시 비밀번호 발급이 실패했습니다. 다시 시도해주세요');");
				out.println("</script>");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// 전화번호로 가입 아이디 조회
	@Override
		public List<UserDTO> selectUserIdByTel(String userTel) {
		return userMapper.selectUserByTel(userTel);
		}	
	// 유저 프로필 가져오기
	@Override
	public UserDTO selectUserProfile(String loginId) {
		return userMapper.selectUserProfile(loginId);
	}
	// 이름 변경하기
	@Override
	public void modifyName(UserDTO userDTO, HttpServletRequest request, HttpServletResponse response) {
		
		if(userMapper.updateUserName(userDTO) == 1) {
			userMapper.deleteAutoLogin(userDTO.getUserId());
			HttpSession session = request.getSession();
			
			session.invalidate();
			
			Cookie cookie = new Cookie("autoLoginId", "");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
			
			response.setContentType("text/html; charset=UTF-8");
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('이름(별명)이 변경되었습니다. 다시 로그인 해주세요.');");
				out.println("location.href='/'");
				out.println("</script>");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('이름(별명) 변경이 실패했습니다. 다시 시도해주세요.');");
				out.println("location.href='/'");
				out.println("</script>");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public boolean confirmPw(UserDTO userDTO) {
		String userId = userDTO.getUserId();
		String userPw = securityUtil.getSha256(userDTO.getUserPw());

		if(userPw.equals(userMapper.selectUserPwById(userId))) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void updateUserPwById(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String userPw = securityUtil.getSha256(request.getParameter("userPw"));
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(userId);
		userDTO.setUserPw(userPw);
		
		if(userMapper.updateUserPwById(userDTO) == 1) {
			
			userMapper.deleteAutoLogin(userDTO.getUserId());
			HttpSession session = request.getSession();
			
			session.invalidate();
			
			Cookie cookie = new Cookie("autoLoginId", "");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
			
			response.setContentType("text/html; charset=UTF-8");
			
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 변경되었습니다. 다시 로그인 해주세요.');");
				out.println("location.href='/'");
				out.println("</script>");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호 변경이 실패했습니다. 다시 시도해주세요.');");
				out.println("location.href='/'");
				out.println("</script>");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		
	@Override
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) {

		String userId = request.getParameter("userId");
		
		if(userMapper.deleteUser(userId) == 1) {
			userMapper.deleteAutoLogin(userId);
			HttpSession session = request.getSession();
			
			session.invalidate();
			
			Cookie cookie = new Cookie("autoLoginId", "");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
			
			response.setContentType("text/html; charset=UTF-8");
			
			try {
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('하루배움을 이용해주셔서 감사합니다.');");
				out.println("location.href='/';");
				out.println("</script>");
				out.flush();
				out.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String updateProfile(MultipartFile profile, HttpServletRequest request) {
		
		String userId = request.getParameter("userId");
		
		String sep = Matcher.quoteReplacement(File.separator);
		
		String userImgPath = "";
		
		try {
			if(profile != null && profile.isEmpty() == false) {
				
				String path = profileUtil.getPath();
				
				File dir = new File(path);
				if(dir.exists() == false) {
					dir.mkdirs();
				}
				String imgOriginName = profile.getOriginalFilename();
				imgOriginName = imgOriginName.substring(imgOriginName.lastIndexOf("\\") + 1);
				
				String userImgFileName = profileUtil.getFilesystemName(imgOriginName);
				
				File file = new File(dir, userImgFileName);
			
				profile.transferTo(file);
				
				String contentType = Files.probeContentType(file.toPath());
				
				boolean userHasImg = contentType != null && contentType.startsWith("image");

				userImgPath = path + sep + userImgFileName;
//				/Users/woomin/Documents/TeamPrj/finalproject/halbae/src/main/resources/static/images/user/profile/aaaa@aaaa.aaaa
				
				
				UserDTO userDTO = new UserDTO();
				userDTO.setUserId(userId);
				userDTO.setUserImgFileName(userImgFileName);
				userDTO.setUserHasImg(userHasImg ? 1 : 0);
				userDTO.setUserImgOriginName(imgOriginName);
				userDTO.setUserImgPath(userImgPath);
				
				userMapper.updateProfile(userDTO);
				
			}
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
			
		return userImgPath;
		
	}
	
	
}
	
	
	
	















