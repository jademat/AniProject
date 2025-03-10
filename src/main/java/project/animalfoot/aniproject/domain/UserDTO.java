package project.animalfoot.aniproject.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data   // setter, getter, toString 자동 생성
@Builder    // 롬복에 있는 빌더
public class UserDTO {        // DB전송 객체

    private int uno;
    private String userid;
    private String userpwd;
    private String name;
    private String phone;
    private String addr;
    private String detailaddr;
    private String email;
    private int dopt_apply;
    private LocalDateTime regdate;
    private String kakaoId;

    // 생성자 추가 (모든 필드를 인자로 받음)
    public UserDTO(int uno, String userid, String userpwd, String name, String phone, String addr, String detailaddr, String email, int dopt_apply, LocalDateTime regdate, String kakaoId) {
        this.uno = uno;
        this.userid = userid;
        this.userpwd = userpwd;
        this.name = name;
        this.phone = phone;
        this.addr = addr;
        this.detailaddr = detailaddr;
        this.email = email;
        this.dopt_apply = dopt_apply;
        this.regdate = regdate;
        this.kakaoId = kakaoId;
    }

    public UserDTO() {

    }
}
