-- 스키마
USE gdj61;

-- 테이블 삭제
DROP TABLE IF EXISTS MESSAGE;
DROP TABLE IF EXISTS CONVERSATION;
DROP TABLE IF EXISTS COUPON_USER;
DROP TABLE IF EXISTS COUPON;
DROP TABLE IF EXISTS REVIEW_ATTACH;
DROP TABLE IF EXISTS REVIEW_LIKE;
DROP TABLE IF EXISTS REVIEW;
DROP TABLE IF EXISTS ENROLL;
DROP TABLE IF EXISTS SCHEDULE;
DROP TABLE IF EXISTS WISH;
DROP TABLE IF EXISTS IMG_CLASS;
DROP TABLE IF EXISTS CLASS_LIST;
DROP TABLE IF EXISTS LEAVE_USER;
DROP TABLE IF EXISTS USER;

-- ****** 테이블 생성 ******
-- 회원 USER
CREATE TABLE USER (
	USER_NO					INT				NOT NULL	AUTO_INCREMENT PRIMARY KEY,
   	USER_ID					VARCHAR(40)     NOT NULL	UNIQUE,
    USER_PW					VARCHAR(64)     NOT NULL,
	USER_NAME				VARCHAR(40),
    USER_TEL				VARCHAR(11)	,
    USER_JOIN_DATE			DATETIME        NOT NULL,
	USER_AUTO_LOGIN_ID		VARCHAR(32),
    USER_AUTO_LOGIN_EXPIRED	DATETIME,
	USER_POINT				INT,
	USER_IMG_PATH			VARCHAR(3000),
	USER_IMG_ORIGIN_NAME	VARCHAR(3000),
	USER_IMG_FILE_NAME      VARCHAR(3000),
	USER_HAS_IMG			INT
);

-- 탈퇴회원 LEAVE_USER
CREATE TABLE LEAVE_USER(
	LEAVE_NO		INT				NOT NULL	AUTO_INCREMENT PRIMARY KEY,
	LEAVE_USER_ID	VARCHAR(40)		NOT NULL
);

-- 전체강의 CLASS_LIST
CREATE TABLE CLASS_LIST (
	CLASS_NO		INT			NOT NULL	  PRIMARY KEY,
    CLASS_TITLE		VARCHAR(200),	-- 제목
    CLASS_CATEGORY	VARCHAR(30),	-- 카테고리
    CLASS_AREA		VARCHAR(30),	-- 지역
    CLASS_TIME		VARCHAR(30),	-- 소요시간
    CLASS_MONEY		VARCHAR(30),	-- 총금액
    CLASS_GRADE		DECIMAL(2,1),	-- 평점
    CLASS_MAIN_PATH VARCHAR(1000),	-- 메인 이미지
    USER_NO			INT			NOT NULL,
    FOREIGN KEY(USER_NO)	REFERENCES USER(USER_NO)	ON DELETE CASCADE
);

-- 강의이미지 IMG_CLASS
CREATE TABLE IMG_CLASS (
	IMG_NO			INT				NOT NULL	 AUTO_INCREMENT  PRIMARY KEY,
    IMG_NAME		VARCHAR(20),
    IMG_MAIN_PATH	VARCHAR(1000)	NOT NULL,
    IMG_DETAIL_PATH	VARCHAR(1000)	NOT NULL,
    CLASS_NO		INT				NOT NULL,
    FOREIGN KEY(CLASS_NO)	REFERENCES CLASS_LIST(CLASS_NO)	ON DELETE CASCADE
);

-- 찜목록 WISH
CREATE TABLE WISH (
	CLASS_NO	INT		NOT NULL,
	USER_NO		INT		NOT NULL,
    FOREIGN KEY(CLASS_NO)	REFERENCES CLASS_LIST(CLASS_NO)	ON DELETE CASCADE,
    FOREIGN KEY(USER_NO)	REFERENCES USER(USER_NO)		ON DELETE CASCADE
);

-- 강의일정 SCHEDULE
CREATE TABLE SCHEDULE (
	SCH_NO			INT				NOT NULL	AUTO_INCREMENT PRIMARY KEY,
    CLASS_NO		INT				NOT NULL,
    SCH_START		DATETIME		NOT NULL,
    SCH_NOW_NUM		INT				NOT NULL,
    SCH_MAX_NUM		INT				NOT NULL,
    SCH_STATE		INT NOT NULL,
    FOREIGN KEY(CLASS_NO)	REFERENCES CLASS_LIST(CLASS_NO)	ON DELETE CASCADE
);

