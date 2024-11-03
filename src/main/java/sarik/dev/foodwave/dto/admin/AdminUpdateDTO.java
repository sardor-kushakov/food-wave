package sarik.dev.foodwave.dto.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import sarik.dev.foodwave.enums.admin.AdminRole;

import java.util.UUID;

@Data
public class AdminUpdateDTO {

    private UUID id;

    @NotBlank
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @Email
    @NotBlank
    private String email;

    private AdminRole role;
    private boolean isActive;
}
