package project.animalfoot.aniproject.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.animalfoot.aniproject.domain.user.UserDTO;
import project.animalfoot.aniproject.domain.user.UserLoginDTO;
import project.animalfoot.aniproject.service.user.UserService;

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
            // 정상 처리 시 상태 코드 200으로 응답
            userService.newUser(user);
            // 성공 후 메인 페이지로 리디렉션
            return ResponseEntity.ok().body("redirect:/");  // 메인 페이지로 리디렉션
        } catch (IllegalStateException e) {
            // 비정상 처리 시 상태 코드 400으로 응답 - 클라이언트 잘못.
            // 중복 아이디나 중복 이메일 사용 시
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // 비정상 처리 시 상태 코드 500으로 응답 - 서버 잘못
            log.error("회원가입 중 예외 발생", e);
            e.printStackTrace();
        }

        return response;
    }

    @GetMapping("/user/login")
    public String login() {
        return "views/user/user/login";
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> loginok(UserLoginDTO user, HttpSession session) {
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();
        log.info("submit된 로그인 정보 : {}", user);

        try {
            // 로그인 처리
            UserDTO loginUser = userService.loginUser(user); // 로그인 처리
            if (loginUser != null) {
                // 로그인 성공
                session.setAttribute("loginUser", loginUser); // 로그인 세션 설정
                session.setMaxInactiveInterval(1800); // 세션 유지 시간 설정: 10분

                response = ResponseEntity.ok().build();
            }
        } catch (IllegalStateException e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return response;
    }

    @GetMapping("/user/myinfo")
    public String getMyInfo(Model model, HttpSession session) {
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:login";  // 로그인되지 않았다면 로그인 페이지로 리다이렉트
        }

        try {
            model.addAttribute("user", loginUser);
            return "views/user/user/myinfo";  // myinfo.html로 사용자 정보 전달
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // 오류 발생 시 error.html로 리다이렉트
        }
    }

    @GetMapping("/user/edit")
    public String edit() {
        return "views/user/user/edit";
    }

    // 사용자 정보 수정 처리
    @PostMapping("/user/edit")
    public String editOk(UserDTO user, String confirmUserpwd, HttpSession session) {
        log.info(" controller editok {}",user);

        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/user/login";  // 로그인되지 않았다면 로그인 페이지로 리다이렉트
        }

        try {
            // 수정된 정보 업데이트
            userService.updateUser(user);

            return "redirect:/user/myinfo";  // 수정 완료 후 사용자 정보 페이지로 리다이렉트
        } catch (Exception e) {
            log.error("회원정보 수정 중 오류 발생", e);
            return "error";  // 오류 발생 시 error 페이지로 리다이렉트
        }
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // 세션 삭제 (로그아웃)
        return "redirect:/";  // 로그아웃 후 메인 페이지로 이동
    }
}