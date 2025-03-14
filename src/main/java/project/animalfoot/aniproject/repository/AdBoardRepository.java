package project.animalfoot.aniproject.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import project.animalfoot.aniproject.domain.admin.adboard.Board;
import project.animalfoot.aniproject.domain.admin.adboard.BoardDTO;
import project.animalfoot.aniproject.domain.admin.adboard.NoticeListDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdBoardRepository {
    @Select("select b.bd_no,b.uno,b.bd_title,b.bd_regdate,b.bd_report,u.userid from board b join users u on b.uno = u.uno order by bd_no desc limit ${stnum},${pageSize}")
    List<BoardDTO> selectBoardList(int stnum,int pageSize);

    @Select("select count(bd_no) cntus from board")
    int countBoard();

    @Select("select b.bd_no,b.uno,b.bd_title,b.bd_contents,b.bd_regdate,b.bd_thumbs,b.bd_views,b.bd_report,u.userid from board b join users u on b.uno = u.uno where b.bd_no = #{bd_no} ")
    Board selectOneBoard(int bd_no);

    @Delete("delete from board where bd_no = #{bd_no}")
    void deleteBoard(int bd_no);

    List<BoardDTO> selectFindBoard(Map<String,Object> params);

    int countFindBoard(Map<String,Object> params);
}
