package sarik.dev.foodwave.dto.user.profile;

import lombok.Data;
import sarik.dev.foodwave.dto.user.UserDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
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
}
