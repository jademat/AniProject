package project.animalfoot.aniproject.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.animalfoot.aniproject.service.admin.AdAdoptionService;
import project.animalfoot.aniproject.service.admin.HomeService;

@Controller
@RequestMapping("home")
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final HomeService homeService;
    private final AdAdoptionService adAdoptionService;


    @GetMapping("/main")
    public String home() {



        return "views/admin/home";
    }
}
