package project.animalfoot.aniproject.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.user.BoardDTO;
import project.animalfoot.aniproject.repository.BoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImlp implements BoardService {

    private final BoardRepository boardMapper;


    @Override
    public List<BoardDTO> getAllBoards() {
        return boardMapper.selectAllBoards();
    }

    // 게시글 번호로 게시글 조회
    @Override
    public BoardDTO getBoardById(int bdNo) {
        return boardMapper.selectBoardById(bdNo);
    }

    // 조회수 증가
    @Override
    public void incrementViewCount(int bdNo) {
        boardMapper.updateViewCount(bdNo);
    }
}