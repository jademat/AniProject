package project.animalfoot.aniproject.service.admin;

import org.apache.ibatis.annotations.Param;
import project.animalfoot.aniproject.domain.admin.adboard.Adopt;
import project.animalfoot.aniproject.domain.admin.adboard.AdoptListDTO;
import project.animalfoot.aniproject.domain.admin.animal.Animal;

import java.util.List;

public interface AdAdoptionService {

    AdoptListDTO readAdopt(int cpg);

    Adopt readOneAdopt(int adono);

    boolean updateAdoStat(int adono, int adoStat);

    List<Animal> aniList();
}
