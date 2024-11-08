package sarik.dev.foodwave.dto.auth.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class AuthUserUpdateDTO {

    @NotBlank
    @Size(max = 50)
    private String username;

    @Email
    @NotBlank
    private String email;

    private Set<UUID> roleIds; // Yangilanish uchun yangi role ID lar
}
