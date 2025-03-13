package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdoptedDTO {
    private String district;
    private int adoptedDogs;  // 입양된 개 수
    private int adoptedCats;  // 입양된 고양이 수


}
