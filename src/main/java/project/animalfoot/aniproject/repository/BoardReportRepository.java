package project.animalfoot.aniproject.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import project.animalfoot.aniproject.domain.user.BoardReportDTO;

@Mapper
public interface BoardReportRepository {   // animal 테이블의 모든 레코드 조회

    @Insert("INSERT INTO board_report (bd_no, br_id, br_category, br_content, br_date, br_status) " +
            "VALUES (#{bdNo}, #{brId}, #{brCategory}, #{brContent}, NOW(), 1)")
    void insertReport(BoardReportDTO reportDTO);



}
