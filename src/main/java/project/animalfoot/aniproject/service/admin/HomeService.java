package project.animalfoot.aniproject.service.admin;

import project.animalfoot.aniproject.domain.admin.adboard.HoAdoptDTO;
import project.animalfoot.aniproject.domain.admin.adboard.HoBoardDTO;

import java.util.List;

public interface HomeService {

    int countAdopt();

    int countAni();

    List<HoAdoptDTO> hoadList();

    List<HoBoardDTO> hoboList();

}
