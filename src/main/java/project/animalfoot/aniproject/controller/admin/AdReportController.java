package project.animalfoot.aniproject.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/report")
public class AdReportController {

    @GetMapping("/bdreport")
    public String bdReport() {

        return "views/adreport/boardnotice";
    }
    @GetMapping("/rpreport")
    public String rpReport() {

        return "views/adreport/replynotice";
    }
}