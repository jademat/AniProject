package project.animalfoot.aniproject.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.animalfoot.aniproject.domain.admin.login.Admin;
import project.animalfoot.aniproject.domain.admin.login.AdminDTO;
import project.animalfoot.aniproject.service.admin.AdminService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/login")
    public String login() {
        return "views/admin/login";
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginok(AdminDTO admin, HttpSession session) {
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        try {
            // 정상 처리시 상태코드 200 응답
            Admin loginadmin = adminService.loginAdmin(admin);
            if(loginadmin != null) {
                session.setAttribute("loginAdmin", loginadmin);
                session.setMaxInactiveInterval(1800); // 세션 유지 : 10분

                response = ResponseEntity.ok().build();

            }
        } catch (IllegalStateException e) {
            // 정상 처리시 상태코드 400 응답 - 클라이언트 잘못
            // 아이디나 비밀번호 잘못 입력시
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // 정상 처리시 상태코드 500 응답 - 서버 잘못
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/home")
    public String home() {
        return "views/admin/home";
    }


    @GetMapping("/setting")
    public String setting() {
        return "views/admin/setting";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}
