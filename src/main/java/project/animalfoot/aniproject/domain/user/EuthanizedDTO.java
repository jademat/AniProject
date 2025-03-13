package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EuthanizedDTO {
    private String district;
    private int euthanizedDogs;  // 안락사된 개 수
    private int euthanizedCats;  // 안락사된 고양이 수


}
