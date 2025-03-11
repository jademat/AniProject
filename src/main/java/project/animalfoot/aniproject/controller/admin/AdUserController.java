package project.animalfoot.aniproject.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


        m.addAttribute("uslist", adUserService.findUser(cpg,findtype,findkey));
        m.addAttribute("cpg", cpg);
        m.addAttribute("stblk", ((cpg -1) /10)*10 +1);
        m.addAttribute("cntpg", adUserService.countFindUser(findtype,findkey));

        return "views/admin/userlist";
    }


}
