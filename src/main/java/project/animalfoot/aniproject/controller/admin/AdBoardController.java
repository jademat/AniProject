package project.animalfoot.aniproject.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.animalfoot.aniproject.domain.admin.adboard.Board;
import project.animalfoot.aniproject.domain.admin.user.User;
import project.animalfoot.aniproject.service.admin.AdBoardService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/adboard")
@RequiredArgsConstructor
@Slf4j
public class AdBoardController {

    private final AdBoardService adBoardService;

    @GetMapping("/boardlist")
    public String board(Model m, @RequestParam(defaultValue = "1") int cpg, HttpServletResponse response) {

        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader("Expires", 0);

        m.addAttribute("bddto",adBoardService.readBoard(cpg));

        return "views/adboard/boardlist";
    }

    @GetMapping("/view/{bd_no}")
    public ResponseEntity<?> view(@PathVariable int bd_no) {
        Board board = adBoardService.readOneBoard(bd_no);
        if (board == null) {
            return ResponseEntity.badRequest().body("유저 정보를 찾을 수 없습니다.");
        }
        return ResponseEntity.ok(board);
    }

    @GetMapping("/find")
    public String find(Model m,String findtype, String findkey,
                       @RequestParam(defaultValue = "1") int cpg) {

        m.addAttribute("bddto", adBoardService.findBoard(cpg,findtype,findkey));

        System.out.println("findtype : " + findtype);
        return "views/adboard/boardlist";
    }

    @PostMapping("/delete/{bd_no}")
    public String delete(@PathVariable int bd_no) {
        boolean boardDeleted = adBoardService.deleteBoard(bd_no);

        if(boardDeleted) {
            return "redirect:/adboard/boardlist";
        }else{
            return "error";
        }
    }

    @GetMapping("/notice")
    public String notice(Model m) {

        return "views/adboard/notice";
    }
}
