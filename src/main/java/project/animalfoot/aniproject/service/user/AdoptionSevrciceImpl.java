
package project.animalfoot.aniproject.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.user.AdoptDTO;
import project.animalfoot.aniproject.domain.user.AnimalDTO;
import project.animalfoot.aniproject.domain.user.AnimalPicDTO;
import project.animalfoot.aniproject.domain.user.BoardListDTO;
import project.animalfoot.aniproject.repository.AnimalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionSevrciceImpl implements AdoptionService {

    private final AnimalRepository adoptionRepository;

    @Value("${board.page-size}") private int pageSize;



    @Override
    public AnimalDTO getAdoptionByAnimalNo(int animalNo) {
        return adoptionRepository.findAdoptionByAnimalNo(animalNo);
    }

    @Override
    public List<AnimalPicDTO> getPicsByAnimalNo(int animalNo) {
        return adoptionRepository.findPicsByAnimalNo(animalNo);
    }

    @Override
    public void submitAdoption(AdoptDTO adoptDTO) {
        adoptionRepository.insertAdoption(adoptDTO);
    }

    @Override
    public BoardListDTO readBoard(int cpg) {

        int stnum = (cpg - 1) *pageSize;
        int totalItems=adoptionRepository.countAnimal();
        List<AnimalDTO> adoptions = adoptionRepository.findAllAdoptions(stnum, pageSize);

        return new BoardListDTO(cpg, totalItems, pageSize,  adoptions);
    }


}