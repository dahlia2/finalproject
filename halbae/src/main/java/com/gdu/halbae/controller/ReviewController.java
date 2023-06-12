package com.gdu.halbae.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gdu.halbae.domain.ReviewAttachDTO;
import com.gdu.halbae.domain.ReviewDTO;
import com.gdu.halbae.domain.ReviewLikeDTO;
import com.gdu.halbae.service.ReviewService;
import com.gdu.halbae.util.PageUtil;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    private static final String UPLOAD_DIR = "C:\\upload\\"; // 파일 업로드 경로
    
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    
    // 리뷰 목록 페이지 요청
    @GetMapping("/review/list")
    public String getReviewList(PageUtil criteria, Model model) {
        // 총 리뷰 수 조회
        int totalReviewCount = reviewService.getTotalReviewCount();
        // 페이지에 해당하는 리뷰 목록 조회
        List<ReviewDTO> reviewList = reviewService.getReviewListWithPaging(criteria);
        // 모델에 데이터 추가
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("pageMaker", criteria);
        model.addAttribute("totalReviewCount", totalReviewCount); 
        return "review/reviewList"; // review/reviewList.html 페이지로 이동
    }

    // 리뷰 작성 폼 페이지 요청
    @GetMapping("/review/write")
    public String writeReviewForm() {
        return "review/reviewForm"; // review/reviewForm.html 페이지로 이동
    }
    
    // 리뷰 작성 처리
    @PostMapping("/review/write")
    public String writeReview(ReviewDTO reviewDTO, HttpServletRequest request) {
        reviewService.writeReview(reviewDTO); // 리뷰 저장
        // 리뷰 첨부 파일이 존재하는 경우 처리
        if (!ObjectUtils.isEmpty(reviewDTO.getReviewAttachList())) {
            for (ReviewAttachDTO attachDTO : reviewDTO.getReviewAttachList()) {
                attachDTO.setReviewNo(reviewDTO.getReviewNo());
                // 리뷰 첨부 파일 저장
                reviewService.saveReviewAttach(
                    reviewDTO.getReviewNo(),
                    getUploadPath(request), // 파일 업로드 경로 가져오기
                    attachDTO.getOriginName(),
                    attachDTO.getFileName()
                );
            }
        }
        return "redirect:/review/list"; // review/list로 리다이렉트
    }

    // 리뷰 첨부 파일 업로드 처리
    @PostMapping(value = "/review/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploadReviewAttach(@RequestParam("uploadFile") MultipartFile[] uploadFiles, HttpServletRequest request) {
        String uploadPath = getUploadPath(request); // 파일 업로드 경로 가져오기
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // 업로드 디렉토리가 없으면 생성
        }
        for (MultipartFile uploadFile : uploadFiles) {
            if (uploadFile.getSize() <= 0) {
                continue; // 파일 크기가 0 이하인 경우 무시
            }
            try {
                String originName = uploadFile.getOriginalFilename();
                String fileName = UUID.randomUUID().toString() + "_" + originName;
                Path savePath = Paths.get(uploadPath, fileName);
                uploadFile.transferTo(savePath.toFile()); // 파일 저장
                // 리뷰 첨부 파일 저장
                reviewService.saveReviewAttach(0, savePath.toString(), originName, fileName);
            } catch (IOException e) {
                e.printStackTrace(); // 예외 처리
            }
        }
        return "redirect:/review/list"; // review/list로 리다이렉트
    }

    // 리뷰 첨부 파일 목록 가져오기
    @GetMapping(value = "/review/getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReviewAttachDTO> getReviewAttachList(int reviewNo) {
        return reviewService.getReviewAttachList(reviewNo); // 리뷰 첨부 파일 목록 반환
    }
    
    // 리뷰 좋아요 수 증가 처리
    @PostMapping("/review/like")
    public String increaseLikeCount(ReviewLikeDTO reviewLikeDTO) {
        reviewService.increaseLikeCount(reviewLikeDTO.getReviewNo(), reviewLikeDTO.getUserNo()); // 좋아요 수 증가 처리
        reviewService.insertReviewLike(reviewLikeDTO); // 리뷰 좋아요 정보 저장
        return "redirect:/review/list"; // review/list로 리다이렉트
    }
    
    // 파일 업로드 경로 가져오기
    private String getUploadPath(HttpServletRequest request) {
        return UPLOAD_DIR + getFolder(request);
    }

    // 업로드 폴더 경로 가져오기
    private String getFolder(HttpServletRequest request) {
        String folderPath = request.getSession().getServletContext().getRealPath("/");
        folderPath = folderPath + "resources/upload/";
        return folderPath.replace("\\", "/");
    }
    
}