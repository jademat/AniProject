package project.animalfoot.aniproject.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import project.animalfoot.aniproject.domain.admin.adboard.Adopt;
import project.animalfoot.aniproject.domain.admin.adboard.AdoptDTO;

import java.util.List;

@Mapper
public interface AdAdoptionRepository {

    List<AdoptDTO> selectAdopt(int stnum, int pageSize);

    @Select("select count(adono) cntad from adopt")
    int countAdopt();

    @Select("select u.userid, ani.nm, a.adono, a.uno, a.animal_no, a.ado_raised, a.ado_members, a.ado_housing, a.ado_allagree, a.ado_reason, a.ado_cost, a.ado_source, a.ado_date, a.ado_stat\n" +
            "from adopt a\n" +
            "join users u on a.uno = u.uno\n" +
            "join animal ani on a.animal_no = ani.animal_no\n" +
            "where a.adono =  #{adono}")
    Adopt selectOneAdopt(int adono);


}