-- 수강신청 ENROLL
CREATE TABLE ENROLL (
    ENROLL_NO        INT             NOT NULL   AUTO_INCREMENT PRIMARY KEY,
    USER_NO          INT             NOT NULL,
    SCH_NO           INT             NOT NULL, 
    ENROLL_DATE      DATETIME        NOT NULL,
    ENROLL_PERSON    INT             NOT NULL,
    ENROLL_REQUEST   VARCHAR(1000),
	FOREIGN KEY(USER_NO)	REFERENCES USER(USER_NO)	ON DELETE CASCADE,
	FOREIGN KEY(SCH_NO)		REFERENCES SCHEDULE(SCH_NO)	ON DELETE CASCADE
);

-- 리뷰 REVIEW
CREATE TABLE REVIEW (
	REVIEW_NO	    INT  		 NOT NULL   AUTO_INCREMENT PRIMARY KEY,
    USER_NO			INT  		 NOT NULL,
    CLASS_NO	    INT  		 NOT NULL,
	REVIEW_CONTENT  LONGTEXT	 NOT NULL,
    REVIEW_GRADE    DECIMAL(2,1) NOT NULL,
    WRITE_DATE      DATETIME     NOT NULL,
LIKES           INT         DEFAULT 0,
    FOREIGN KEY (USER_NO)  REFERENCES USER(USER_NO)			ON DELETE CASCADE,
    FOREIGN KEY (CLASS_NO) REFERENCES CLASS_LIST(CLASS_NO)	ON DELETE CASCADE
);

-- 리뷰 좋아요 REVIEW_LIKE
CREATE TABLE REVIEW_LIKE (
	REVIEW_NO	INT  NOT NULL,
    USER_NO		INT  NOT NULL,
    FOREIGN KEY(REVIEW_NO) REFERENCES REVIEW(REVIEW_NO) ON DELETE CASCADE,
    FOREIGN KEY(USER_NO)   REFERENCES USER(USER_NO) ON DELETE CASCADE
);

-- 리뷰 첨부파일 REVIEW_ATTACH
CREATE TABLE REVIEW_ATTACH (
	REA_NO		 INT	          NOT NULL    AUTO_INCREMENT  PRIMARY KEY,
    REVIEW_NO	 INT              NOT NULL,
    PATH		 LONGTEXT  		  NOT NULL,
    ORIGIN_NAME  LONGTEXT         NOT NULL,
    FILE_NAME	 LONGTEXT	      NOT NULL,
    DOWNLOAD	 INT,
    THUMBNAIL	 INT,
    FOREIGN KEY (REVIEW_NO) REFERENCES REVIEW(REVIEW_NO)
);

-- 전체쿠폰함 COUPON
CREATE TABLE COUPON (
	COUPON_NO			INT 		  NOT NULL  AUTO_INCREMENT PRIMARY KEY,
    COUPON_NAME 		VARCHAR(20)   NOT NULL,
    COUPON_DISCOUNT		INT			  NOT NULL,
    COUPON_START_DATE	DATETIME 	  NOT NULL,
    COUPON_END_DATE		DATETIME 	  NOT NULL
);

-- 회원쿠폰함 COUPON_USER
CREATE TABLE COUPON_USER (
    USER_NO         INT   NOT NULL,
    COUPON_NO       INT   NOT NULL,
    COUPON_STATUS   INT   NOT NULL,
    FOREIGN KEY (USER_NO) REFERENCES USER(USER_NO) ON DELETE CASCADE,
    FOREIGN KEY (COUPON_NO) REFERENCES COUPON(COUPON_NO) ON DELETE CASCADE,
    PRIMARY KEY (USER_NO, COUPON_NO)
);

-- 문의채팅방 CONVERSATION
CREATE TABLE CONVERSATION (
    CON_ID      INT        NOT NULL   AUTO_INCREMENT PRIMARY KEY,
    CLASS_NO    INT        NOT NULL,
    USER_NO     INT    	   NOT NULL,
    CON_CREATE  DATETIME   NOT NULL,
    CON_STATE   INT        NOT NULL,
    FOREIGN KEY(CLASS_NO) REFERENCES CLASS_LIST(CLASS_NO),
    FOREIGN KEY(USER_NO)  REFERENCES USER(USER_NO)
);

-- 문의채팅내용 MESSAGE
CREATE TABLE MESSAGE (
    MSG_ID     INT     	        NOT NULL   AUTO_INCREMENT PRIMARY KEY,
	CON_ID     INT      	    NOT NULL,
    USER_NO    INT    		    NOT NULL,
    MESSAGE    LONGTEXT         NOT NULL,
    SEND_TIME  DATETIME 	    NOT NULL,
    FOREIGN KEY(CON_ID)  REFERENCES CONVERSATION(CON_ID),
    FOREIGN KEY(USER_NO) REFERENCES USER(USER_NO)
);

