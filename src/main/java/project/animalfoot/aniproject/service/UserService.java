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

    // 카카오 로그인 처리
    public User kakaoLogin(UserDTO user) {
        // 카카오 아이디로 사용자 조회
        User findUser = userMapper.findByKakaoId(user.getKakaoId());

        if (findUser == null) {
            // 만약 카카오 아이디로 등록된 사용자가 없으면, 새로운 사용자로 등록
            boolean isNewUser = newUser(user);  // 카카오 로그인 시에는 이메일과 카카오 아이디로 회원가입 처리
            if (isNewUser) {
                // 가입이 정상적으로 되면 새로 생성된 User 객체를 반환
                return userMapper.findByKakaoId(user.getKakaoId());
            } else {
                throw new IllegalStateException("회원 가입 실패");
            }
        }
        // 이미 카카오 아이디가 존재하는 사용자라면, 기존 사용자 반환
        return findUser;
    }




}