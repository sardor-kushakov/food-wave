package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.notification.NotificationCreateDTO;
import sarik.dev.foodwave.dto.notification.NotificationDTO;
import sarik.dev.foodwave.entity.user.Notification;
import sarik.dev.foodwave.entity.user.User;
import sarik.dev.foodwave.repository.NotificationRepository;
import sarik.dev.foodwave.repository.UserRepository;
import sarik.dev.foodwave.service.NotificationService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public NotificationDTO createNotification(NotificationCreateDTO notificationCreateDTO) {
        User user = userRepository.findById(notificationCreateDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Notification notification = Notification.builder()
                .user(user)
                .type(notificationCreateDTO.getType()) // NotificationType ni String ga aylantiring
                .title(notificationCreateDTO.getTitle())
                .message(notificationCreateDTO.getMessage())
                .build();

        notificationRepository.save(notification);
        return NotificationDTO.fromEntity(notification);
    }

    @Override
    public NotificationDTO getNotificationById(UUID notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));
        return NotificationDTO.fromEntity(notification);
    }

    @Override
    public List<NotificationDTO> getUserNotifications(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return notificationRepository.findByUserAndIsRead(user, false).stream()
                .map(NotificationDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getUnreadNotifications(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return notificationRepository.findByUserAndIsRead(user, false).stream()
                .map(NotificationDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void markNotificationAsRead(UUID notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));

        notification.markAsRead();
        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationDTO> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(NotificationDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
