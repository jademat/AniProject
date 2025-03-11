package project.animalfoot.aniproject.service.user;


import project.animalfoot.aniproject.domain.user.BoardDTO;

import java.util.List;

public interface BoardService {

    // 모든 게시글 가져오기
    public List<BoardDTO> getAllBoards() ;
    // 조회수 증가
    public void incrementViewCount(int bdNo) ;
    // 게시글 조회
    public BoardDTO getBoardById(int bdNo);


}
