package project.animalfoot.aniproject.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.admin.adboard.Adopt;
import project.animalfoot.aniproject.domain.admin.adboard.Board;
import project.animalfoot.aniproject.domain.admin.adboard.HoAdoptDTO;
import project.animalfoot.aniproject.domain.admin.adboard.HoBoardDTO;
import project.animalfoot.aniproject.repository.HomeRepository;

import java.util.List;


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

    @Override
    public List<HoAdoptDTO> hoadList() {
        return homeMapper.adoptList();
    }

    @Override
    public List<HoBoardDTO> hoboList() {
        return homeMapper.boardList();
    }


}
