package project.animalfoot.aniproject.domain.admin.adboard;

import lombok.Data;

import java.util.List;

@Data
public class BoardListDTO {
    private int cpg;
    private int stblk;
    private int cntpg;
    private int edblk;
    private List<?> bdlist;

    public BoardListDTO(int cpg, int totalItems, int pageSize, List<?> bdlist) {
        this.cpg = cpg;
        this.cntpg = (int) Math.ceil((double) totalItems / pageSize);
        this.stblk = ((cpg - 1) / 10) * 10 + 1;
        this.edblk = Math.min(stblk + 10 - 1, cntpg);
        this.bdlist = bdlist;

    }
}