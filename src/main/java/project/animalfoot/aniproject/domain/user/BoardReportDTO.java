package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BoardReportDTO {
    private int brNo;         // 신고 번호
    private int bdNo;         // 게시글 번호
    private String brId;      // 신고자 아이디
    private String brCategory; // 신고 분류
    private String brContent; // 신고 내용
    private LocalDateTime brDate;
    private int brStatus;     // 신고 상태

}
