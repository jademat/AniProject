package project.animalfoot.aniproject.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/support")
public class supportController {

    @GetMapping("/donate")
    public String donate() {
        return "views/user/support/donate";
    }

    @GetMapping("/financial")
    public String financial() {
        return "views/user/support/financial";
    }
}
