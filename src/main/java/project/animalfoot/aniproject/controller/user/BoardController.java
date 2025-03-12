package project.animalfoot.aniproject.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.animalfoot.aniproject.domain.user.*;
import project.animalfoot.aniproject.service.user.BoardReportService;
import project.animalfoot.aniproject.service.user.BoardService;
import project.animalfoot.aniproject.service.user.GoogleRecaptchaService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final GoogleRecaptchaService googleRecaptchaService;
    private final BoardReportService boardReportService;


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
        // 로그인된 사용자의 정보 가져오기
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        // 로그인한 사용자만 글쓰기 버튼 보이기
        String userId = (loginUser != null) ? loginUser.getUserid() : null;

        List<BoardDTO> boardList = boardService.getAllBoards();
        model.addAttribute("boardList", boardList);
        model.addAttribute("userId", userId);  // userId를 model에 추가

        return "views/user/board/review/list"; // Thymeleaf 템플릿 파일명
    }

    @GetMapping("review/view/{bdNo}")
    public String viewBoard(@PathVariable("bdNo") int bdNo, Model model, HttpSession session) {

        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        Integer uno = (loginUser != null) ? loginUser.getUno() : null;
        // 조회수 증가
        boardService.incrementViewCount(bdNo);

        // 게시글 데이터 가져오기
        BoardDTO board = boardService.getBoardById(bdNo);

        if (board == null) {
            return "redirect:/board/review/list";
        }

        model.addAttribute("board", board);
        model.addAttribute("uno", uno);

        return "views/user/board/review/view";
    }

    @PostMapping("/review/delete/{bdNo}")
    public String deleteBoard(@PathVariable("bdNo") int bdNo) {
        boolean isDeleted = boardService.deleteBoard(bdNo);
        if (isDeleted) {
            return "redirect:/board/review/list";  // 삭제 후 목록 페이지로 리디렉션
        } else {
            return "error";  // 삭제 실패 시 에러 페이지
        }
    }
    // 게시글 수정 페이지
    @GetMapping("/review/edit/{bdNo}")
    public String editBoard(@PathVariable("bdNo") int bdNo, Model model) {
        // 수정할 게시글을 가져오기
        BoardDTO board = boardService.getBoardById(bdNo);
        if (board == null) {
            return "redirect:/board/review/list"; // 게시글이 없으면 목록 페이지로 리디렉션
        }
        model.addAttribute("board", board);  // 수정할 게시글 정보 전달
        return "views/user/board/review/edit";  // 수정 페이지로 이동
    }

    @PostMapping("/review/edit/{bdNo}")
    public String updateBoard(@PathVariable("bdNo") int bdNo,
                              @RequestParam("bdTitle") String bdTitle,
                              @RequestParam("bdContents") String bdContents) {
        // 수정할 제목과 내용만 담기
        BoardUpdateDTO boardUpdateDTO = new BoardUpdateDTO();
        boardUpdateDTO.setBdNo(bdNo);
        boardUpdateDTO.setBdTitle(bdTitle);
        boardUpdateDTO.setBdContents(bdContents);

        boolean isUpdated = boardService.updateBoard(boardUpdateDTO);
        if (isUpdated) {
            return "redirect:/board/review/view/" + bdNo;  // 수정 후 상세보기 페이지로 리디렉션
        } else {
            return "error";  // 수정 실패 시 에러 페이지
        }
    }

    @GetMapping("/review/report/{bdNo}")
    public String showReportForm(@PathVariable("bdNo") int bdNo, Model model) {
        model.addAttribute("bdNo", bdNo);
        return "views/user/board/review/report";  // 신고 폼 뷰 반환 (report.html)
    }

    // 신고를 처리하는 메서드
    @PostMapping("/review/report/{bdNo}")
    public String reportBoard(@PathVariable("bdNo") int bdNo,
                              @RequestParam("brCategory") String brCategory,
                              @RequestParam("brContent") String brContent,
                              HttpSession session) {

        // 세션에서 로그인된 사용자 정보 가져오기
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        // 로그인되지 않은 경우 로그인 페이지로 리디렉션
        if (loginUser == null) {
            return "redirect:/login";  // 로그인 페이지로 리디렉션
        }

        String brId = loginUser.getUserid();  // 로그인한 사용자 ID 가져오기

        // 신고 DTO 생성
        BoardReportDTO reportDTO = BoardReportDTO.builder()
                .bdNo(bdNo)
                .brId(brId)
                .brCategory(brCategory)
                .brContent(brContent)
                .build();

        // 서비스 호출하여 신고 처리
        boardReportService.reportBoard(reportDTO);

        return "redirect:/board/review/view/" + bdNo;  // 신고 후 게시글 상세 페이지로 리디렉션
    }


    @GetMapping("/review/write")
    public String adoptionWrite(Model model, HttpSession session) {
        // 로그인된 사용자의 정보 가져오기
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        if (loginUser != null) {
            model.addAttribute("uno", loginUser.getUno());
            model.addAttribute("sitekey", System.getenv().get("recaptcha.sitekey"));
        }
        return "views/user/board/review/write";
    }

    @PostMapping( "/review/write")
    public ResponseEntity<?> writeok(NewBoardDTO newBoardDTO,
                                     @RequestParam("g-recaptcha-response")   String gRecaptchaResponse ) {
        ResponseEntity<?> response = ResponseEntity.internalServerError().build();
        log.info("submit된 게시글 정보 : {}",newBoardDTO);
        log.info("submit된 recaptcha 응답코드 : {}",gRecaptchaResponse);

        try {
            if (!googleRecaptchaService.verifyRecaptcha(gRecaptchaResponse)) {
                throw new IllegalStateException("자동가입방지 코드 오류!!!");

            }

            if(boardService.newBoard(newBoardDTO)) {
                response =  ResponseEntity.ok().build();
            }
        }catch (IllegalStateException ex){
            response=ResponseEntity.badRequest().body(ex.getMessage());
        }

        return response;
    }




}
