package project.animalfoot.aniproject.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adboard")
@RequiredArgsConstructor
@Slf4j
public class AdBoardController {

    @GetMapping("/boardlist")
    public String board() {
        return "views/adboard/boardlist";
    }

    @GetMapping("/notice")
    public String notice() {
        return "views/adboard/notice";
    }
}
