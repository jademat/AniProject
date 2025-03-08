package project.animalfoot.aniproject.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.animalfoot.aniproject.domain.User;
import project.animalfoot.aniproject.domain.UserDTO;
import project.animalfoot.aniproject.service.UserService;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/join")
    public String join() {
        return "views/user/user/join";
    }

    @PostMapping("/user/join")
    public ResponseEntity<?> joinok(UserDTO user) {
        // 회원 가입 처리시 기타오류 발생에 대한 응답코드 설정
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        log.info("submit된 회원 정보 : {}", user);

        try {
            // 정상 처리 시 상태코드 200으로 응답
            userService.newUser(user);
            response = ResponseEntity.ok().build();
        }catch (IllegalStateException e) {
            // 비정상 처리 시 상태코드 400으로 응답. - 클라이언트 잘못.
            // 중복 아이디나 중복 이메일 사용 시
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();
        }catch (Exception e) {
            // 비정상 처리 시 상태코드 500으로 응답 - 서버 잘못
            e.printStackTrace();
        }

        return response;

    }

    @GetMapping("/user/login")
    public String login() {
        return "views/user/user/login";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginok(UserDTO user, HttpSession session) {
        // 로그인 처리시 기타오류 발생에 대한 응답코드 설정
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        log.info("submit된 로그인 정보 : {}", user);

        try {
            // 정상 처리 시 상태코드 200으로 응답
            User loginUser = userService.loginUser(user);
            session.setAttribute("loginUser", loginUser);
            session.setMaxInactiveInterval(600);   // 세션 유지 : 10분

            response = ResponseEntity.ok().build();
        }catch (IllegalStateException e) {
            // 비정상 처리 시 상태코드 400으로 응답. - 클라이언트 잘못.
            // 아이디나 비밀번호 잘못 입력 시
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();
        }catch (Exception e) {
            // 비정상 처리 시 상태코드 500으로 응답 - 서버 잘못
            e.printStackTrace();
        }

        return response;

    }





    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 제거
        return "redirect:/";
    }


    @GetMapping("/aboutus/hello")
    public String hello() {
        return "views/user/aboutus/hello";
    }

    @GetMapping("/aboutus/location")
    public String location() {
        return "views/user/aboutus/location";
    }

    @GetMapping("/aboutus/burget")
    public String burget() {
        return "views/user/aboutus/burget";
    }

    @GetMapping("/aboutus/adoptionProgress")
    public String adoption() {
        return "views/user/aboutus/adoptionProgress";
    }


}