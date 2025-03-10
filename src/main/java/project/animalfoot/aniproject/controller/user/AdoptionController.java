
package project.animalfoot.aniproject.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.animalfoot.aniproject.domain.user.AnimalDTO;
import project.animalfoot.aniproject.domain.user.AnimalPicDTO;
import project.animalfoot.aniproject.service.user.AdoptionService;

import java.util.List;

@Controller
@RequestMapping("/adoption")
@RequiredArgsConstructor
@Slf4j
public class AdoptionController {

    private final AdoptionService adoptionService;


    // 리스트 페이지: 모든 입양 동물 리스트 조회
    @GetMapping("/list")
    public String list(Model model) {
        List<AnimalDTO> adoptionList = adoptionService.getAllAdoptions();
        model.addAttribute("adoptions", adoptionList);
        return "views/user/adoption/list";  // Thymeleaf 뷰
    }

    // 상세 보기 페이지: 선택된 입양 동물 상세 정보 조회
    @GetMapping("/view")
    public String view(int animalNo, Model model) {
        AnimalDTO adoption = adoptionService.getAdoptionByAnimalNo(animalNo);
        List<AnimalPicDTO> pics = adoptionService.getPicsByAnimalNo(animalNo);
        model.addAttribute("adoption", adoption);
        model.addAttribute("pics", pics);
        return "views/user/adoption/view";  // 상세 정보 뷰
    }
    // 입양 신청 페이지로 이동
    @GetMapping("/write/{animalNo}")
    public String apply(@PathVariable("animalNo") int animalNo, Model model) {
        AnimalDTO adoption = adoptionService.getAdoptionByAnimalNo(animalNo);
        model.addAttribute("adoption", adoption);
        return "views/user/adoption/write";  // 입양 신청 페이지 뷰
    }



}