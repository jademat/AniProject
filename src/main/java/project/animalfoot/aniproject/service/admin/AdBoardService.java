package project.animalfoot.aniproject.service.admin;

import project.animalfoot.aniproject.domain.admin.adboard.Board;
import project.animalfoot.aniproject.domain.admin.adboard.BoardListDTO;

public interface AdBoardService {

        BoardListDTO readBoard(int cpg);

        Board readOneBoard(int bd_no);

        boolean deleteBoard(int bd_no);

}
