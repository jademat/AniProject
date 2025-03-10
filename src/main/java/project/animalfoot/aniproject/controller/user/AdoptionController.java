package project.animalfoot.aniproject.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adoption")
@RequiredArgsConstructor
@Slf4j
public class AdoptionController {

    @GetMapping("/write")
    public String write() {
        return "views/user/adoption/write";
    }

    @GetMapping("/list")
    public String list() {
        return "views/user/adoption/list";
    }

    @GetMapping("/view")
    public String view() {
        return "views/user/adoption/view";
    }

    @GetMapping("/process")
    public String process() {
        return "views/user/adoption/process";
    }
}
