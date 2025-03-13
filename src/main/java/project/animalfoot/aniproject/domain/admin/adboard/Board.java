package project.animalfoot.aniproject.domain.admin.adboard;

import lombok.Data;

@Data
public class Board {
    private int bd_no;
    private int nuo;
    private String bd_title;
    private String bd_contents;
    private String bd_regdate;
    private String bd_update;
    private String bd_thumbs;
    private String bd_views;
    private String bd_report;
    private String userid;

}
