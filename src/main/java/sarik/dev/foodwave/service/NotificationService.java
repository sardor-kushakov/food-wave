package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.notification.NotificationCreateDTO;
import sarik.dev.foodwave.dto.notification.NotificationDTO;

import java.util.List;
import java.util.UUID;

public interface NotificationService {

    NotificationDTO createNotification(NotificationCreateDTO notificationCreateDTO);

    NotificationDTO getNotificationById(UUID notificationId);

    List<NotificationDTO> getUserNotifications(UUID userId);

    List<NotificationDTO> getUnreadNotifications(UUID userId);

    void markNotificationAsRead(UUID notificationId);

    List<NotificationDTO> getAllNotifications();
}
