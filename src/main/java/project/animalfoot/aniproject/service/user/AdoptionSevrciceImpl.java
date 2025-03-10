
package project.animalfoot.aniproject.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.user.AnimalDTO;
import project.animalfoot.aniproject.domain.user.AnimalPicDTO;
import project.animalfoot.aniproject.repository.AnimalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionSevrciceImpl implements AdoptionService {

    private final AnimalRepository adoptionRepository;

    @Override
    public List<AnimalDTO> getAllAdoptions() {
        return adoptionRepository.findAllAdoptions();
    }

    @Override
    public AnimalDTO getAdoptionByAnimalNo(int animalNo) {
        return adoptionRepository.findAdoptionByAnimalNo(animalNo);
    }

    @Override
    public List<AnimalPicDTO> getPicsByAnimalNo(int animalNo) {
        return adoptionRepository.findPicsByAnimalNo(animalNo);
    }
}