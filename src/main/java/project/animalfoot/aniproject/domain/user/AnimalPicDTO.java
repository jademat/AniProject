package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AnimalPicDTO {
    private int animalNo;      // 동물 번호
    private String photoKnd;   // 사진 종류 (예: 얼굴, 전체 등)
    private int photoNo;       // 사진 번호
    private String photoUrl;   // 사진 URL
}
