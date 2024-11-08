package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.review.ReviewCreateDTO;
import sarik.dev.foodwave.dto.review.ReviewDTO;
import sarik.dev.foodwave.dto.review.ReviewUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    ReviewDTO createReview(ReviewCreateDTO reviewCreateDTO);

    ReviewDTO getReviewById(UUID reviewId);

    List<ReviewDTO> getReviewsByUserId(UUID userId);

    List<ReviewDTO> getReviewsByDishId(UUID dishId);

    ReviewDTO updateReview(UUID reviewId, ReviewUpdateDTO reviewUpdateDTO);

    void deleteReview(UUID reviewId);
}
