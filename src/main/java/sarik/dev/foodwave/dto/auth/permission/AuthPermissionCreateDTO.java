package sarik.dev.foodwave.dto.auth.permission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthPermissionCreateDTO {
    
    @NotBlank
    @Size(max = 50)
    private String name;
}
