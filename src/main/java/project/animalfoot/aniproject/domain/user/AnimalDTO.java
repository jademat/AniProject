package project.animalfoot.aniproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AnimalDTO {
    private int animalNo;
    private String name;
    private String species;
    private String entryDate;

}
