package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.user.Notification;
import sarik.dev.foodwave.entity.user.User;
import sarik.dev.foodwave.enums.user.NotificationType;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findByUserAndIsRead(User user, boolean isRead);

    List<Notification> findByUserAndType(User user, NotificationType type);

    List<Notification> findByIsRead(boolean isRead);
}
