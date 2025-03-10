package project.animalfoot.aniproject.repository;

import project.animalfoot.aniproject.domain.user.User;
import project.animalfoot.aniproject.domain.user.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository {

    @Insert("insert into users (userid, userpwd, name, email) values (#{userid},#{userpwd},#{name},#{email})")
    int insertUser(UserDTO member);

    @Select("select * from users where userid = #{userid}")
    User findByUserid(String userid);

    @Select("select count(userid) from users where userid = #{userid}")
    int countByUserid(String userid);

    @Select("select count(email) from users where email = #{email}")
    int countByEmail(String mail);

    // 카카오 ID로 사용자 조회
    @Select("select * from users where kakao_id = #{kakaoId}")
    User findByKakaoId(String kakaoId);
}