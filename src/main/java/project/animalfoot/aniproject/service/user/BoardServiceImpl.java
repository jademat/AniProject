package project.animalfoot.aniproject.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.animalfoot.aniproject.domain.user.*;
import project.animalfoot.aniproject.repository.BoardRepository;
import project.animalfoot.aniproject.repository.ReplyRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardMapper;
    private final ReplyRepository replyMapper;

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