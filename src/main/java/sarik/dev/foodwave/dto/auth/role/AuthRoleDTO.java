package sarik.dev.foodwave.dto.auth.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sarik.dev.foodwave.entity.auth.AuthPermission;
import sarik.dev.foodwave.entity.auth.AuthRole;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRoleDTO {

    private UUID id;
    private String name;
    private Set<String> permissions; // Permission nomlarini saqlaydi

    public static AuthRoleDTO fromEntity(AuthRole authRole) {
        return AuthRoleDTO.builder()
                .id(authRole.getId())
                .name(authRole.getName())
                .permissions(authRole.getPermissions().stream()
                        .map(AuthPermission::getName)
                        .collect(Collectors.toSet()))
                .build();
    }
}
