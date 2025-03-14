
package project.animalfoot.aniproject.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.animalfoot.aniproject.domain.user.AdoptDTO;
import project.animalfoot.aniproject.domain.user.AnimalDTO;
import project.animalfoot.aniproject.domain.user.AnimalPicDTO;
import project.animalfoot.aniproject.domain.user.UserDTO;
import project.animalfoot.aniproject.service.user.AdoptionService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/adoption")
@RequiredArgsConstructor
@Slf4j
public class AdoptionController {

    private final AdoptionService adoptionService;


    // 리스트 페이지: 모든 입양 동물 리스트 조회
    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "1")int cpg, HttpServletResponse response) {

        // 클라이언트 캐시 제어
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        model.addAttribute("bdsdto",adoptionService.readBoard(cpg));

        return "views/user/adoption/list";  // Thymeleaf 뷰
    }

    // 상세 보기 페이지: 선택된 입양 동물 상세 정보 조회
    @GetMapping("/view")
    public String view(int animalNo, Model model, HttpSession session) {
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        String userId = (loginUser != null) ? loginUser.getUserid() : null;

        AnimalDTO adoption = adoptionService.getAdoptionByAnimalNo(animalNo);
        List<AnimalPicDTO> pics = adoptionService.getPicsByAnimalNo(animalNo);
        model.addAttribute("adoption", adoption);
        model.addAttribute("pics", pics);
        model.addAttribute("userId", userId);
        return "views/user/adoption/view";  // 상세 정보 뷰
    }
    // 입양 신청 페이지로 이동
    @GetMapping("/write/{animalNo}")
    public String apply(@PathVariable("animalNo") int animalNo, Model model, HttpSession session) {
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        if (loginUser != null) {
            model.addAttribute("uno", loginUser.getUno());
            model.addAttribute("applicant_name", loginUser.getName());
            model.addAttribute("applicant_phone", loginUser.getPhone());
            model.addAttribute("applicant_email", loginUser.getEmail());
        }

        AnimalDTO adoption = adoptionService.getAdoptionByAnimalNo(animalNo);
        model.addAttribute("adoption", adoption);
        model.addAttribute("animal_no", animalNo);
        return "views/user/adoption/write";  // 입양 신청 페이지 뷰
    }

    @PostMapping("/submit")
    public String writeok(AdoptDTO adoptDTO, HttpSession session, RedirectAttributes redirectAttributes) {
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        if (loginUser == null) {
            redirectAttributes.addFlashAttribute("message", "로그인이 필요합니다.");
            return "redirect:/user/user/login";  // 로그인 페이지로 리다이렉트
        }

        adoptDTO.setUno(loginUser.getUno()); // 로그인한 사용자의 uno 저장
        adoptionService.submitAdoption(adoptDTO); // DB 저장

        redirectAttributes.addFlashAttribute("message","입양 신청이 완료되었습니다.");
        return "redirect:/adoption/list";
    }

  @GetMapping("/process")
    public String process() {
        return "views/user/adoption/process";
    }


}