insert into admin (ad_id,ad_pw,ad_email) values('admin12','admin123','admin12@admin.com')

-- users 테이블
insert into users (userid, userpwd, name, phone, addr, detailaddr, email, dopt_apply)
values
    ('user1', 'c7d0cccedf3900d458784c9fba1336d71c434bc380474cc219fda24a2d420f8f', '홍길동', '010-1234-5678', '서울시 강남구', '역삼동 123-45', 'user1@example.com', 0),
    ('user2', 'c7d0cccedf3900d458784c9fba1336d71c434bc380474cc219fda24a2d420f8f', '김철수', '010-2345-6789', '부산시 해운대구', '센텀시티 67번지', 'user2@example.com', 1),
    ('user3', 'c7d0cccedf3900d458784c9fba1336d71c434bc380474cc219fda24a2d420f8f', '이영희', '010-3456-7890', '인천시 남동구', '소래포구 12-3', 'user3@example.com', 0),
    ('user4', 'c7d0cccedf3900d458784c9fba1336d71c434bc380474cc219fda24a2d420f8f', '박지민', '010-4567-8901', '대구시 수성구', '범어동 88-10', 'user4@example.com', 1),
    ('user5', 'c7d0cccedf3900d458784c9fba1336d71c434bc380474cc219fda24a2d420f8f', '최유리', '010-5678-9012', '광주시 북구', '첨단지구 501번지', 'user5@example.com', 0);
