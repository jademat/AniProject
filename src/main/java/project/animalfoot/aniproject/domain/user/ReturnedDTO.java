package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReturnedDTO {

    private String district;
    private int returnedDogs;   // 파양된 개 수
    private int returnedCats;   // 파양된 고양이 수


}
