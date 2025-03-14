package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoPayReadyDTO {

    private Long orderId;
    private String userId;
    private Integer amount;
}
