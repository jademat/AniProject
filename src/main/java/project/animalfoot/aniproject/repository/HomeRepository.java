package project.animalfoot.aniproject.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import project.animalfoot.aniproject.domain.admin.adboard.HoAdoptDTO;
import project.animalfoot.aniproject.domain.admin.adboard.HoBoardDTO;

import java.util.List;

@Mapper
public interface HomeRepository {

    @Select("select count(adono) adono from adopt where ado_stat = 1")
    int adoptCount();

    @Select("select count(animal_no)  from animal where adp_sttus = 'N'")
    int adoptAniCount();

    List<HoAdoptDTO> adoptList();

    List<HoBoardDTO> boardList();
}
