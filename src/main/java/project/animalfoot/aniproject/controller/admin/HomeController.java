package project.animalfoot.aniproject.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.animalfoot.aniproject.repository.AdUserRepository;
import project.animalfoot.aniproject.service.admin.AdAdoptionService;
import project.animalfoot.aniproject.service.admin.AdBoardService;
import project.animalfoot.aniproject.service.admin.HomeService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("home")
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final AdBoardService adBoardService;
    private final AdAdoptionService adAdoptionService;
    private final AdUserRepository adUserMapper;
    private final HomeService homeService;


    @GetMapping("/main")
    public String home(Model m, @RequestParam(defaultValue = "1") int cpg, HttpServletResponse response) {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        m.addAttribute("bddto", adBoardService.readBoard(cpg));

        m.addAttribute("adosdto", adAdoptionService.readAdopt(cpg));
        m.addAttribute("totalUser", adUserMapper.countUser());
        m.addAttribute("adoptUser", homeService.countAdopt());
        m.addAttribute("adoptAni", homeService.countAni());

        return "views/admin/home";
    }
}
