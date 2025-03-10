package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;
import java.beans.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
public class AdoptionDTO {
    private int animalNo;           // 동물 번호
    private String nm;              // 동물 이름
    private Date entrncDate;        // 입소 날짜
    private String spcs;            // 종 (예: 개, 고양이)
    private String breeds;          // 품종
    private String sexdstn;         // 성별 (예: 남자, 여자)
    private String age;             // 나이
    private BigDecimal bdwgh;       // 몸무게
    private String adpSttus;        // 입양 상태 (대기, 입양완료 등)
    private String tmprPrtcSttus;   // 임시 보호 상태 (보호 중, 보호 완료 등)
    private String intrcnMvpUrl;    // 이동 URL (이미지나 동영상 URL)
    private String intrcnCn;        // 소개 내용
    private String tmprPrtcCn;      // 임시 보호자 연락처

}