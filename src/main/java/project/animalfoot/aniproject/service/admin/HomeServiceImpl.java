package project.animalfoot.aniproject.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.admin.adboard.Adopt;
import project.animalfoot.aniproject.domain.admin.adboard.Board;
import project.animalfoot.aniproject.repository.HomeRepository;



@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final HomeRepository homeMapper;


    @Override
    public int countAdopt() {
        return homeMapper.adoptCount();
    }

    @Override
    public int countAni() {
        return homeMapper.adoptAniCount();
    }
}
