package project.animalfoot.aniproject.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import project.animalfoot.aniproject.domain.admin.user.User;
import project.animalfoot.aniproject.service.admin.AdUserService;


import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class AdUserController {

    private final AdUserService adUserService;

   @GetMapping("/userlist")
    public String list(Model m, @RequestParam(defaultValue = "1") int cpg, HttpServletResponse response) {
        // 클라이언트 캐시 제어
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader("Expires", 0);

        m.addAttribute("usdto",adUserService.readUser(cpg));

        return "views/admin/userlist";
    }

    @GetMapping("/find")
    public String find(Model m,String findtype, String findkey,
                       @RequestParam(defaultValue = "1") int cpg) {


        m.addAttribute("usdto", adUserService.findUser(cpg,findtype,findkey));

        return "views/admin/userlist";
    }

    @GetMapping("/view/{uno}")
    public ResponseEntity<?> view(@PathVariable int uno) {
        User user = adUserService.readOneUser(uno);
        if (user == null) {
            return ResponseEntity.badRequest().body("유저 정보를 찾을 수 없습니다.");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/delete/{uno}")
    public String delete(@PathVariable int uno) {
       boolean isdeleted = adUserService.deleteUser(uno);

       if(isdeleted) {
           return "redirect:/user/userlist";
       }else{
           return "error";
       }
    }


}
