package sarik.dev.foodwave.dto.user.profile;

import lombok.Data;
import java.util.UUID;
import java.time.LocalDate;

@Data
public class UserProfileUpdateDTO {

    private UUID id;

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
    private String occupation;
    private String bio;
    private String alternatePhone;
}
