package project.animalfoot.aniproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    @GetMapping("/login")
    public String login() {
        return "views/admin/login";
    }

    @GetMapping("/home")
    public String home() {
        return "views/admin/home";
    }

    @GetMapping("/userlist")
    public String list() {
        return "views/admin/userlist";
    }

    @GetMapping("/setting")
    public String setting() {
        return "views/admin/setting";
    }
}
