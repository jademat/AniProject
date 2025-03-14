package project.animalfoot.aniproject.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.admin.user.User;
import project.animalfoot.aniproject.domain.admin.user.UserDTO;
import project.animalfoot.aniproject.domain.admin.user.UserListDTO;
import project.animalfoot.aniproject.repository.AdUserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdUserServiceImpl implements AdUserService {

    private final AdUserRepository adUserMapper;
    @Value("20") private int pageSize;

    @Override
    public UserListDTO readUser(int cpg) {
        int stnum = (cpg -1) * pageSize;
        int totalItems = adUserMapper.countUser();
        List<UserDTO> users = adUserMapper.selectUser(stnum,pageSize);

        return new UserListDTO(cpg,totalItems,pageSize,users);
    }

    @Override
    public UserListDTO  findUser(int cpg, String findtype, String findkey) {
        int stnum = (cpg - 1) * pageSize;

        Map<String,Object> params = new HashMap<>();
        params.put("stnum",stnum);
        params.put("pageSize",pageSize);
        params.put("findtype",findtype);
        params.put("findkey",findkey);

        int totalItems = countFindUser(params);
        List<UserDTO> users = adUserMapper.selectFindUser(params);

        return new UserListDTO(cpg, totalItems, pageSize, users);
    }

    @Override
    public int countFindUser(Map<String, Object> params) {

        return adUserMapper.countFindUser(params);
    }

    @Override
    public User readOneUser(int uno) {
        User us = adUserMapper.selectOneUser(uno);
        return us;
    }

    @Override
    public boolean deleteUser(int uno) {
        try {
            adUserMapper.deleteUserid(uno);
            return true;
        }catch (Exception e){
            return false;

        }
    }

}
