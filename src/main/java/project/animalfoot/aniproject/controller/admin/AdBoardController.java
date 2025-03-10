package project.animalfoot.aniproject.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.animalfoot.aniproject.service.admin.AdBoardService;

@Controller
@RequestMapping("/adboard")
@RequiredArgsConstructor
@Slf4j
public class AdBoardController {

    private final AdBoardService adBoardService;

    @GetMapping("/boardlist")
    public String board() {
        return "views/adboard/boardlist";
    }

    @GetMapping("/notice")
    public String notice(Model m) {

        m.addAttribute("notdto",adBoardService.listNotice());

        return "views/adboard/notice";
    }
}
