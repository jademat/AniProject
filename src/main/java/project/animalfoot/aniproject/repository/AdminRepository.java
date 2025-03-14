package project.animalfoot.aniproject.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import project.animalfoot.aniproject.domain.admin.login.Admin;

@Mapper
public interface AdminRepository {

    @Select("select * from admin where ad_id = #{ad_id}")
    Admin findByAdmin(String admin);


}
