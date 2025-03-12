package project.animalfoot.aniproject.domain.admin.adboard;

import lombok.Data;

@Data
public class AdoptDTO {
    private int adono;       // 신청번호
    private String userid;    // 신청자 (유저 아이디)
    private String nm; // 입양 동물
    private String phone;     // 연락처
    private int ado_stat;     // 신청 상태
    private String ado_date;  // 신청일

    public String getAdoStatText() {
        switch (ado_stat) {
            case 1:
                return "신청";
            case 2:
                return "허가";
            case 3:
                return "반려";
            default:
                return "기타";
        }
    }
}