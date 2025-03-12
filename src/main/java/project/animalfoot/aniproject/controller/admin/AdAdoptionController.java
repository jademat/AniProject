package project.animalfoot.aniproject.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adopt")
@RequiredArgsConstructor
@Slf4j
public class AdAdoptionController {

    @GetMapping("/adopt")
    public String adoptlist() {
        return "views/adadopt/adopt";
    }
    @GetMapping("/admission")
    public String admissionlist() {
        return "views/adadopt/admission";
    }

}
