package project.animalfoot.aniproject.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import project.animalfoot.aniproject.domain.admin.user.UserDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdUserRepository {
    @Select("select uno, userid,name,addr,email,regdate from users order by uno desc limit ${stnum},${pageSize}")
    List<UserDTO> selectUser(int stnum, int pageSize);

    @Select("select count(uno) cntus from users")
    int countUser();

    List<UserDTO> selectFindUser(Map<String,Object> params);

    int countFindUser(Map<String,Object> params);
}
