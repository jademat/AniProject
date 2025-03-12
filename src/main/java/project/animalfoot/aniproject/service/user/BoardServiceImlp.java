package project.animalfoot.aniproject.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.user.BoardDTO;
import project.animalfoot.aniproject.domain.user.BoardUpdateDTO;
import project.animalfoot.aniproject.domain.user.NewBoardDTO;
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
    @Override
    public void insertBoard(NewBoardDTO board) {
        boardMapper.insertBoard(board);
    }

    @Override
    public void saveBoard(NewBoardDTO board) {

    }

    @Override
    public boolean newBoard(NewBoardDTO newBoardDTO) {

        int result=boardMapper.insertBoard(newBoardDTO);
        return result >0;
    }

    @Override
    public boolean deleteBoard(int bdNo) {
        try {
            boardMapper.deleteById(bdNo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateBoard(BoardUpdateDTO boardUpdateDTO) {
        int updatedRows = boardMapper.updateBoard(boardUpdateDTO);
        return updatedRows > 0;
    }




}