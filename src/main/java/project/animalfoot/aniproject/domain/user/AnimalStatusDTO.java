package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalStatusDTO {

    private String district;
    private int totalDogs;
    private int adoptedDogs;
    private int euthanizedDogs;
}
