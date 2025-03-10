package project.animalfoot.aniproject.repository;

import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.animalfoot.aniproject.domain.user.AdoptionDTO;
import project.animalfoot.aniproject.domain.user.AdoptionPicDTO;
import project.animalfoot.aniproject.domain.user.AdoptionStatusDTO;


import java.util.List;

@Mapper
public interface AdoptionRepository {   // animal 테이블의 모든 레코드 조회

    // animal 테이블의 모든 레코드 조회
    @Select("SELECT * FROM animal")
    List<AdoptionDTO> findAllAdoptions();

    // animal 테이블에서 동물 번호로 조회
    @Select("SELECT * FROM animal WHERE animal_no = #{animalNo}")
    AdoptionDTO findAdoptionByAnimalNo(@Param("animalNo") int animalNo);

    // animal_pic 테이블에서 동물 번호로 사진 조회
    @Select("SELECT * FROM animal_pic WHERE animal_no = #{animalNo}")
    List<AdoptionPicDTO> findPicsByAnimalNo(@Param("animalNo") int animalNo);
}
