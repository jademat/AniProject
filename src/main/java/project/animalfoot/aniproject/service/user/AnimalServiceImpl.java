package project.animalfoot.aniproject.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.user.adoption.AnimalDTO;
import project.animalfoot.aniproject.domain.user.adoption.AnimalPicDTO;
import project.animalfoot.aniproject.domain.user.adoption.AnimalStatusDTO;
import project.animalfoot.aniproject.repository.AnimalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

  private final AnimalRepository animalMapper;

    // 입양 가능한 동물 목록 가져오기
    public List<AnimalDTO> getAnimalListForAdoption() {
        return animalMapper.getAnimalListForAdoption();  // 입양 대기 상태인 동물들
    }

    // 동물 사진 정보 가져오기
    public List<AnimalPicDTO> getAnimalPics() {
        return animalMapper.getAnimalPics();  // 모든 동물에 대한 사진 목록
    }

    // 동물 상태 정보 가져오기
    public List<AnimalStatusDTO> getAnimalStatus() {
        return animalMapper.getAnimalStatus();  // 동물 상태 목록
    }
}