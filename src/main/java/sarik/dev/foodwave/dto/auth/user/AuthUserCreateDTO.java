package sarik.dev.foodwave.dto.auth.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class AuthUserCreateDTO {

    @NotBlank
    @Size(max = 50)
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    private Set<UUID> roleIds; // Role ID larini saqlash uchun
}
