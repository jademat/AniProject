insert into admin (ad_id,ad_pw,ad_email) values('admin12','admin123','admin12@admin.com');

insert into users (userid, userpwd, name, phone, addr, detailaddr, email, dopt_apply)
values
    ('user01', 'c7d0cccedf3900d458784c9fba1336d71c434bc380474cc219fda24a2d420f8f', '홍길동', '010-1234-5678', '서울시 강남구', '역삼동 123-45', 'user1@example.com', 0),
    ('user02', 'c7d0cccedf3900d458784c9fba1336d71c434bc380474cc219fda24a2d420f8f', '김철수', '010-2345-6789', '부산시 해운대구', '센텀시티 67번지', 'user2@example.com', 1),
    ('user03', 'c7d0cccedf3900d458784c9fba1336d71c434bc380474cc219fda24a2d420f8f', '이영희', '010-3456-7890', '인천시 남동구', '소래포구 12-3', 'user3@example.com', 0),
    ('user04', 'c7d0cccedf3900d458784c9fba1336d71c434bc380474cc219fda24a2d420f8f', '박지민', '010-4567-8901', '대구시 수성구', '범어동 88-10', 'user4@example.com', 1),
    ('user05', 'c7d0cccedf3900d458784c9fba1336d71c434bc380474cc219fda24a2d420f8f', '최유리', '010-5678-9012', '광주시 북구', '첨단지구 501번지', 'user5@example.com', 0);


-- 게시글 데이터 삽입
INSERT INTO board (uno, bd_title, bd_contents, bd_regdate, bd_thumbs, bd_views)
VALUES ((SELECT uno FROM users WHERE userid = 'user1'), '첫 번째 입양 후기', '정말 좋은 경험이었어요! 우리 강아지가 너무 귀엽습니다.', NOW(), 5, 120);

INSERT INTO board (uno, bd_title, bd_contents, bd_regdate, bd_thumbs, bd_views)

VALUES ((SELECT uno FROM users WHERE userid = 'user2'), '고양이 입양 후기', '처음에는 낯설어했지만, 지금은 완전히 적응했어요!', NOW(), 10, 200);
