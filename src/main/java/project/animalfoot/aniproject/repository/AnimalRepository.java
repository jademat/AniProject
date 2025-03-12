package project.animalfoot.aniproject.repository;

import org.apache.ibatis.annotations.*;

import project.animalfoot.aniproject.domain.user.*;


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





    // 지역구별 유기동물 현황
    @Select("SELECT district, " +
            "SUM(total_dogs) AS total_dogs, " +
            "SUM(returned_dogs) AS returned_dogs, " +
            "SUM(adopted_dogs) AS adopted_dogs, " +
            "SUM(euthanized_dogs) AS euthanized_dogs, " +
            "SUM(transferred_dogs) AS transferred_dogs, " +
            "SUM(total_cats) AS total_cats, " +
            "SUM(returned_cats) AS returned_cats, " +
            "SUM(adopted_cats) AS adopted_cats, " +
            "SUM(euthanized_cats) AS euthanized_cats, " +
            "SUM(transferred_cats) AS transferred_cats " +
            "FROM animal_status " +
            "GROUP BY district")
    List<AnimalStatusDTO> getAnimalStatusByDistrict();

    // 지역구별 파양된 강아지, 고양이 현황 (AnimalGraphDTO로 수정)
    @Select("SELECT district, SUM(returned_dogs) AS returned_dogs, SUM(returned_cats) AS returned_cats FROM animal_status GROUP BY district")
    List<ReturnedDTO> getReturnedDogsAndCats(); // 변경된 반환 타입

    // 지역구별 입양된 강아지, 고양이 현황
    @Select("SELECT district, " +
            "SUM(adopted_dogs) AS adopted_dogs, " +
            "SUM(adopted_cats) AS adopted_cats " +
            "FROM animal_status " +
            "GROUP BY district")
    List<AdoptedDTO> getAdoptedDogsAndCats();

    // 지역구별 안락사된 강아지, 고양이 현황
    @Select("SELECT district, " +
            "SUM(euthanized_dogs) AS euthanized_dogs, " +
            "SUM(euthanized_cats) AS euthanized_cats " +
            "FROM animal_status " +
            "GROUP BY district")
    List<EuthanizedDTO> getEuthanizedDogsAndCats();

    // 지역구별 이송된 강아지, 고양이 현황
    @Select("SELECT district, " +
            "SUM(transferred_dogs) AS transferred_dogs, " +
            "SUM(transferred_cats) AS transferred_cats " +
            "FROM animal_status " +
            "GROUP BY district")
    List<TransferredDTO> getTransferredDogsAndCats();
}


