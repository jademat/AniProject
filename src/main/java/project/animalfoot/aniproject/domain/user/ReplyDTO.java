package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReplyDTO {
    private String userid;        // 작성자 번호
    private int bdNo;       // 게시글 번호
    private String reCon;// 댓글 내용
    private Long refNo;     // 부모 댓글 번호 (대댓글일 때만 사용, 일반 댓글은 null)
}
