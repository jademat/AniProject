package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferredDTO {
    private String district;
    private int transferredDogs;  // 이송된 개 수
    private int transferredCats;  // 이송된 고양이 수


}
