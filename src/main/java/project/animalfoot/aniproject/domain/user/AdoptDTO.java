package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdoptDTO {

    private int uno;                // 신청자 번호
    private int animal_no;          // 동물 번호
    private int ado_raised;         // 키운 적 여부
    private int ado_members;        // 가족 구성원 수
    private String ado_housing;     // 주거 형태
    private String ado_allagree;    // 모두 찬성 여부
    private String ado_reason;      // 입양 이유
    private String ado_cost;        // 1년 양육비
    private String ado_source;

}
