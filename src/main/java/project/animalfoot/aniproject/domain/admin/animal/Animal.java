package project.animalfoot.aniproject.domain.admin.animal;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Animal {
    private int animal_no;           // 동물 번호
    private String nm;              // 동물 이름
    private Date entrnc_date;        // 입소 날짜
    private String spcs;            // 종 (예: 개, 고양이)
    private String breeds;          // 품종
    private String sexdstn;         // 성별 (예: 남자, 여자)
    private String age;             // 나이
    private BigDecimal bdwgh;       // 몸무게
    private String adp_sttus;        // 입양 상태 (대기, 입양완료 등)
    private String tmpr_prtc_sttus;   // 임시 보호 상태 (보호 중, 보호 완료 등)



}
