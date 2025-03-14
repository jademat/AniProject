package project.animalfoot.aniproject.service.user;

import project.animalfoot.aniproject.domain.user.*;

import java.util.List;

public interface AdoptionService {

    AnimalDTO getAdoptionByAnimalNo(int animalNo);
    List<AnimalPicDTO> getPicsByAnimalNo(int animalNo);


    public void submitAdoption(AdoptDTO adoptDTO);


    // users 테이블의 dopt_aply에 1
//    void updateDoptApplyStatus(int userId);

    BoardListDTO readBoard(int cpg);

    List<UserDTO> selectAdopt();
}
