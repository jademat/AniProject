package project.animalfoot.aniproject.service.admin;


import project.animalfoot.aniproject.domain.admin.user.User;
import project.animalfoot.aniproject.domain.admin.user.UserDTO;
import project.animalfoot.aniproject.domain.admin.user.UserListDTO;

import java.util.List;
import java.util.Map;

public interface AdUserService {

    UserListDTO readUser(int cpg);

    UserListDTO  findUser(int cpg,String findtype, String findkey);

    int countFindUser(Map<String, Object> params);

    User readOneUser(int uno);

    boolean deleteUser(int uno);
}
