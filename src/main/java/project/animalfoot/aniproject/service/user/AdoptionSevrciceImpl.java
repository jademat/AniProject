
package project.animalfoot.aniproject.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.user.AdoptionDTO;
import project.animalfoot.aniproject.domain.user.AdoptionPicDTO;
import project.animalfoot.aniproject.domain.user.AdoptionStatusDTO;
import project.animalfoot.aniproject.repository.AdoptionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionSevrciceImpl implements AdoptionService {

    private final AdoptionRepository adoptionRepository;

    @Override
    public List<AdoptionDTO> getAllAdoptions() {
        return adoptionRepository.findAllAdoptions();
    }

    @Override
    public AdoptionDTO getAdoptionByAnimalNo(int animalNo) {
        return adoptionRepository.findAdoptionByAnimalNo(animalNo);
    }

    @Override
    public List<AdoptionPicDTO> getPicsByAnimalNo(int animalNo) {
        return adoptionRepository.findPicsByAnimalNo(animalNo);
    }
}