package project.animalfoot.aniproject.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import project.animalfoot.aniproject.domain.admin.login.Admin;

@Mapper
public interface HomeRepository {

    @Select("select count(adono) adono from adopt where ado_stat = 1")
    int adoptCount();

    @Select("select count(animal_no)  from animal where adp_sttus = 'N'")
    int adoptAniCount();
}
