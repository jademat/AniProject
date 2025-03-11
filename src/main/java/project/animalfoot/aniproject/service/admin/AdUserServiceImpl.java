package project.animalfoot.aniproject.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
    public List<UserDTO> findUser(int cpg, String findtype, String findkey) {
        Map<String,Object> params = new HashMap<>();
        params.put("stnum",(cpg-1) * pageSize);
        params.put("pageSize",pageSize);
        params.put("findtype",findtype);
        params.put("findkey",findkey);
        return adUserMapper.selectFindUser(params);
    }

    @Override
    public int countFindUser(String findtype, String findkey) {
        Map<String,Object> params = new HashMap<>();
        params.put("pageSize",pageSize);
        params.put("findtype",findtype);
        params.put("findkey",findkey);
        return adUserMapper.countFindUser(params);
    }
}
