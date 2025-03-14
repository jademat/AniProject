package project.animalfoot.aniproject.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.animalfoot.aniproject.domain.user.*;
import project.animalfoot.aniproject.repository.BoardRepository;
import project.animalfoot.aniproject.repository.ReplyRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardMapper;
    private final ReplyRepository replyMapper;
    @Value("${board.page-size}") private int pageSize;


   /* @Override
    public List<BoardDTO> getAllBoards() {
        return boardMapper.selectAllBoards();
    }*/

    // 게시글 번호로 게시글 조회
    @Override
    public BoardDTO getBoardById(int bdNo) {
        return boardMapper.selectBoardById(bdNo);
    }

    @Override
    public BoardListDTO readBoard(int cpg) {
        int stnum = (cpg - 1) * pageSize;
        int totalItems=boardMapper.countBoard();
        List<BoardDTO> boards = boardMapper.selectAllBoards(stnum,pageSize);

        return new BoardListDTO(cpg,totalItems,pageSize,boards);
    }

    @Override
    public BoardListDTO findBoard(int cpg, String findtype, String findkey) {

        Map<String,Object> params=new HashMap<>();
        params.put("stnum",(cpg-1)*pageSize);
        params.put("pageSize",pageSize);
        params.put("findType",findtype);
        params.put("findkey",findkey);

        int totalCount= countfindBoard(params);

       List<BoardDTO> boardList= boardMapper.selectFindBoard(params);

        return new BoardListDTO(cpg,totalCount,pageSize,boardList);
    }



    @Override
    public int countfindBoard(Map<String, Object> params) {


        return boardMapper.countFindBoard(params);
    }

    // 조회수 증가
    @Override
    public void incrementViewCount(int bdNo) {
        boardMapper.updateViewCount(bdNo);
    }

    // 게시글 작성
    @Override
    public boolean newBoard(NewBoardDTO newBoardDTO) {
        int result = boardMapper.insertBoard(newBoardDTO);
        return result > 0;
    }

    // 게시글 삭제
    @Override
    public boolean deleteBoard(int bdNo) {
        try {
            boardMapper.deleteById(bdNo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 게시글 수정
    @Override
    public boolean updateBoard(BoardUpdateDTO boardUpdateDTO) {
        int updatedRows = boardMapper.updateBoard(boardUpdateDTO);
        return updatedRows > 0;
    }

    // 댓글 조회 (게시글 번호로 댓글 조회)
    @Transactional
    @Override
    public List<Reply> getRepliesByBoardId(int bdNo) {
        return replyMapper.selectRepliesByBoardId(bdNo);
    }

    // 댓글 작성
    @Transactional
    @Override
    public boolean addReply(ReplyDTO replyDTO) {
        int result = replyMapper.insertReply(replyDTO);
        return result > 0;
    }
    // 대댓글 작성
    @Override
    public boolean addSubReply(ReplyDTO replyDTO) {
        int result = replyMapper.insertSubReply(replyDTO);
        log.info("replyMapper.insertSubReply");
        return result > 0;
    }

    // 댓글 수정
    @Transactional
    @Override
    public boolean updateReply(ReplyDTO replyDTO) {
        int result = replyMapper.updateReply(replyDTO);
        return result > 0;
    }

    // 댓글 삭제
    @Transactional
    @Override
    public boolean deleteReply(int reNo) {
        int result = replyMapper.deleteReply(reNo);
        return result > 0;
    }
}