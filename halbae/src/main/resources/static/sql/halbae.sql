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
INSERT INTO USER(USER_ID, USER_PW, USER_JOIN_DATE) VALUES('admin', 'admin@halbae.com', now());
INSERT INTO USER(USER_ID, USER_PW, USER_JOIN_DATE) VALUES('jjch', '1111', '2023-06-06');
COMMIT;

-- 탈퇴회원 LEAVE_USER
CREATE TABLE LEAVE_USER(
	LEAVE_NO		INT			NOT NULL	AUTO_INCREMENT PRIMARY KEY,
	LEAVE_USER_ID	VARCHAR(40)		NOT NULL
);

-- 전체강의 CLASS_LIST
CREATE TABLE CLASS_LIST (
	CLASS_NO			INT			NOT NULL   AUTO_INCREMENT  PRIMARY KEY,
	CLASS_TITLE			VARCHAR(200), 			-- 제목
	CLASS_CATEGORY		VARCHAR(30),  			-- 카테고리
	CLASS_AREA			VARCHAR(30),  			-- 지역
    CLASS_TIME			VARCHAR(30),  			-- 소요시간
    CLASS_MONEY			VARCHAR(30),			-- 총금액
    CLASS_GRADE			DECIMAL(2,1), 			-- 평점
    CLASS_MAIN_PATH		VARCHAR(1000), 			-- 메인 이미지
    USER_NO				INT			NOT NULL,	-- 회원번호
    FOREIGN KEY(USER_NO) REFERENCES USER(USER_NO) ON DELETE CASCADE
);
-- 1 ~ 10 요리/베이킹
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('꾸덕,쫀득 마카롱 원데이클래스-뚝섬역,어썸블리스', '요리/베이킹', '성수', '2시간', '60,000원', 4.5, '/images/classList/cooking/bakery1.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('모두 반하고간 휘낭시에 맛집 쫀득꾸덕 덕후라면 일루와', '요리/베이킹', '부천', '2시간', '60,000원', 4.4, '/images/classList/cooking/bakery2.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('허밍 버드 케이크 만들기', '요리/베이킹', '잠실', '2시간', '50,000원', 4.3, '/images/classList/cooking/bakery3.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('안먹어본사람은 있어도 한번먹은사람은 없다는 마약르뱅쿠키', '요리/베이킹', '부천', '2시간', '60,000원', 4.4, '/images/classList/cooking/bakery4.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('장미? 국화? 이런거말고 진짜 잘쓰는 꽃 파이핑 기술, 앙금 플라워 케이크', '요리/베이킹', '은평', '3시간', '126,000원', 4.2, '/images/classList/cooking/bakery5.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('겉바속촉의 대표 까눌레 원데이 클래스', '요리/베이킹', '수유', '1시간', '60,000원', 4.1, '/images/classList/cooking/bakery6.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('캐릭터 김밥아트 2급', '요리/베이킹', '의정부', '8시간', '360,000원', 4.3, '/images/classList/cooking/bakery7.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('[원데이/케잌만들기] 고급진 케잌 만들기 도전!!', '요리/베이킹', '잠실', '4시간', '180,000원', 4.2, '/images/classList/cooking/bakery8.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('앙금플라워 장미 카네이션 앙금쿠키 만들기', '요리/베이킹', '일산', '3시간', '75,000원', 4.4, '/images/classList/cooking/bakery9.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('겉바속촉 3종 결스콘 만들기', '요리/베이킹', '잠실', '2시간', '70,000원', 4.1, '/images/classList/cooking/bakery10.png', 1);
-- 11 ~ 20 댄스/스포츠
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('21년 최고매출, 22년 최다판매 친절한 쁨선생 케이팝', '댄스/스포츠', '사당/서울대입구', '2시간', '25,900원', 4.4, '/images/classList/dance/dance1.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('세상에 몸치는 없다-아이엠,키치,퀸카,스파이시등', '댄스/스포츠', '강남/신촌홍대', '3시간', '38,400원', 4.2, '/images/classList/dance/dance2.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('_아이브 여자아이돌 에스파 무조건 최애 안무 마스터_', '댄스/스포츠', '신촌홍대', '2시간', '26,800원', 4.5, '/images/classList/dance/dance3.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('사당영상맛집 K-POP 영상남기자 언포기븐,손오공,퀸카', '댄스/스포츠', '사당', '2시간', '32,500원', 4.3, '/images/classList/dance/dance4.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('대관료포함,강남원데이댄스 르세라핌 아이브 아이들 에스파', '댄스/스포츠', '강남', '2시간', '35,000원', 4.2, '/images/classList/dance/dance5.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('디테일 가득 댄스 손오공 이프푸언포기븐 아이엠', '댄스/스포츠', '건대/잠실/강남', '3시간', '39,000원', 4.0, '/images/classList/dance/dance6.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('-건대- 원데이 댄스 배우기 춤 맛집', '댄스/스포츠', '건대', '2시간', '33,000원', 4.3, '/images/classList/dance/dance7.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('-피땀눈물,Lemonade- 보이그룹 댄스 레전드', '댄스/스포츠', '신림', '2시간', '27,500원', 4.5, '/images/classList/dance/dance8.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('원데이,개인,그룹댄스 ++ 신나게 춤출준비 되셨나요', '댄스/스포츠', '사당/신림', '2시간', '33,000원', 4.4, '/images/classList/dance/dance9.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('성신여대,상왕십리 소수로 진행하는 프라이빗 댄스 클래스', '댄스/스포츠', '한양대/수유/혜화', '2시간', '33,000원', 4.2, '/images/classList/dance/dance10.png', 1);
-- 21 ~ 30 디자인
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('아임웹 맞춤 코칭 효율 200프로 보장,불만족시 환불', '디자인', '신촌', '2시간', '72,000원', 4.1, '/images/classList/design/design1.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('퀄리티 10배 높아지는 전문가의 포토샵보정, 리터칭', '디자인', '상봉', '3시간', '195,000원', 4.3, '/images/classList/design/design2.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('디자이너처럼 제대로 배우는, 일러스트+포토샵', '디자인', '신촌/홍대', '18시간', '324,000원', 4.2, '/images/classList/design/design3.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('실무자에게 배우는 기초탄탄 스케치업+엔스케이프', '디자인', '신림', '3시간', '120,000원', 4.4, '/images/classList/design/design4.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('한달 완성 UXUI 취업 이직 스터디!! UX프로토 타이핑 툴 실무 역량 키우기!!', '디자인', '잠실', '10시간', '400,000원', 4.5, '/images/classList/design/design5.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('한번 배우면 기본은 하는 꿀팁 라이노, 키샷, 포토샵', '디자인', '신촌홍대/강남', '12시간', '312,000원', 4.2, '/images/classList/design/design6.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('패션 포트폴리오, 패션포트폴리오 합격노하우', '디자인', '신촌홍대', '1시간', '99,000원', 4.1, '/images/classList/design/design7.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('포토샵과 피그마 비전공자를 위한 디자인 입문', '디자인', '분당', '3시간', '147,000원', 4.4, '/images/classList/design/design8.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('4주 완성 디자인 기초반 모집, 포토샵+일러스트레이터', '디자인', '신촌홍대', '12시간', '252,000원', 4.3, '/images/classList/design/design9.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('온+오프라인,영상제공,포토샵+일러스트 완벽 알짜 수업', '디자인', '신촌홍대/강남', '8시간', '149,600원', 4.2, '/images/classList/design/design10.png', 1);
-- 31 ~ 40 피트니스/스포츠
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('롱보드 소수인원 입문강습', '피트니스/스포츠', '강남', '1시간', '50,000원', 4.0, '/images/classList/fitness/fitness1.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('+레슨+ 나만 알고 싶은 실전농구기술들을 알려드립니다.', '피트니스/스포츠', '김포/일산/마포', '12시간', '300,000원', 4.2, '/images/classList/fitness/fitness2.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('한강 윙포일, 챔피언에게 배우자', '피트니스/스포츠', '건대', '3시간', '150,000원', 4.1, '/images/classList/fitness/fitness3.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('쉽게 배우는 롱보드 댄싱 함께해요', '피트니스/스포츠', '잠실', '1시간', '40,000원', 4.1, '/images/classList/fitness/fitness4.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('서울대입구 폴댄스 재밌게 배워보자', '피트니스/스포츠', '신림/서울대입구', '1시간', '10,000원', 4.2, '/images/classList/fitness/fitness5.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('프리다이빙을 하루만에 배워보자! (feat.수중사진) #프리다이빙체험 #AIDA 소속', '피트니스/스포츠', '올림픽공원', '3시간', '66,000원', 4.5, '/images/classList/fitness/fitness6.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('유연성 덤블링 체험반(원데이 클래스)', '피트니스/스포츠', '사당', '1시간', '38,000원', 4.3, '/images/classList/fitness/fitness7.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('[파쿠르]원하는 곳 어디든 내 맘대로! 파쿠르 배우기', '피트니스/스포츠', '부산동래', '20시간', '300,000원', 4.4, '/images/classList/fitness/fitness8.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('활을 잡았으면 두부라도 맞혀야지. 개인,그룹 다 가능', '피트니스/스포츠', '하남', '2시간', '30,000원', 4.1, '/images/classList/fitness/fitness9.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('유소년 및 성인 아이스하키 원데이 클래스.', '피트니스/스포츠', '일산', '1시간', '20,000원', 4.3, '/images/classList/fitness/fitness10.png', 1);
-- 41 ~ 50 개발/공부
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('iOS 개발자 취업 강의-급여 1100만원', '개발/공부', '잠실', '20시간', '960,000원', 4.3, '/images/classList/study/study1.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('워드프레스 6시간 속성 마스터+컨설팅-원데이클래스', '개발/공부', '종로', '6시간', '240,000원', 4.1, '/images/classList/study/study2.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('Python 6월 시작 모집중 - 입문 + 온라인', '개발/공부', '강남/건대', '16시간', '400,000원', 4.4, '/images/classList/study/study3.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('온오프라인, 플러터 Flutter + NodeJS', '개발/공부', '신림/강남', '2시간', '100,000원', 4.2, '/images/classList/study/study4.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('진짜에게 배워봐. 안드로이드, ios 어플리케이션 개발', '개발/공부', '잠실', '8시간', '264,000원', 4.5, '/images/classList/study/study5.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('HTML 퍼블리싱 1대1 맞춤수업 디자이너, 취업준비', '개발/공부', '은평', '12시간', '600,000원', 4.0, '/images/classList/study/study6.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('1대1 맞춤 퍼블리싱 실무 활용 노하우', '개발/공부', '일산', '2시간', '72,000원', 4.2, '/images/classList/study/study7.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('딥러닝을 이용한 자연어처리', '개발/공부', '강남/신촌홍대', '8시간', '320,000원', 4.4, '/images/classList/study/study8.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('AWS 입문부터 고급까지~ (개발과운영 DevOps)', '개발/공부', '강남', '9시간', '450,000원', 4.1, '/images/classList/study/study9.png', 1);
INSERT INTO CLASS_LIST(CLASS_TITLE, CLASS_CATEGORY, CLASS_AREA, CLASS_TIME, CLASS_MONEY, CLASS_GRADE, CLASS_MAIN_PATH, USER_NO)
VALUES('60시간 [Kotlin으로 배우는] 안드로이드 서비스개발', '개발/공부', '건대', '60시간', '1,800,000원', 4.5, '/images/classList/study/study10.png', 1);
COMMIT;

-- 강의이미지 IMG_CLASS
CREATE TABLE IMG_CLASS (
	IMG_NO				INT				NOT NULL    AUTO_INCREMENT  PRIMARY KEY,
	IMG_NAME			VARCHAR(20),
	IMG_MAIN_PATH		VARCHAR(1000)   NOT NULL,
	IMG_DETAIL_PATH		VARCHAR(1000)   NOT NULL,
	CLASS_NO			INT				NOT NULL,
    FOREIGN KEY(CLASS_NO) REFERENCES CLASS_LIST(CLASS_NO) ON DELETE CASCADE
);
-- 1 ~ 10 요리/베이킹
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('요리/베이킹1', '/images/classList/cooking/bakery1.png', '/images/classList/cooking/bakery1-1.png', 1);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('요리/베이킹2', '/images/classList/cooking/bakery2.png', '/images/classList/cooking/bakery2-1.png', 2);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('요리/베이킹3', '/images/classList/cooking/bakery3.png', '/images/classList/cooking/bakery3-1.png', 3);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('요리/베이킹4', '/images/classList/cooking/bakery4.png', '/images/classList/cooking/bakery4-1.png', 4);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('요리/베이킹5', '/images/classList/cooking/bakery5.png', '/images/classList/cooking/bakery5-1.png', 5);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('요리/베이킹6', '/images/classList/cooking/bakery6.png', '/images/classList/cooking/bakery6-1.png', 6);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('요리/베이킹7', '/images/classList/cooking/bakery7.png', '/images/classList/cooking/bakery7-1.png', 7);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('요리/베이킹8', '/images/classList/cooking/bakery8.png', '/images/classList/cooking/bakery8-1.png', 8);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('요리/베이킹9', '/images/classList/cooking/bakery9.png', '/images/classList/cooking/bakery9-1.png', 9);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('요리/베이킹10', '/images/classList/cooking/bakery10.png', '/images/classList/cooking/bakery10-1.png', 10);
-- 11 ~ 20 댄스/스포츠
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('댄스/스포츠1', '/images/classList/dance/dance1.png', '/images/classList/dance/dance1-1.png', 11);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('댄스/스포츠2', '/images/classList/dance/dance2.png', '/images/classList/dance/dance2-1.png', 12);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('댄스/스포츠3', '/images/classList/dance/dance3.png', '/images/classList/dance/dance3-1.png', 13);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('댄스/스포츠4', '/images/classList/dance/dance4.png', '/images/classList/dance/dance4-1.png', 14);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('댄스/스포츠5', '/images/classList/dance/dance5.png', '/images/classList/dance/dance5-1.png', 15);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('댄스/스포츠6', '/images/classList/dance/dance6.png', '/images/classList/dance/dance6-1.png', 16);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('댄스/스포츠7', '/images/classList/dance/dance7.png', '/images/classList/dance/dance7-1.png', 17);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('댄스/스포츠8', '/images/classList/dance/dance8.png', '/images/classList/dance/dance8-1.png', 18);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('댄스/스포츠9', '/images/classList/dance/dance9.png', '/images/classList/dance/dance9-1.png', 19);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('댄스/스포츠10', '/images/classList/dance/dance10.png', '/images/classList/dance/dance10-1.png', 20);
-- 21 ~ 30 디자인
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('디자인1', '/images/classList/design/design1.png', '/images/classList/design/design1-1.png', 21);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('디자인2', '/images/classList/design/design2.png', '/images/classList/design/design2-1.png', 22);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('디자인3', '/images/classList/design/design3.png', '/images/classList/design/design3-1.png', 23);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('디자인4', '/images/classList/design/design4.png', '/images/classList/design/design4-1.png', 24);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('디자인5', '/images/classList/design/design5.png', '/images/classList/design/design5-1.png', 25);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('디자인6', '/images/classList/design/design6.png', '/images/classList/design/design6-1.png', 26);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('디자인7', '/images/classList/design/design7.png', '/images/classList/design/design7-1.png', 27);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('디자인8', '/images/classList/design/design8.png', '/images/classList/design/design8-1.png', 28);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('디자인9', '/images/classList/design/design9.png', '/images/classList/design/design9-1.png', 29);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('디자인10', '/images/classList/design/design10.png', '/images/classList/design/design10-1.png', 30);
-- 31 ~ 40 피트니스/스포츠
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('피트니스/스포츠1', '/images/classList/fitness/fitness1.png', '/images/classList/fitness/fitness1-1.png', 31);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('피트니스/스포츠2', '/images/classList/fitness/fitness2.png', '/images/classList/fitness/fitness2-1.png', 32);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('피트니스/스포츠3', '/images/classList/fitness/fitness3.png', '/images/classList/fitness/fitness3-1.png', 33);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('피트니스/스포츠4', '/images/classList/fitness/fitness4.png', '/images/classList/fitness/fitness4-1.png', 34);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('피트니스/스포츠5', '/images/classList/fitness/fitness5.png', '/images/classList/fitness/fitness5-1.png', 35);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('피트니스/스포츠6', '/images/classList/fitness/fitness6.png', '/images/classList/fitness/fitness6-1.png', 36);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('피트니스/스포츠7', '/images/classList/fitness/fitness7.png', '/images/classList/fitness/fitness7-1.png', 37);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('피트니스/스포츠8', '/images/classList/fitness/fitness8.png', '/images/classList/fitness/fitness8-1.png', 38);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('피트니스/스포츠9', '/images/classList/fitness/fitness9.png', '/images/classList/fitness/fitness9-1.png', 39);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('피트니스/스포츠10', '/images/classList/fitness/fitness10.png', '/images/classList/fitness/fitness10-1.png', 40);
-- 41 ~ 50 개발/공부
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('개발/공부1', '/images/classList/study/study1.png', '/images/classList/study/study1-1.png', 41);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('개발/공부2', '/images/classList/study/study2.png', '/images/classList/study/study2-1.png', 42);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('개발/공부3', '/images/classList/study/study3.png', '/images/classList/study/study3-1.png', 43);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('개발/공부4', '/images/classList/study/study4.png', '/images/classList/study/study4-1.png', 44);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('개발/공부5', '/images/classList/study/study5.png', '/images/classList/study/study5-1.png', 45);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('개발/공부6', '/images/classList/study/study6.png', '/images/classList/study/study6-1.png', 46);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('개발/공부7', '/images/classList/study/study7.png', '/images/classList/study/study7-1.png', 47);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('개발/공부8', '/images/classList/study/study8.png', '/images/classList/study/study8-1.png', 48);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('개발/공부9', '/images/classList/study/study9.png', '/images/classList/study/study9-1.png', 49);
INSERT INTO IMG_CLASS(IMG_NAME, IMG_MAIN_PATH, IMG_DETAIL_PATH, CLASS_NO) VALUES('개발/공부10', '/images/classList/study/study10.png', '/images/classList/study/study10-1.png', 50);
COMMIT;

-- 찜목록 WISH
CREATE TABLE WISH (
	CLASS_NO	INT		NOT NULL,
	USER_NO		INT		NOT NULL,
    FOREIGN KEY(CLASS_NO) REFERENCES CLASS_LIST(CLASS_NO) ON DELETE CASCADE,
    FOREIGN KEY(USER_NO) REFERENCES USER(USER_NO) ON DELETE CASCADE
);

-- 강의일정 SCHEDULE
CREATE TABLE SCHEDULE (
	SCH_NO			INT				NOT NULL        AUTO_INCREMENT PRIMARY KEY,
    CLASS_NO		INT				NOT NULL,
    SCH_START		DATETIME		NOT NULL,
    SCH_MAX_NUM		INT				NOT NULL,
    SCH_STATE		INT NOT NULL,
    FOREIGN KEY(CLASS_NO) REFERENCES CLASS_LIST(CLASS_NO) ON DELETE CASCADE
);
INSERT INTO SCHEDULE(CLASS_NO, SCH_START, SCH_MAX_NUM, SCH_STATE) VALUES('1', '20230622', 5, 0); 
INSERT INTO SCHEDULE(CLASS_NO, SCH_START, SCH_MAX_NUM, SCH_STATE) VALUES('1', '20230808', 5, 0);
INSERT INTO SCHEDULE(CLASS_NO, SCH_START, SCH_MAX_NUM, SCH_STATE) VALUES('1', '20230915', 5, 0); 
INSERT INTO SCHEDULE(CLASS_NO, SCH_START, SCH_MAX_NUM, SCH_STATE) VALUES('1', '20240117', 5, 0);
INSERT INTO SCHEDULE(CLASS_NO, SCH_START, SCH_MAX_NUM, SCH_STATE) VALUES('2', '20230627', 5, 0); 
INSERT INTO SCHEDULE(CLASS_NO, SCH_START, SCH_MAX_NUM, SCH_STATE) VALUES('2', '20230701', 5, 0);
INSERT INTO SCHEDULE(CLASS_NO, SCH_START, SCH_MAX_NUM, SCH_STATE) VALUES('2', '20230814', 5, 0); 
INSERT INTO SCHEDULE(CLASS_NO, SCH_START, SCH_MAX_NUM, SCH_STATE) VALUES('3', '20231112', 5, 0);
INSERT INTO SCHEDULE(CLASS_NO, SCH_START, SCH_MAX_NUM, SCH_STATE) VALUES('3', '20231223', 5, 0);

-- 수강신청 ENROLL
CREATE TABLE ENROLL (
    ENROLL_NO        INT             NOT NULL   AUTO_INCREMENT PRIMARY KEY,
    USER_NO          INT             NOT NULL,
    SCH_NO           INT             NOT NULL, 
    ENROLL_DATE      DATETIME        NOT NULL,
    ENROLL_PERSON    INT             NOT NULL,
    ENROLL_REQUEST   VARCHAR(1000)   NOT NULL,
    ENROLL_AMOUNT    INT             NOT NULL, 
    ENROLL_SALE      INT             NOT NULL, 
    ENROLL_PAY       INT             NOT NULL,  
	FOREIGN KEY(USER_NO) REFERENCES USER(USER_NO) ON DELETE CASCADE,
	FOREIGN KEY(SCH_NO)  REFERENCES SCHEDULE(SCH_NO) ON DELETE CASCADE
);

-- 리뷰 REVIEW
CREATE TABLE REVIEW (
	REVIEW_NO	    INT  		 NOT NULL   AUTO_INCREMENT PRIMARY KEY,
    USER_NO			INT  		 NOT NULL,
    CLASS_NO	    INT  		 NOT NULL,
	REVIEW_CONTENT  LONGTEXT	 NOT NULL,
    REVIEW_GRADE    DECIMAL(2,1) NOT NULL,
    WRITE_DATE      DATETIME     NOT NULL,
    FOREIGN KEY (USER_NO)  REFERENCES USER(USER_NO) ON DELETE CASCADE,
    FOREIGN KEY (CLASS_NO) REFERENCES CLASS_LIST(CLASS_NO) ON DELETE CASCADE
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
INSERT INTO COUPON (COUPON_NAME, COUPON_DISCOUNT, COUPON_START_DATE, COUPON_END_DATE)
VALUES ('신규', 20, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY));
INSERT INTO COUPON (COUPON_NAME, COUPON_DISCOUNT, COUPON_START_DATE, COUPON_END_DATE)
VALUES ('6월', 5, NOW(), DATE_ADD(LAST_DAY(CURDATE() - INTERVAL 1 MONTH), INTERVAL 1 DAY));
COMMIT;

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

