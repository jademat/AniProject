package project.animalfoot.aniproject.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class User {

    private int mno;
    private String userid;
    private String userpwd;
    private String name;
    private String email;
    private LocalDateTime regdate;
}