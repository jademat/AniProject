package project.animalfoot.aniproject.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.admin.adboard.NoticeListDTO;
import project.animalfoot.aniproject.repository.AdBoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdBoardServiceImpl implements AdBoardService {

    private final AdBoardRepository adBoardMapper;

}
