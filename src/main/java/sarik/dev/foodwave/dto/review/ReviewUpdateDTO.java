package sarik.dev.foodwave.dto.review;

import lombok.Data;

import java.util.UUID;

@Data
public class ReviewUpdateDTO {

    private UUID id;

    private int rating;
    private String comment;
}
