package project.animalfoot.aniproject.domain.user;

import lombok.Data;

@Data
public class BoardUpdateDTO {
    private int bdNo;          // 게시글 번호
    private String bdTitle;    // 제목
    private String bdContents; // 내용


}
