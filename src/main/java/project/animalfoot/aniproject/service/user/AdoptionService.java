package project.animalfoot.aniproject.service.user;

import project.animalfoot.aniproject.domain.user.AdoptionDTO;
import project.animalfoot.aniproject.domain.user.AdoptionPicDTO;
import project.animalfoot.aniproject.domain.user.AdoptionStatusDTO;

import java.util.List;

public interface AdoptionService {
    List<AdoptionDTO> getAllAdoptions();
    AdoptionDTO getAdoptionByAnimalNo(int animalNo);
    List<AdoptionPicDTO> getPicsByAnimalNo(int animalNo);

}
