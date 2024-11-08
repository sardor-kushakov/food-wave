package sarik.dev.foodwave.dto.user;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.dto.address.AddressDTO;
import sarik.dev.foodwave.dto.login.LoginHistoryDTO;
import sarik.dev.foodwave.dto.notification.NotificationDTO;
import sarik.dev.foodwave.dto.order.OrderDTO;
import sarik.dev.foodwave.dto.review.ReviewDTO;
import sarik.dev.foodwave.entity.user.User;
import sarik.dev.foodwave.enums.user.Gender;
import sarik.dev.foodwave.enums.user.UserRole;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
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

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user the User entity
     * @return a UserDTO with data from the entity
     */
    public static UserDTO fromEntity(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword()) // Consider removing this field if sensitive
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .userRole(user.getUserRole())
                .profilePictureUrl(user.getProfilePictureUrl())
                .isActive(user.isActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .lastLogin(user.getLastLogin())
                .addresses(user.getAddresses().stream()
                        .map(AddressDTO::fromEntity) // Assuming AddressDTO has a fromEntity method
                        .collect(Collectors.toSet()))
                .orders(user.getOrders().stream()
                        .map(OrderDTO::fromEntity) // Assuming OrderDTO has a fromEntity method
                        .collect(Collectors.toSet()))
                .reviews(user.getReviews().stream()
                        .map(ReviewDTO::fromEntity) // Assuming ReviewDTO has a fromEntity method
                        .collect(Collectors.toSet()))
                .notifications(user.getNotifications().stream()
                        .map(NotificationDTO::fromEntity) // Assuming NotificationDTO has a fromEntity method
                        .collect(Collectors.toSet()))
                .loginHistories(user.getLoginHistories().stream()
                        .map(LoginHistoryDTO::fromEntity) // Assuming LoginHistoryDTO has a fromEntity method
                        .collect(Collectors.toSet()))
                .build();
    }
}
