package sarik.dev.foodwave.dto.auth.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRoleUpdateDTO {
    
    @NotBlank
    private String name;
}
