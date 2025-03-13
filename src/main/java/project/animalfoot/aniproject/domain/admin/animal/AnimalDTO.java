package project.animalfoot.aniproject.domain.admin.animal;

import lombok.Data;

import java.util.Date;

@Data
public class AnimalDTO {

    private int animalNo;           // 동물 번호
    private String nm;              // 동물 이름
    private Date entrncDate;        // 입소 날짜
    private String spcs;            // 종 (예: 개, 고양이)
    private String breeds;          // 품종
    private String adpSttus;        // 입양 상태 (대기, 입양완료 등)
    private String tmprPrtcSttus;   // 임시 보호 상태 (보호 중, 보호 완료 등)


}
