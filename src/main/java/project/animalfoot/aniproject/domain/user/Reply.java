package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reply {

    private String re_no;
    private String ref_no;
    private int uno;
    private int bd_no;
    private String comments;
}
