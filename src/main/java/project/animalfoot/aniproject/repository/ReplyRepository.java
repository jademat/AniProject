package project.animalfoot.aniproject.repository;

import org.apache.ibatis.annotations.*;
import project.animalfoot.aniproject.domain.user.Reply;
import project.animalfoot.aniproject.domain.user.ReplyDTO;

import java.util.List;

@Mapper
public interface ReplyRepository {

    // 게시글에 대한 댓글 목록 조회
    @Select("SELECT * FROM reply WHERE bd_no = #{bdNo} ORDER BY ref_no, re_no")
    List<Reply> selectRepliesByBoardId(int bdNo);


    // 댓글 추가
    @Insert("INSERT INTO reply (userid, bd_no, re_con, ref_no, re_regdate) " +
            "VALUES (#{userid}, #{bdNo}, #{reCon}, IFNULL(#{refNo}, last_insert_id()+1), NOW())")
    int insertReply(ReplyDTO reply);

    // 대댓글 추가
    @Insert("INSERT INTO reply (userid, bd_no, re_con, ref_no, re_regdate) " +
            "VALUES (#{userid}, #{bdNo}, #{reCon}, #{refNo}, NOW())")
    int insertSubReply(ReplyDTO reply);

    // 댓글 수정
    @Update("UPDATE reply SET re_con = #{reCon}, re_update = #{reUpdate} WHERE re_no = #{reNo}")
    int updateReply(ReplyDTO reply);

    // 댓글 삭제
    @Delete("DELETE FROM reply WHERE re_no = #{reNo}")
    int deleteReply(int reNo);





}
