package project.animalfoot.aniproject.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.animalfoot.aniproject.domain.ReadyResponse;
import project.animalfoot.aniproject.domain.user.KakaoPayReadyDTO;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class KakaoPayService {

    @Value("${kakao.pay.key}")
    private String kakaoPayKey; // 카카오페이 API 키

    @Value("${kakao.pay.url}")
    private String kakaoPayUrl; // 카카오페이 API URL

    public ReadyResponse payReady(KakaoPayReadyDTO kakaoPayDTO) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("cid", "TC0ONETIME");                                    // 가맹점 코드(테스트용)
        parameters.put("partner_order_id", String.valueOf(kakaoPayDTO.getOrderId()));                       // 주문번호
        parameters.put("partner_user_id", kakaoPayDTO.getUserId());                          // 회원 아이디
        parameters.put("item_name", "123456");                                      // 상품명
        parameters.put("quantity", "1");                                        // 상품 수량
        parameters.put("total_amount", String.valueOf(kakaoPayDTO.getAmount()));             // 상품 총액
        parameters.put("tax_free_amount", "0");                                 // 상품 비과세 금액
        parameters.put("approval_url", "http://localhost:8080/order/pay/completed"); // 결제 성공 시 URL
        parameters.put("cancel_url", "http://localhost:8080/order/pay/cancel");      // 결제 취소 시 URL
        parameters.put("fail_url", "http://localhost:8080/order/pay/fail");          // 결제 실패 시 URL

        // HttpEntity : HTTP 요청 또는 응답에 해당하는 Http Header와 Http Body를 포함하는 클래스
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // RestTemplate
        // : Rest 방식 API를 호출할 수 있는 Spring 내장 클래스
        //   REST API 호출 이후 응답을 받을 때까지 기다리는 동기 방식 (json, xml 응답)
        RestTemplate template = new RestTemplate();
        String url = "https://open-api.kakaopay.com/online/v1/payment/ready";
        // RestTemplate의 postForEntity : POST 요청을 보내고 ResponseEntity로 결과를 반환받는 메소드
        ResponseEntity<ReadyResponse> responseEntity = template.postForEntity(url, requestEntity, ReadyResponse.class);
        log.info("결제준비 응답객체: " + responseEntity.getBody());

        return responseEntity.getBody();
    }

    // 결제 준비 API 호출
    public String preparePayment(Long orderId, String userId, Integer amount, KakaoPayReadyDTO kakaoPayReadyDTO) {
        String requestUrl = kakaoPayUrl + "/v1/payment/ready"; // 카카오페이 결제 준비 URL

        // 카카오페이 결제 준비 요청 파라미터
        Map<String, Object> params = new HashMap<>();
        params.put("cid", "TC0ONETIME");  // 테스트용 CID
        params.put("partner_order_id", orderId.toString());
        params.put("partner_user_id", userId);
        params.put("item_name", "유기동물 기부");
        params.put("quantity", 1);
        params.put("total_amount", amount);
        params.put("tax_free_amount", 0);
        params.put("approval_url", "http://localhost:8080/kakaoPay/success");
        params.put("cancel_url", "http://localhost:8080/kakaoPay/cancel");
        params.put("fail_url", "http://localhost:8080/kakaoPay/fail");

        // 헤더 설정 (Authorization: Bearer access_token)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + kakaoPayKey);

        // 요청 본문 및 헤더 설정
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(params, headers);

        // 카카오페이 API 호출
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.POST, request, String.class);

        // 카카오페이 응답 처리
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return "Payment preparation failed: " + response.getBody();
        }
    }

    // 결제 승인 API 호출
    public String approvePayment(String pgToken) {
        String requestUrl = kakaoPayUrl + "/v1/payment/approve";  // 결제 승인 URL

        // 결제 승인 요청 파라미터
        Map<String, Object> params = new HashMap<>();
        params.put("cid", "TC0ONETIME");  // 테스트용 CID
        params.put("tid", "T12345678901234567890");  // 결제 고유 TID
        params.put("partner_order_id", "1234567890");  // 주문 ID
        params.put("partner_user_id", "user123");  // 사용자 ID
        params.put("pg_token", pgToken);  // 카카오페이로부터 받은 토큰

        // 헤더 설정 (Authorization: Bearer access_token)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + kakaoPayKey);

        // 요청 본문 및 헤더 설정
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(params, headers);

        // 카카오페이 API 호출
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.POST, request, String.class);

        // 결제 승인 처리 결과 반환
        if (response.getStatusCode() == HttpStatus.OK) {
            return "Payment approved successfully";
        } else {
            return "Payment approval failed: " + response.getBody();
        }
    }

    // 카카오페이 측에 요청 시 헤더부에 필요한 값
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "SECRET_KEY DEV72675D7CF28396D3C3225A5CE34C6A117C212");
        headers.set("Content-type", "application/json");

        return headers;
    }
}
