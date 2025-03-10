-- user 테이블
create table if not exists users (
    uno int auto_increment primary key,                 -- 유저 번호
    userid varchar(18) unique not null,                 -- 유저 아이디
    userpwd varchar(64) not null ,                      -- 유저 비밀번호
    name varchar(50) not null,                          -- 유저 이름
    phone varchar(50) not null,                         -- 유저 연락처
    addr varchar(150) not null,                         -- 유저 주소
    detailaddr varchar(150) not null,                   -- 유저
    email varchar(100) not null,                        -- 유저 이메일
    dopt_apply int default 0,                           -- 유저
    regdate datetime default  current_timestamp         -- 유저 생성일
);

-- admin 테이블
create table if not exists admin (
    ad_id varchar(50) primary key,                      -- 관리자 아이디
    ad_pw varchar(50) not null,                         -- 관리자 비밀번호
    ad_email varchar(100) not null                      -- 관리자 이메일
);

-- 게시판 테이블 (입양 후기)
create table if not exists board (
    bd_no int auto_increment primary key,                           -- 게시글 번호
    uno int not null,                       -- 게시글 작성자
    bd_title varchar(128) not null,                     -- 게시글 제목
    bd_contents text not null ,                         -- 게시글 내용
    bd_regdate datetime default current_timestamp,      -- 게시글 작성일
    bd_update datetime,                                 -- 게시글 수정일
    bd_thumbs int default 0,                            -- 게시글 추천수
    bd_views int default 0,                             -- 게시글 조회수
    bd_report int default 0,                            -- 게시글 신고상태
    foreign key (uno) references users (uno)
);

-- 댓글 테이블
create table if not exists reply (
    re_no int auto_increment  primary key,              -- 댓글 번호
    ref_no int,                                         -- 대댓글 번호
    uno int not null,                                   -- 댓글 작성자
    bd_no int not null,                                 -- 게시글 번호
    re_con text not null ,                              -- 댓글 내용
    re_regdate datetime default current_timestamp,      -- 댓글 작성일
    re_update datetime,                                 -- 댓글 수정일
    foreign key (uno) references users (uno),
    foreign key (bd_no) references board (bd_no)
);

-- animal 테이블 (변경 없음)
create table if not exists animal (
    animal_no int primary key,                -- 동물 번호
    nm varchar(100) not null,                 -- 동물 이름
    entrnc_date date not null,                -- 입소 날짜
    spcs varchar(50) not null,                -- 종 (예: 개, 고양이)
    breeds varchar(50),                       -- 품종
    sexdstn varchar(10),                      -- 성별 (예: 남자, 여자)
    age varchar(50),                          -- 나이
    bdwgh decimal(5,2),                       -- 몸무게 (예: 10.5 kg)
    adp_sttus varchar(20),                    -- 입양 상태 (예: 대기, 입양완료)
    tmpr_prtc_sttus varchar(20),              -- 임시 보호 상태 (예: 보호 중, 보호 완료)
    intrcn_mvp_url varchar(255),              -- 이동 URL (예: 이미지나 동영상 URL)
    intrcn_cn text,                           -- 소개 내용 (예: 동물에 대한 설명)
    tmpr_prtc_cn text                         -- 임시 보호자 연락처 (예: 보호자 연락처)
);

-- animal_pic 테이블 (변경 없음)
create table if not exists animal_pic (
    animal_no int not null,                                 -- 동물 번호
    photo_knd varchar(50) not null,                         -- 사진 종류 (예: 얼굴, 전체 등)
    photo_no int not null,                                  -- 사진 번호 (같은 동물에 대한 여러 장의 사진을 구분)
    photo_url varchar(255) not null,                        -- 사진 URL (사진이 저장된 경로 또는 URL)
    foreign key (animal_no) references animal(animal_no)    -- 동물 테이블과 연관
);

-- animal_status 테이블 수정 (동물 번호 연관 추가)
create table if not exists animal_status (
    district varchar(100) not null,                         -- 지역 구분 (예: 지역 이름)
    total int not null,                                     -- 전체 동물 수
    total_dogs int not null,                                -- 전체 개 수
    returned_dogs int not null,                             -- 반환된 개 수
    adopted_dogs int not null,                              -- 입양된 개 수
    euthanized_dogs int not null,                           -- 안락사된 개 수
    transferred_dogs int not null,                          -- 다른 곳으로 이송된 개 수
    total_cats int not null,                                -- 전체 고양이 수
    returned_cats int not null,                             -- 반환된 고양이 수
    adopted_cats int not null,                              -- 입양된 고양이 수
    euthanized_cats int not null,                           -- 안락사된 고양이 수
    transferred_cats int not null                          -- 다른 곳으로 이송된 고양이 수
);

