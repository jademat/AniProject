package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Reply {

    private int reNo;
    private Long refNo;
    private String userid;
    private int bdNo;
    private String reCon;
    private LocalDateTime reRegdate;
    private LocalDateTime reUpdate;





}
