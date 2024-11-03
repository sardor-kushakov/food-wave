package sarik.dev.foodwave.dto.review;

import lombok.Data;
import sarik.dev.foodwave.dto.user.UserDTO;
import sarik.dev.foodwave.dto.dish.DishDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReviewDTO {

    private UUID id;
    private UserDTO user;
    private DishDTO dish;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
