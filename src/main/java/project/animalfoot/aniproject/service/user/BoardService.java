package project.animalfoot.aniproject.service.user;


import project.animalfoot.aniproject.domain.user.BoardDTO;
import project.animalfoot.aniproject.domain.user.BoardUpdateDTO;
import project.animalfoot.aniproject.domain.user.NewBoardDTO;

import java.util.List;

public interface BoardService {

    // 모든 게시글 가져오기
    public List<BoardDTO> getAllBoards() ;
    // 조회수 증가
    public void incrementViewCount(int bdNo) ;
    // 게시글 조회
    public BoardDTO getBoardById(int bdNo);
    // 게시글 작성
    void insertBoard(NewBoardDTO board);

    void saveBoard(NewBoardDTO board);

    boolean newBoard(NewBoardDTO board);


    boolean deleteBoard(int bdNo);

    boolean updateBoard(BoardUpdateDTO board);
}
