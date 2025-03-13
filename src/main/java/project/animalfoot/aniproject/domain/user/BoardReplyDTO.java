package project.animalfoot.aniproject.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BoardReplyDTO {
    private BoardDTO board;
    private List<ReplyDTO> replies;
}
