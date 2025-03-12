package project.animalfoot.aniproject.service.admin;

import project.animalfoot.aniproject.domain.admin.adboard.Adopt;
import project.animalfoot.aniproject.domain.admin.adboard.AdoptListDTO;

public interface AdAdoptionService {

    AdoptListDTO readAdopt(int cpg);

    Adopt readOneAdopt(int adono);
}
