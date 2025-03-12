package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AnimalStatusDTO {
    private String district;         // 지역 구분
    // private int total;               // 전체 동물 수
    private int total_dogs;           // 전체 개 수
    private int returned_dogs;        // 파양된 개 수
    private int adopted_dogs;         // 입양된 개 수
    private int euthanized_dogs;      // 안락사된 개 수
    private int transferred_dogs;     // 이송된 개 수
    private int total_cats;           // 전체 고양이 수
    private int returned_cats;        // 파양된 고양이 수
    private int adopted_cats;         // 입양된 고양이 수
    private int euthanized_cats;      // 안락사된 고양이 수
    private int transferred_cats;     // 이송된 고양이 수


}
