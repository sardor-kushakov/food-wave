package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.user.LoginHistory;
import sarik.dev.foodwave.entity.user.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, UUID> {

    List<LoginHistory> findByUser(User user);
}
