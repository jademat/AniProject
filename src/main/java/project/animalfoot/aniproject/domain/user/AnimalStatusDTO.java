package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AnimalStatusDTO {
    private String district;         // 지역 구분
    private int total;               // 전체 동물 수
    private int totalDogs;           // 전체 개 수
    private int returnedDogs;        // 반환된 개 수
    private int adoptedDogs;         // 입양된 개 수
    private int euthanizedDogs;      // 안락사된 개 수
    private int transferredDogs;     // 이송된 개 수
    private int totalCats;           // 전체 고양이 수
    private int returnedCats;        // 반환된 고양이 수
    private int adoptedCats;         // 입양된 고양이 수
    private int euthanizedCats;      // 안락사된 고양이 수
    private int transferredCats;     // 이송된 고양이 수
}
