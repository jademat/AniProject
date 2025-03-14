
package project.animalfoot.aniproject.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.user.AdoptDTO;
import project.animalfoot.aniproject.domain.user.AnimalDTO;
import project.animalfoot.aniproject.domain.user.AnimalPicDTO;
import project.animalfoot.aniproject.domain.user.UserDTO;
import project.animalfoot.aniproject.repository.AnimalRepository;
import project.animalfoot.aniproject.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionSevrciceImpl implements AdoptionService {

    private final AnimalRepository adoptionRepository;
    private final UserRepository userRepository;

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

    @Override
    public void submitAdoption(AdoptDTO adoptDTO) {
        adoptionRepository.insertAdoption(adoptDTO);
        updateDoptApplyStatus(adoptDTO.getUno());  // dopt_apply 상태를 1로
    }

    @Override
    public void updateDoptApplyStatus(int userId) {
        int rowsAffected = userRepository.updateDoptApplyStatus(userId, 1);  // dopt_apply 상태를 1로 변경
        if (rowsAffected == 0) {
            throw new RuntimeException("User not found or failed to update status");
        }
    }


}