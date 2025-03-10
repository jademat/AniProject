package project.animalfoot.aniproject.service.user;

import project.animalfoot.aniproject.domain.user.AnimalDTO;
import project.animalfoot.aniproject.domain.user.AnimalPicDTO;

import java.util.List;

public interface AdoptionService {
    List<AnimalDTO> getAllAdoptions();
    AnimalDTO getAdoptionByAnimalNo(int animalNo);
    List<AnimalPicDTO> getPicsByAnimalNo(int animalNo);

}
