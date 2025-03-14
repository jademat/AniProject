package project.animalfoot.aniproject.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import project.animalfoot.aniproject.domain.admin.adboard.Adopt;
import project.animalfoot.aniproject.service.admin.AdAdoptionService;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/adopt")
@RequiredArgsConstructor
@Slf4j
public class AdAdoptionController {

    private final AdAdoptionService adAdoptionService;

    @GetMapping("/list")
    public String adoptlist(Model m, @RequestParam(defaultValue = "1")int cpg, HttpServletResponse response) {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        m.addAttribute("adosdto",adAdoptionService.readAdopt(cpg));

        return "views/adadopt/adopt";
    }
    @GetMapping("/view/{adono}")
    public ResponseEntity<?> view(@PathVariable int adono) {
        Adopt adopt = adAdoptionService.readOneAdopt(adono);
        if (adopt == null) {
            return ResponseEntity.badRequest().body("입양신청 정보를 찾을 수 없습니다.");
        }
        return ResponseEntity.ok(adopt);
    }

    @PostMapping("/updateAdoStat/{adono}")
    public ResponseEntity<?> updateAdoStat(@PathVariable int adono, @RequestBody Map<String, Integer> request) {
        int adoStat = request.get("ado_stat"); // "adoStat" 키로 상태 값을 받음
        boolean success = adAdoptionService.updateAdoStat(adono, adoStat); // 서비스 호출
        return ResponseEntity.ok(Map.of("success", success)); // 성공 여부 반환
    }

    @GetMapping("/anilist")
    public String anilist(Model m) {

        m.addAttribute("anidto",adAdoptionService.aniList());

        return "views/adadopt/anilist";
    }


    @GetMapping("/admission")
    public String admissionlist() {
        return "views/adadopt/admission";
    }

}
