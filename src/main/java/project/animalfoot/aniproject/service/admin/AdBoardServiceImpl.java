package project.animalfoot.aniproject.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.admin.adboard.NoticeListDTO;
import project.animalfoot.aniproject.reposiotry.admin.AdBoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdBoardServiceImpl implements AdBoardService {

    private final AdBoardRepository adBoardMapper;

    @Override
    public NoticeListDTO listNotice() {
        List<NoticeListDTO> notices = adBoardMapper.selectNotice();
        return new NoticeListDTO();
    }
}
