package sarik.dev.foodwave.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryCreateDTO {

    @NotBlank
    @Size(max = 100)
    private String name;

    private String description;
}
