package sarik.dev.foodwave.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import sarik.dev.foodwave.enums.user.Gender;
import sarik.dev.foodwave.enums.user.UserRole;

@Data
public class UserCreateDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number format")
    private String phoneNumber;

    private Gender gender;
    private UserRole userRole;
    private String profilePictureUrl;
    private boolean isActive = true;
}
