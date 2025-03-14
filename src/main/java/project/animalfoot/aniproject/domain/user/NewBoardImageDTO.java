package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewBoardImageDTO {
    private String imgname;
    private int gno;
    private int imgsize;



}
