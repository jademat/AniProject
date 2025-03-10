package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Data   // setter, getter, toStirng 자동 생성
@Builder    // 롬복에 있는 빌더
public class UserDTO {        // DB전송 객체

    private String userid;
    private String userpwd;
    private String name;
    private String email;
    private String kakaoId;

    // 생성자 추가 (모든 필드를 인자로 받음)
    public UserDTO(String userid, String userpwd, String name, String email, String kakaoId) {
        this.userid = userid;
        this.userpwd = userpwd;
        this.name = name;
        this.email = email;
        this.kakaoId = kakaoId;
    }

    public UserDTO() {

    }
}