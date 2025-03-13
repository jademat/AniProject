package project.animalfoot.aniproject.domain.admin.user;

import lombok.Data;

import java.util.List;

@Data
public class UserListDTO {
    private int cpg;
    private int stblk;
    private int cntpg;
    private int edblk;
    private List<?> uslist;

    public UserListDTO(int cpg, int totalItems, int pageSize, List<?> uslist) {
        this.cpg = cpg;
        this.cntpg = (int) Math.ceil((double)totalItems/pageSize);
        this.uslist = uslist;
        this.stblk =  ((cpg -1) /10) * 10 +1;
        this.edblk = Math.min(stblk + 10 - 1, cntpg);
    }


}
