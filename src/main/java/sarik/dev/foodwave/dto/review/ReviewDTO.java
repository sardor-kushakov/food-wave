package sarik.dev.foodwave.dto.review;

import lombok.Data;
import lombok.Builder;
import sarik.dev.foodwave.dto.user.UserDTO;
import sarik.dev.foodwave.dto.dish.DishDTO;
import sarik.dev.foodwave.entity.user.Review;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ReviewDTO {

    private UUID id;
    private UserDTO user;
    private DishDTO dish;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Converts a Review entity to a ReviewDTO.
     *
     * @param review the Review entity
     * @return a ReviewDTO with data from the entity
     */
    public static ReviewDTO fromEntity(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .user(UserDTO.fromEntity(review.getUser())) // Assuming UserDTO has a fromEntity method
                .dish(DishDTO.fromEntity(review.getDish())) // Assuming DishDTO has a fromEntity method
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }
}
