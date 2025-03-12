package project.animalfoot.aniproject.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.animalfoot.aniproject.domain.user.AdoptDTO;
import project.animalfoot.aniproject.domain.user.AnimalDTO;
import project.animalfoot.aniproject.domain.user.AnimalPicDTO;

import java.util.List;

@Mapper
public interface AnimalRepository {   // animal 테이블의 모든 레코드 조회

    // animal 테이블의 모든 레코드 조회
    @Select("SELECT * FROM animal")
    List<AnimalDTO> findAllAdoptions();

    // animal 테이블에서 동물 번호로 조회
    @Select("SELECT * FROM animal WHERE animal_no = #{animalNo}")
    AnimalDTO findAdoptionByAnimalNo(@Param("animalNo") int animalNo);

    // animal_pic 테이블에서 동물 번호로 사진 조회
    @Select("SELECT * FROM animal_pic WHERE animal_no = #{animalNo}")
    List<AnimalPicDTO> findPicsByAnimalNo(@Param("animalNo") int animalNo);

    @Insert("INSERT INTO adopt (uno, animal_no, ado_raised, ado_members, ado_housing, ado_allagree, ado_reason, ado_cost, ado_source) " +
            "VALUES (#{uno}, #{animal_no}, #{ado_raised}, #{ado_members}, #{ado_housing}, #{ado_allagree}, #{ado_reason}, #{ado_cost}, #{ado_source})")
    void insertAdoption(AdoptDTO adoptDTO);


}
