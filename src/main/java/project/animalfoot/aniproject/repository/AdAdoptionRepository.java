package project.animalfoot.aniproject.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.animalfoot.aniproject.domain.admin.adboard.Adopt;
import project.animalfoot.aniproject.domain.admin.adboard.AdoptDTO;
import project.animalfoot.aniproject.domain.admin.animal.Animal;
import project.animalfoot.aniproject.domain.user.UserDTO;

import java.util.List;

@Mapper
public interface AdAdoptionRepository {

    List<AdoptDTO> selectAdopt(int stnum, int pageSize);

    @Select("select count(adono) cntad from adopt")
    int countAdopt();

    Adopt selectOneAdopt(int adono);

    @Update("UPDATE adopt SET ado_stat = #{adoStat} WHERE adono = #{adono} AND ado_stat = 1")
    int updateAdoStat(@Param("adono") int adono, @Param("adoStat") int adoStat);

    @Select("select animal_no, nm, entrnc_date, spcs, breeds, sexdstn, age, bdwgh, adp_sttus, tmpr_prtc_sttus from animal order by animal_no desc")
    List<Animal> selectAniList();

}
