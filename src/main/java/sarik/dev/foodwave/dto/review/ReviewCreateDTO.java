package sarik.dev.foodwave.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class ReviewCreateDTO {

    private UUID userId;

    private UUID dishId;

    @Min(1)
    @Max(5)
    private int rating;

    @NotBlank
    private String comment;
}
