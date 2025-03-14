package project.animalfoot.aniproject.service.admin;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.admin.adboard.Adopt;
import project.animalfoot.aniproject.domain.admin.adboard.AdoptDTO;
import project.animalfoot.aniproject.domain.admin.adboard.AdoptListDTO;
import project.animalfoot.aniproject.domain.admin.animal.Animal;
import project.animalfoot.aniproject.repository.AdAdoptionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdAdoptionServiceImpl implements AdAdoptionService {

    private final AdAdoptionRepository adAdoptMapper;
    @Value("20") private int pageSize;

    @Override
    public AdoptListDTO readAdopt(int cpg) {
        int stnum = (cpg - 1) * pageSize;
        int totalItems = adAdoptMapper.countAdopt();
        List<AdoptDTO> adopts = adAdoptMapper.selectAdopt(stnum,pageSize);

        return new AdoptListDTO(cpg,totalItems,pageSize,adopts);
    }

    @Override
    public Adopt readOneAdopt(int adono) {
        Adopt adopt = adAdoptMapper.selectOneAdopt(adono);
        return adopt;
    }

    @Override
    public boolean updateAdoStat(int adono, int adoStat) {
        // 매퍼를 호출하여 데이터베이스에서 ado_stat 값을 업데이트
        int updatedRows = adAdoptMapper.updateAdoStat(adono, adoStat);

        // 업데이트된 행이 하나 이상이면 true 반환
        return updatedRows > 0;
    }

    @Override
    public List<Animal> aniList() {
        return adAdoptMapper.selectAniList();
    }


}
