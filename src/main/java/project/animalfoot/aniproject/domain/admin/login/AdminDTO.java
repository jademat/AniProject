package project.animalfoot.aniproject.domain.admin.login;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDTO {
    private String ad_id;
    private String ad_pw;

}
