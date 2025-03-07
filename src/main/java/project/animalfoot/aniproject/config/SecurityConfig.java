package project.animalfoot.aniproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/static/**", "/css/**", "/images/**", "/js/**").permitAll()  // static 파일은 인증 없이 접근 허용
                .and()
                .formLogin(form -> form
                        .loginPage("/user/user/login")  // 로그인 페이지 경로 설정
                        .defaultSuccessUrl("/", true)  // 로그인 성공 시 리디렉션 경로
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/user/user/logout")  // 로그아웃 URL 설정
                        .logoutSuccessUrl("/")  // 로그아웃 후 리디렉션 경로
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")  // 세션과 쿠키 삭제
                );

        return http.build();
    }
}
