package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.admin.notification.AdminNotificationCreateDTO;
import sarik.dev.foodwave.dto.admin.notification.AdminNotificationDTO;
import sarik.dev.foodwave.entity.admin.Admin;
import sarik.dev.foodwave.entity.admin.AdminNotification;
import sarik.dev.foodwave.repository.AdminNotificationRepository;
import sarik.dev.foodwave.repository.AdminRepository;
import sarik.dev.foodwave.service.AdminNotificationService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminNotificationServiceImpl implements AdminNotificationService {

    private final AdminNotificationRepository notificationRepository;
    private final AdminRepository adminRepository;

    @Override
    @Transactional
    public AdminNotificationDTO createNotification(AdminNotificationCreateDTO notificationCreateDTO) {
        Admin admin = adminRepository.findById(notificationCreateDTO.getAdminId())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));

        AdminNotification notification = AdminNotification.builder()
                .admin(admin)
                .message(notificationCreateDTO.getMessage())
                .isRead(false)
                .build();

        notificationRepository.save(notification);
        return AdminNotificationDTO.fromEntity(notification);
    }

    @Override
    public List<AdminNotificationDTO> getNotificationsByAdmin(UUID adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));

        return notificationRepository.findByAdmin(admin).stream()
                .map(AdminNotificationDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdminNotificationDTO> getUnreadNotificationsByAdmin(UUID adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));

        return notificationRepository.findByAdminAndIsRead(admin, false).stream()
                .map(AdminNotificationDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void markNotificationAsRead(UUID notificationId) {
        AdminNotification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));

        notification.markAsRead();
        notificationRepository.save(notification);
    }

    @Override
    @Transactional
    public void deleteNotification(UUID notificationId) {
        if (notificationRepository.existsById(notificationId)) {
            notificationRepository.deleteById(notificationId);
        } else {
            throw new IllegalArgumentException("Notification not found");
        }
    }
}
