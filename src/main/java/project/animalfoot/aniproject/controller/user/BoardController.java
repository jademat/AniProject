package project.animalfoot.aniproject.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.animalfoot.aniproject.domain.user.BoardDTO;
import project.animalfoot.aniproject.service.user.BoardService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;


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

    @GetMapping("/review/list")
    public String listBoard(Model model, HttpSession session) {
        List<BoardDTO> boardList = boardService.getAllBoards();
        model.addAttribute("boardList", boardList);

        // 로그인 여부 체크 후 전달
        String userId = (String) session.getAttribute("loginUser");
        System.out.println("loginUser: " + userId);  // 로그에 출력하여 확인
        model.addAttribute("loginUser", userId);

        return "views/user/board/review/list"; // Thymeleaf 템플릿 파일명
    }

    @GetMapping("review/view/{bdNo}")
    public String viewBoard(@PathVariable("bdNo") int bdNo, Model model, HttpSession session) {
        // 조회수 증가
        boardService.incrementViewCount(bdNo);

        // 게시글 데이터 가져오기
        BoardDTO board = boardService.getBoardById(bdNo);

        // ✅ 콘솔에서 값 확인하기
        System.out.println("게시글 제목: " + board.getBdTitle());
        System.out.println("게시글 작성자 아이디: " + board.getUserId());

        if (board == null) {
            return "redirect:/board/review/list";
        }

        model.addAttribute("board", board);
        model.addAttribute("userId", session.getAttribute("userId"));

        return "views/user/board/review/view";
    }
}
