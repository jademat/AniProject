package project.animalfoot.aniproject.service;

import project.animalfoot.aniproject.domain.User;
import project.animalfoot.aniproject.domain.UserDTO;
import project.animalfoot.aniproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userMapper;

    public boolean newUser(UserDTO user) {

        // 아이디 중복 체크
        if (userMapper.countByUserid(user.getUserid()) > 0) {
            throw new IllegalStateException("이미 존재하는 아이디입니다!!");
        }

        // 이메일 중복 체크
        if (userMapper.countByEmail(user.getEmail()) > 0) {
            throw new IllegalStateException("이미 존재하는 이메일입니다!!");
        }

        int result = userMapper.insertUser(user);
        return result == 1; // 회원정보가 테이블 저장되었는지 여부에 따라 true/false 반환
    }

    public User loginUser(UserDTO user) {
        User findUser = userMapper.findByUserid(user.getUserid());

        if (findUser == null || !findUser.getUserpwd().equals(user.getUserpwd())) {
            throw new IllegalStateException("아이디나 비밀번호가 일치하지 않습니다!!");

        }
        return findUser;
    }
}