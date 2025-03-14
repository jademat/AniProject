package project.animalfoot.aniproject.domain.admin.adboard;

import lombok.Data;

@Data
public class Adopt {

    private int adono;
    private int uno;
    private int animalno;
    private int ado_raised;
    private int ado_members;
    private String ado_housing;
    private String ado_allagree;
    private String ado_reason;
    private String ado_cost;
    private String ado_source;
    private String ado_date;
    private int ado_stat;
    private String userid;
    private String nm;

    public String getAdoStatText() {
        switch (ado_stat) {
            case 1:
                return "신청";
            case 2:
                return "승인";
            case 3:
                return "반려";
            default:
                return "기타";
        }
    }
}
