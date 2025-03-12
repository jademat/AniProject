package project.animalfoot.aniproject.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.animalfoot.aniproject.domain.user.*;
import project.animalfoot.aniproject.repository.AnimalRepository;

import java.util.List;

@Service
@Transactional
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;  // Repository를 통해 DB 접근


    // 지역구별 유기동물 현황
    public List<AnimalStatusDTO> getAnimalStatusByDistrict() {
        return animalRepository.getAnimalStatusByDistrict();
    }

    // 지역구별 파양된 강아지, 고양이 현황
    public List<ReturnedDTO> getReturnedDogsAndCats() {
        return animalRepository.getReturnedDogsAndCats();
    }

    // 지역구별 입양된 강아지, 고양이 현황
    public List<AdoptedDTO> getAdoptedDogsAndCats() {  // 반환 타입 수정
        return animalRepository.getAdoptedDogsAndCats();
    }

    // 지역구별 안락사된 강아지, 고양이 현황
    public List<EuthanizedDTO> getEuthanizedDogsAndCats() {  // 반환 타입 수정
        return animalRepository.getEuthanizedDogsAndCats();
    }

    // 지역구별 이송된 강아지, 고양이 현황
    public List<TransferredDTO> getTransferredDogsAndCats() {  // 반환 타입 수정
        return animalRepository.getTransferredDogsAndCats();
    }


}
