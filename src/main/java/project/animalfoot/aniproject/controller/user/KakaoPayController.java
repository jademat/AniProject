package project.animalfoot.aniproject.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.animalfoot.aniproject.domain.ReadyResponse;
import project.animalfoot.aniproject.domain.user.KakaoPayReadyDTO;
import project.animalfoot.aniproject.service.user.KakaoPayService;

@Slf4j
@Controller
@RequestMapping("/kakaoPay")
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;

    // 결제 준비 메서드
    @PostMapping("/donate")
    public @ResponseBody ReadyResponse donate(@RequestBody KakaoPayReadyDTO kakaoPayReadyDTO) {
        String userId = kakaoPayReadyDTO.getUserId();
        int amount = kakaoPayReadyDTO.getAmount();
        Long orderId = kakaoPayReadyDTO.getOrderId();

        log.info("주문 상품 이름: " + userId);
        log.info("주문 금액: " + amount);
        log.info("주문 번호: " + orderId);

        // 카카오 결제 준비하기
        ReadyResponse readyResponse = kakaoPayService.payReady(kakaoPayReadyDTO);
        // 세션에 결제 고유번호(tid) 저장
        //SessionUtils.addAttribute("tid", readyResponse.getTid());
        log.info("결제 고유번호: " + readyResponse.getTid());

        return readyResponse;
    }


    @GetMapping("/success")
    public String success() {
        return "kakaoPaySuccess";  // 결제 성공 페이지로 리디렉션
    }

    @GetMapping("/cancel")
    public String cancel() {
        return "kakaoPayCancel";  // 결제 취소 페이지로 리디렉션
    }

    @GetMapping("/fail")
    public String fail() {
        return "kakaoPayFail";  // 결제 실패 페이지로 리디렉션
    }


}


