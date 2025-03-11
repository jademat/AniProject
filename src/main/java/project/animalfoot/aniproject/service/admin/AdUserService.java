package project.animalfoot.aniproject.service.admin;


import project.animalfoot.aniproject.domain.admin.user.UserDTO;
import project.animalfoot.aniproject.domain.admin.user.UserListDTO;

import java.util.List;

public interface AdUserService {

    UserListDTO readUser(int cpg);

    List<UserDTO> findUser(int cpg,String findtype, String findkey);

    int countFindUser(String findtype, String findkey);
}
