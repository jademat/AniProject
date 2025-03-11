package project.animalfoot.aniproject.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.animalfoot.aniproject.domain.user.BoardDTO;

import java.util.List;

@Mapper
public interface BoardRepository {
    // 모든 게시글 가져오기
    @Select("SELECT b.bd_no AS bdNo, b.uno AS uno, u.userid AS userId, b.bd_title AS bdTitle, " +
            "b.bd_contents AS bdContents, b.bd_regdate AS bdRegdate, b.bd_thumbs AS bdThumbs, b.bd_views AS bdViews " +
            "FROM board b " +
            "JOIN users u ON b.uno = u.uno " +
            "ORDER BY b.bd_no DESC")
    List<BoardDTO> selectAllBoards();

    // 조회수 증가
    @Update("UPDATE board SET bd_views = bd_views + 1 WHERE bd_no = #{bdNo}")
    void updateViewCount(int bdNo);
    // 게시물 상세 내역
    @Select("SELECT b.bd_no AS bdNo, b.uno AS uno, u.userid AS userId, b.bd_title AS bdTitle, b.bd_contents AS bdContents, b.bd_regdate AS bdRegdate, b.bd_thumbs AS bdThumbs, b.bd_views AS bdViews " +
            "FROM board b " +
            "JOIN users u ON b.uno = u.uno " +
            "WHERE b.bd_no = #{bdNo}")
    BoardDTO selectBoardById(int bdNo);
}
