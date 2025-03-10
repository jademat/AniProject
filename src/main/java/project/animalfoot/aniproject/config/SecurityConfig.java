package project.animalfoot.aniproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 설정 (카카오 API는 제외)
                .csrf().disable()

                // CORS 설정 (카카오 API 허용)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("https://dapi.kakao.com", "http://localhost:8080"));
                    config.setAllowedMethods(List.of("GET", "POST"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(List.of("*"));
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", config);
                    return config;
                }))

                // iframe 허용 (카카오 팝업 허용)
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())
                )

                // 로그인 페이지 설정 (로그인 페이지를 보여주도록 수정)
                .formLogin(form -> form
                        .loginPage("/user/login")  // 로그인 페이지 URL 지정
                        .permitAll()  // 모든 사용자에게 로그인 페이지 허용
                )

                // 모든 요청을 허용 (익명 사용자 접근 가능)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // 모든 요청을 허용
                )

                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }
}