package sarik.dev.foodwave.dto.user.profile;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.dto.user.UserDTO;
import sarik.dev.foodwave.entity.user.UserProfile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserProfileDTO {

    private UUID id;
    private UserDTO user;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private LocalDate dateOfBirth;
    private String nationalId;
    private String profilePictureUrl;
    private boolean isVerified;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String occupation;
    private String bio;
    private String alternatePhone;

    /**
     * Converts a UserProfile entity to a UserProfileDTO.
     *
     * @param userProfile the UserProfile entity
     * @return a UserProfileDTO with data from the entity
     */
    public static UserProfileDTO fromEntity(UserProfile userProfile) {
        return UserProfileDTO.builder()
                .id(userProfile.getId())
                .user(UserDTO.fromEntity(userProfile.getUser())) // Assuming UserDTO has a fromEntity method
                .addressLine1(userProfile.getAddressLine1())
                .addressLine2(userProfile.getAddressLine2())
                .city(userProfile.getCity())
                .state(userProfile.getState())
                .zipCode(userProfile.getZipCode())
                .country(userProfile.getCountry())
                .dateOfBirth(userProfile.getDateOfBirth())
                .nationalId(userProfile.getNationalId())
                .profilePictureUrl(userProfile.getProfilePictureUrl())
                .isVerified(userProfile.isVerified())
                .createdAt(userProfile.getCreatedAt())
                .updatedAt(userProfile.getUpdatedAt())
                .occupation(userProfile.getOccupation())
                .bio(userProfile.getBio())
                .alternatePhone(userProfile.getAlternatePhone())
                .build();
    }
}
