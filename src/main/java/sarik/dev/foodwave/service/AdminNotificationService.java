package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.admin.notification.AdminNotificationCreateDTO;
import sarik.dev.foodwave.dto.admin.notification.AdminNotificationDTO;

import java.util.List;
import java.util.UUID;

public interface AdminNotificationService {

    AdminNotificationDTO createNotification(AdminNotificationCreateDTO notificationCreateDTO);

    List<AdminNotificationDTO> getNotificationsByAdmin(UUID adminId);

    List<AdminNotificationDTO> getUnreadNotificationsByAdmin(UUID adminId);

    void markNotificationAsRead(UUID notificationId);

    void deleteNotification(UUID notificationId);
}
