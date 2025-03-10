package project.animalfoot.aniproject.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.animalfoot.aniproject.service.user.AnimalServiceImpl;


import java.util.List;

@Controller
@RequestMapping("/adoption")
@RequiredArgsConstructor
@Slf4j
public class AdoptionController {

    private final AnimalServiceImpl animalService;

    // 입양 리스트 페이지
    @GetMapping("/list")
    public String list(Model model) {
        // 동물 목록을 서비스에서 가져와서 모델에 추가
        List<AnimalDTO> animalList = animalService.getAnimalListForAdoption();
        model.addAttribute("animals", animalList);  // animals라는 이름으로 동물 목록 전달
        return "views/user/adoption/list";  // 입양 리스트 화면
    }

    @GetMapping("/write")
    public String write() {
        return "views/user/adoption/write";
    }
    @GetMapping("/view")
    public String view() {
        return "views/user/adoption/view";
    }
}
