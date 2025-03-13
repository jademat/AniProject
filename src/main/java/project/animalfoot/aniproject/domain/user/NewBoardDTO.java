package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewBoardDTO {


    private int uno;
    private String bd_title;
    private String bd_content;

}
