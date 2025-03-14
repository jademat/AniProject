package project.animalfoot.aniproject.service.user;


import project.animalfoot.aniproject.domain.user.*;

import java.util.List;
import java.util.Map;

public interface BoardService {

    BoardListDTO readBoard(int cpg);

    BoardListDTO findBoard(int cpg,String findtype,String findkey);

    int countfindBoard(Map<String, Object> params);

    // 조회수 증가
    public void incrementViewCount(int bdNo) ;
    // 게시글 조회
    public BoardDTO getBoardById(int bdNo);
    // 게시글 작성

    boolean newBoard(NewBoardDTO gal );

    boolean deleteBoard(int bdNo);

    boolean updateBoard(BoardUpdateDTO board);

    // 댓글 조회 (게시글 번호로)
    List<Reply> getRepliesByBoardId(int bdNo);


    // 댓글 수정
    boolean updateReply(ReplyDTO replyDTO);

    // 댓글 삭제
    boolean deleteReply(int reNo);

    // 댓글 작성
    boolean addReply(ReplyDTO replyDTO);
    // 대댓글 작성
    boolean addSubReply(ReplyDTO replyDTO);


}