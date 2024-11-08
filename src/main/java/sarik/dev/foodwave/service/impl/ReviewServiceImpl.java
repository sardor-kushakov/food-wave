package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.review.ReviewCreateDTO;
import sarik.dev.foodwave.dto.review.ReviewDTO;
import sarik.dev.foodwave.dto.review.ReviewUpdateDTO;
import sarik.dev.foodwave.entity.user.Review;
import sarik.dev.foodwave.entity.user.User;
import sarik.dev.foodwave.entity.user.Dish;
import sarik.dev.foodwave.repository.ReviewRepository;
import sarik.dev.foodwave.repository.UserRepository;
import sarik.dev.foodwave.repository.DishRepository;
import sarik.dev.foodwave.service.ReviewService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;

    @Override
    @Transactional
    public ReviewDTO createReview(ReviewCreateDTO reviewCreateDTO) {
        User user = userRepository.findById(reviewCreateDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Dish dish = dishRepository.findById(reviewCreateDTO.getDishId())
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        Review review = Review.builder()
                .user(user)
                .dish(dish)
                .rating(reviewCreateDTO.getRating())
                .comment(reviewCreateDTO.getComment())
                .build();

        reviewRepository.save(review);
        return ReviewDTO.fromEntity(review);
    }

    @Override
    public ReviewDTO getReviewById(UUID reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        return ReviewDTO.fromEntity(review);
    }

    @Override
    public List<ReviewDTO> getReviewsByUserId(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return reviewRepository.findByUser(user).stream()
                .map(ReviewDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByDishId(UUID dishId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        return reviewRepository.findByDish(dish).stream()
                .map(ReviewDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReviewDTO updateReview(UUID reviewId, ReviewUpdateDTO reviewUpdateDTO) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        review.updateReview(reviewUpdateDTO.getRating(), reviewUpdateDTO.getComment());
        reviewRepository.save(review);

        return ReviewDTO.fromEntity(review);
    }

    @Override
    @Transactional
    public void deleteReview(UUID reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
