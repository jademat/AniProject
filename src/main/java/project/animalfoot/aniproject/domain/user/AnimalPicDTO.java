package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AnimalPicDTO {

    private int animalNo;
    private String photoUrl;
}
