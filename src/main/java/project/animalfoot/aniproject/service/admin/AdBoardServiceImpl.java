package project.animalfoot.aniproject.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.admin.adboard.Board;
import project.animalfoot.aniproject.domain.admin.adboard.BoardDTO;
import project.animalfoot.aniproject.domain.admin.adboard.BoardListDTO;
import project.animalfoot.aniproject.domain.admin.adboard.NoticeListDTO;
import project.animalfoot.aniproject.domain.admin.user.UserDTO;
import project.animalfoot.aniproject.domain.admin.user.UserListDTO;
import project.animalfoot.aniproject.repository.AdBoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdBoardServiceImpl implements AdBoardService {

    private final AdBoardRepository adBoardMapper;
    @Value("20") private int pageSize;

    @Override
    public BoardListDTO readBoard(int cpg) {

        int stnum = (cpg -1) * pageSize;
        int totalItems = adBoardMapper.countBoard();
        List<BoardDTO> boards = adBoardMapper.selectBoardList(stnum, pageSize);

        return new BoardListDTO(cpg,totalItems,pageSize,boards);

    }

    @Override
    public Board readOneBoard(int bd_no) {
        Board board = adBoardMapper.selectOneBoard(bd_no);
        return board;
    }

    @Override
    public boolean deleteBoard(int bd_no) {
        try {
            adBoardMapper.deleteBoard(bd_no);
            return true;
        } catch (Exception e){
            return false;
        }

    }
}
