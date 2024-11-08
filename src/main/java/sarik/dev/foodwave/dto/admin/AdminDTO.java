package sarik.dev.foodwave.dto.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.entity.admin.Admin;
import sarik.dev.foodwave.enums.admin.AdminRole;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class AdminDTO {

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Entitydan DTO ga aylantiruvchi fromEntity metodi
    public static AdminDTO fromEntity(Admin admin) {
        return AdminDTO.builder()
                .id(admin.getId())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .email(admin.getEmail())
                .role(admin.getRole())
                .isActive(admin.isActive())
                .createdAt(admin.getCreatedAt())
                .updatedAt(admin.getUpdatedAt())
                .build();
    }
}
