package project.animalfoot.aniproject.domain.admin.adboard;

import lombok.Data;

import java.util.List;

@Data
public class AdoptListDTO {
    private int cpg;
    private int stblk;
    private int cntpg;
    private int edblk;
    private List<?> adolist;

    public AdoptListDTO(int cpg, int totalItems, int pageSize, List<?> adolist) {
        this.cpg = cpg;
        this.cntpg = (int) Math.ceil((double)totalItems/pageSize);
        this.stblk =  ((cpg -1) /10) * 10 +1;
        this.edblk = Math.min(stblk + 10 - 1, cntpg);
        this.adolist = adolist;
    }


}
