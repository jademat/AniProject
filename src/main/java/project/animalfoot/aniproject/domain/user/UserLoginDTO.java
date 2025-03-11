package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginDTO {

    private String userid;
    private String userpwd;

}