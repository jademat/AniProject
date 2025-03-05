package project.animalfoot.aniproject.repository;

import project.animalfoot.aniproject.domain.User;
import project.animalfoot.aniproject.domain.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository {

    @Insert("insert into members (userid, userpwd, name, email) values (#{userid},#{userpwd},#{name},#{email})")
    int insertUser(UserDTO member);

    @Select("select * from members where userid = #{userid}")
    User findByUserid(String userid);

    @Select("select count(userid) from members where userid = #{userid}")
    int countByUserid(String userid);

    @Select("select count(email) from members where email = #{email}")
    int countByEmail(String mail);
}