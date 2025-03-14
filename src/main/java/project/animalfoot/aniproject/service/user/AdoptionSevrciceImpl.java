
package project.animalfoot.aniproject.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.user.*;
import project.animalfoot.aniproject.repository.AnimalRepository;
import project.animalfoot.aniproject.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionSevrciceImpl implements AdoptionService {


    private final AnimalRepository adoptionRepository;
    private final UserRepository userRepository;

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
        // 입양 정보 추가 (ado_stat = 1)
        adoptionRepository.insertAdoption(adoptDTO);

        // 사용자의 dopt_apply 값 증가
        userRepository.updateDoptApply(adoptDTO.getUno());
    }
//    @Override
//    public void updateDoptApplyStatus(int userId) {
//        int rowsAffected = userRepository.updateDoptApplyStatus(userId, 1);  // dopt_apply 상태를 1로 변경
//        if (rowsAffected == 0) {
//            throw new RuntimeException("User not found or failed to update status");
//        }
//    }

    @Override
    public BoardListDTO readBoard(int cpg) {

        int stnum = (cpg - 1) *pageSize;
        int totalItems=adoptionRepository.countAnimal();
        List<AnimalDTO> adoptions = adoptionRepository.findAllAdoptions(stnum, pageSize);

        return new BoardListDTO(cpg, totalItems, pageSize,  adoptions);
    }

    @Override
    public List<UserDTO> selectAdopt() {
        return adoptionRepository.selectAdoptUser();
    }


}