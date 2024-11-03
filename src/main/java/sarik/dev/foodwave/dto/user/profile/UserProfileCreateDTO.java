package sarik.dev.foodwave.dto.user.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserProfileCreateDTO {

    @NotBlank
    private UUID userId;

    @NotBlank
    @Size(max = 100)
    private String addressLine1;

    @Size(max = 100)
    private String addressLine2;

    @NotBlank
    @Size(max = 50)
    private String city;

    @NotBlank
    @Size(max = 50)
    private String state;

    @NotBlank
    @Size(max = 20)
    private String zipCode;

    @NotBlank
    @Size(max = 50)
    private String country;

    @Past
    private LocalDate dateOfBirth;

    @Size(max = 15)
    private String nationalId;

    private String profilePictureUrl;

    private String occupation;
    private String bio;
    private String alternatePhone;
}
