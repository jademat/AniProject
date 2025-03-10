package project.animalfoot.aniproject.reposiotry.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import project.animalfoot.aniproject.domain.admin.adboard.NoticeListDTO;

import java.util.List;

@Mapper
public interface AdBoardRepository {

    @Select("select not_no,ad_id,not_title,not_date  from not_board order by not_no desc")
    List<NoticeListDTO> selectNotice();
}
