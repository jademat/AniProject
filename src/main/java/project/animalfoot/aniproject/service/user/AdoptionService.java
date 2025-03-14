package project.animalfoot.aniproject.service.user;

import project.animalfoot.aniproject.domain.user.AdoptDTO;
import project.animalfoot.aniproject.domain.user.AnimalDTO;
import project.animalfoot.aniproject.domain.user.AnimalPicDTO;
import project.animalfoot.aniproject.domain.user.BoardListDTO;

import java.util.List;

public interface AdoptionService {

    AnimalDTO getAdoptionByAnimalNo(int animalNo);
    List<AnimalPicDTO> getPicsByAnimalNo(int animalNo);


    public void submitAdoption(AdoptDTO adoptDTO);

    BoardListDTO readBoard(int cpg);
}
