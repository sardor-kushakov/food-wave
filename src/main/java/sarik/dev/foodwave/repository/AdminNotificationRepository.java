package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.admin.Admin;
import sarik.dev.foodwave.entity.admin.AdminNotification;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdminNotificationRepository extends JpaRepository<AdminNotification, UUID> {

    List<AdminNotification> findByAdmin(Admin admin);

    List<AdminNotification> findByAdminAndIsRead(Admin admin, boolean isRead);
}
