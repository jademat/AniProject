package project.animalfoot.aniproject.repository;

import org.apache.ibatis.annotations.*;
import project.animalfoot.aniproject.domain.user.BoardDTO;
import project.animalfoot.aniproject.domain.user.BoardUpdateDTO;
import project.animalfoot.aniproject.domain.user.NewBoardDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardRepository {

    // 모든 게시글 가져오기
    @Select("SELECT b.bd_no AS bdNo, b.uno AS uno, u.userid AS userId, b.bd_title AS bdTitle, " +
            "b.bd_contents AS bdContents, b.bd_regdate AS bdRegdate, b.bd_thumbs AS bdThumbs, b.bd_views AS bdViews " +
            "FROM board b " +
            "JOIN users u ON b.uno = u.uno " +
            "ORDER BY b.bd_no DESC " +
            "LIMIT #{stnum}, #{pageSize}")
    List<BoardDTO> selectAllBoards(int stnum, int pageSize);


    // 조회수 증가
    @Update("UPDATE board SET bd_views = bd_views + 1 WHERE bd_no = #{bdNo}")
    void updateViewCount(int bdNo);

    // 게시물 상세 내역
    @Select("SELECT b.bd_no AS bdNo, b.uno AS uno, u.userid AS userId, b.bd_title AS bdTitle, b.bd_contents AS bdContents, b.bd_regdate AS bdRegdate, b.bd_thumbs AS bdThumbs, b.bd_views AS bdViews " +
            "FROM board b " +
            "JOIN users u ON b.uno = u.uno " +
            "WHERE b.bd_no = #{bdNo}")
    BoardDTO selectBoardById(int bdNo);

    // 게시물 작성
    @Insert("INSERT INTO board (uno, bd_title, bd_contents) " +
            "VALUES (#{uno}, #{bd_title}, #{bd_content})")
    int insertBoard(NewBoardDTO newBoardDTO);

    @Update("UPDATE board SET bd_title = #{bdTitle}, bd_contents = #{bdContents} WHERE bd_no = #{bdNo}")
    int updateBoard(BoardUpdateDTO boardUpdateDTO);



    @Select("SELECT COUNT(bd_no) AS cntbd FROM board")
    int countBoard();

    @Select("SELECT b.bd_no AS bdNo, b.uno AS uno, u.userid AS userId, b.bd_title AS bdTitle, " +
            "b.bd_contents AS bdContents, b.bd_regdate AS bdRegdate, b.bd_thumbs AS bdThumbs, b.bd_views AS bdViews " +
            "FROM board b " +
            "JOIN users u ON b.uno = u.uno " +
            "ORDER BY b.bd_no DESC")
    List<BoardDTO> selectBoard(int stnum, int pageSize);

    @Delete("DELETE FROM board WHERE bd_no = #{bdNo}")
    void deleteById(int bdNo);

    @Update("UPDATE board SET bd_report = 1 WHERE bd_no = #{bdNo}")
    void updateReportStatus(@Param("bdNo") int bdNo);


    @Select("SELECT b.bd_no AS bdNo, b.uno AS uno, u.userid AS userId, b.bd_title AS bdTitle, " +
            "b.bd_contents AS bdContents, b.bd_regdate AS bdRegdate, b.bd_thumbs AS bdThumbs, b.bd_views AS bdViews " +
            "FROM board b " +
            "JOIN users u ON b.uno = u.uno " +
            "WHERE ${findType} LIKE CONCAT('%', #{findkey}, '%') " +  // 동적 검색 조건
            "ORDER BY b.bd_no DESC " +
            "LIMIT #{stnum}, #{pageSize}")
    List<BoardDTO> selectFindBoard(Map<String, Object> params);

    @Select("SELECT count(b.bd_no) FROM board b JOIN users u ON b.uno = u.uno WHERE ${findType} LIKE CONCAT('%', #{findkey}, '%')")
    int countFindBoard(Map<String, Object> params);


}