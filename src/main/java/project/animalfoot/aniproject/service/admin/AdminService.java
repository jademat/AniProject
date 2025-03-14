package project.animalfoot.aniproject.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.animalfoot.aniproject.domain.admin.login.Admin;
import project.animalfoot.aniproject.domain.admin.login.AdminDTO;
import project.animalfoot.aniproject.repository.AdminRepository;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminMapper;

    public Admin loginAdmin(AdminDTO admin) {
        Admin findAdmin = adminMapper.findByAdmin(admin.getAd_id());

        if(findAdmin == null || !findAdmin.getAd_pw().equals(admin.getAd_pw())) {
            throw new IllegalStateException("아이디 비밀번호 일치하지 않음");
        }
        return findAdmin;
    }


}
