package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.user.Review;
import sarik.dev.foodwave.entity.user.User;
import sarik.dev.foodwave.entity.user.Dish;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

    List<Review> findByUser(User user);

    List<Review> findByDish(Dish dish);
}
