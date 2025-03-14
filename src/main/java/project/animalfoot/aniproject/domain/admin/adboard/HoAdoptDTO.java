package project.animalfoot.aniproject.domain.admin.adboard;

import lombok.Data;

@Data
public class HoAdoptDTO {
    private int adono;       // 신청번호
    private String userid;    // 신청자 (유저 아이디)
    private String nm; // 입양 동물
    private String ado_date;  // 신청일

}