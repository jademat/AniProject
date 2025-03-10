package project.animalfoot.aniproject.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.animalfoot.aniproject.service.BoardService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/review/list")
    public String adoptionList() {
        return "views/user/board/review/list";
    }

    @GetMapping("/review/view")
    public String adoptionView() {
        return "views/user/board/review/view";
    }

    @GetMapping("/review/write")
    public String adoptionWrite() {
        return "views/user/board/review/write";
    }

    @GetMapping("/faq/list")
    public String faqList() {
        return "views/user/board/faq/list";
    }


    @GetMapping("/admission/list")
    public String volunteerList() {
        return "views/user/board/admission/list";
    }

    @GetMapping("/admission/view")  // 수정된 경로
    public String volunteerView() {
        return "views/user/board/admission/view";
    }

    @GetMapping("/admission/write")  // 수정된 경로
    public String volunteerWrite() {
        return "views/user/board/admission/write";
    }
}
