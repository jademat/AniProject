package project.animalfoot.aniproject.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import project.animalfoot.aniproject.domain.user.UserDTO;
import project.animalfoot.aniproject.service.user.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KakaoLoginController {

    private final UserService userService;
    private static final String CLIENT_ID = "f41d66937a155dd0f5cbf3d4d0b765fa";  // 카카오 API client_id
    private static final String REDIRECT_URI = "http://localhost:8080/login/kakao";  // 리다이렉트 URI

    // 카카오 로그인 callback 처리
    @GetMapping("/login/kakao")
    public String kakaoLogin(@RequestParam String code, HttpSession session) {
        // 카카오 API에서 사용자 정보 받아오기 위한 API 호출
        String accessToken = getKakaoAccessToken(code);
        UserDTO kakaoUser = getKakaoUserInfo(accessToken);

        // 로그인 처리 및 세션 저장
        session.setAttribute("loginUser", kakaoUser);
        session.setMaxInactiveInterval(600);   // 세션 유지 : 10분

        return "redirect:/";  // 로그인 후 메인 페이지로 리다이렉트
    }

    // 카카오 OAuth2 인증 코드로 액세스 토큰을 요청
    private String getKakaoAccessToken(String code) {
        String requestUrl = "https://kauth.kakao.com/oauth/token";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(requestUrl)
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", CLIENT_ID)
                .queryParam("redirect_uri", REDIRECT_URI)
                .queryParam("code", code);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(uriBuilder.toUriString(), null, String.class);

        // 카카오 API 응답 로그 출력
        log.info("카카오 API 응답: {}", response.getBody());

        String accessToken = response.getBody(); // 여기서는 그냥 String으로 처리
        return accessToken; // 실제로는 JSON 파싱 후 access_token 추출
    }

    private UserDTO getKakaoUserInfo(String accessToken) {
        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(userInfoUrl + "?access_token=" + accessToken, String.class);

        // 카카오 사용자 정보 응답 로그 출력
        log.info("카카오 사용자 정보 응답: {}", response);

        UserDTO userDTO = new UserDTO();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);

            // 카카오 사용자 정보에서 필요한 값 추출
            String kakaoId = jsonNode.get("id").asText();
            String name = jsonNode.get("properties").get("nickname").asText();
            String email = jsonNode.get("kakao_account").get("email").asText();

            // UserDTO에 값 설정
            userDTO.setKakaoId(kakaoId);
            userDTO.setName(name);
            userDTO.setEmail(email);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userDTO;  // UserDTO 객체 반환
    }

    // 로그인 완료 페이지: 카카오 로그인 후 처리 결과 페이지
    @GetMapping("/login/kakao/complete")
    public String completeKakaoLogin(HttpSession session) {
        // 세션에서 로그인한 사용자 정보 가져오기
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        // 세션에 사용자 정보가 없으면 로그인되지 않은 것으로 처리
        if (loginUser == null) {
            log.info("로그인 실패: 사용자 정보 없음.");
            return "redirect:/";  // 로그인되지 않은 경우 메인 페이지로 리다이렉트
        }

        // 로그인된 사용자 정보 로그
        log.info("로그인 완료: {}", loginUser);

        // 로그인 완료 페이지로 이동 (이 페이지는 템플릿 이름을 반환)
        return "index";  // "index"는 해당 HTML 페이지의 이름 (예: index.html)
    }

}
