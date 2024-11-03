package sarik.dev.foodwave.dto.user;

import lombok.Data;
import sarik.dev.foodwave.dto.address.AddressDTO;
import sarik.dev.foodwave.dto.login.LoginHistoryDTO;
import sarik.dev.foodwave.dto.notification.NotificationDTO;
import sarik.dev.foodwave.dto.order.OrderDTO;
import sarik.dev.foodwave.dto.review.ReviewDTO;
import sarik.dev.foodwave.enums.user.Gender;
import sarik.dev.foodwave.enums.user.UserRole;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class UserDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password; // You might want to exclude this field in some cases
    private String phoneNumber;
    private Gender gender;
    private UserRole userRole;
    private String profilePictureUrl;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
    private Set<AddressDTO> addresses;
    private Set<OrderDTO> orders;
    private Set<ReviewDTO> reviews;
    private Set<NotificationDTO> notifications;
    private Set<LoginHistoryDTO> loginHistories;
}