-- 입양
CREATE TABLE IF NOT EXISTS adopt (
    adono INT AUTO_INCREMENT PRIMARY KEY,                    -- 신청서 번호
    uno int not null,                                        -- 신청자 번호
    animal_no INT not null,                                  -- 동물 번호
    ado_raised INT not null,                                 -- 키운 적 여부
    ado_members INT not null,                                -- 가족 구성원 수
    ado_housing VARCHAR(10) NOT NULL,                        -- 주거 형태
    ado_allagree VARCHAR(10) NOT NULL,                       -- 모두 찬성 여부
    ado_reason TEXT NOT NULL,                                -- 입양 이유
    ado_cost VARCHAR(100) NOT NULL,                          -- 1년 양육비
    ado_source VARCHAR(100) NOT NULL,                        -- 경로 유입
    ado_date datetime default current_timestamp,              -- 신청일
    foreign key (uno) references users (uno),
    foreign key (animal_no) references animal (animal_no)
);

-- 입소
CREATE TABLE IF NOT EXISTS intake (
    ta_no INT AUTO_INCREMENT PRIMARY KEY,                      -- 입소 신청 번호
    uno int not null,                                          -- 신청자 번호
    ta_name VARCHAR(10) NOT NULL,                              -- 동물 이름
    ta_age INT NOT NULL,                                       -- 동물 나이
    ta_species VARCHAR(50) NOT NULL,                           -- 동물 종
    ta_gender VARCHAR(10) NOT NULL,                            -- 동물 성별
    ta_vaccinated VARCHAR(10) NOT NULL,                        -- 접종 여부
    ta_neutered VARCHAR(10) NOT NULL,                          -- 중성화 여부
    ta_history VARCHAR(10) NOT NULL,                           -- 질병 이력
    ta_notes text NOT NULL,                                    -- 특이 사항
    ta_Hope VARCHAR(20) NOT NULL,                              -- 입소 희망일
    ta_ph VARCHAR(50) NOT NULL,                                -- 신청자 연락처
    ta_vp VARCHAR(20) NOT NULL,                                -- 방문 or 픽업
    ta_photo TEXT not null,                                    -- 동물 사진
    ta_date datetime default current_timestamp,                -- 신청일
    foreign key (uno) references users (uno)
);

-- 게시글 신고 0-일반/1-신고//2-삭제(사용자한테 삭제된 댓글 표시)
CREATE TABLE IF NOT EXISTS board_report (
    br_no INT AUTO_INCREMENT PRIMARY KEY,                      -- 게시글 신고 번호
    bd_no INT NOT NULL,                                        -- 게시글 번호
    br_id VARCHAR(50) NOT NULL,                                -- 신고자 아이디
    br_category VARCHAR(20) NOT NULL,                          -- 신고 분류
    br_content TEXT NOT NULL,                                  -- 신고 내용
    br_date datetime default current_timestamp,                -- 신고일
    br_status INT NOT NULL DEFAULT 0,                           -- 신고 상태
    foreign key (bd_no) references board (bd_no)
);

-- 댓글 신고 0-일반/1-신고//2-삭제(사용자한테 삭제된 댓글 표시)
CREATE TABLE IF NOT EXISTS reply_report (
    rr_no INT AUTO_INCREMENT PRIMARY KEY,                      -- 댓글 신고 번호
    re_no INT NOT NULL,                                        -- 댓글 번호
    reporter_id VARCHAR(50) NOT NULL,                          -- 신고자 아이디
    report_category VARCHAR(20) NOT NULL,                      -- 신고 분류
    report_content TEXT NOT NULL,                              -- 신고 내용
    report_date datetime default current_timestamp,            -- 신고일
    report_status INT NOT NULL DEFAULT 0 ,                      -- 신고 상태
    foreign key (re_no) references reply (re_no)
);

-- 공지사항 게시판
CREATE TABLE IF NOT EXISTS not_board (
    not_no int auto_increment primary key,                     -- 공지사항 번호
    ad_id varchar(15) not null,                                -- 공지사항 작성자
    not_title varchar(30) not null,                            -- 공지사항 제목
    not_cont text not null,                                    -- 공지사항 내용
    not_date datetime default current_timestamp,              -- 공지사항 작성일
    foreign key (ad_id) references admin(ad_id)
);