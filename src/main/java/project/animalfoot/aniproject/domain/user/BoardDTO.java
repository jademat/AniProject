package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder

public class BoardDTO {
    private int bdNo;          // 게시글 번호
    private int uno;           // 작성자 ID (users 테이블과 연관)
    private String userId;     // 작성자 아이디
    private String bdTitle;    // 제목
    private String bdContents; // 내용
    private LocalDateTime bdRegdate; // 작성일
    private int bdThumbs;      // 추천 수
    private int bdViews;       // 조회 수
}