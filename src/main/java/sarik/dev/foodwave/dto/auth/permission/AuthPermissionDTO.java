package sarik.dev.foodwave.dto.auth.permission;

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
public class AuthPermissionDTO {

    private UUID id;
    private String name;

    // Optional: role names to show associated roles
    private Set<String> roles;

    public static AuthPermissionDTO fromEntity(AuthPermission authPermission) {
        return AuthPermissionDTO.builder()
                .id(authPermission.getId())
                .name(authPermission.getName())
                .roles(authPermission.getRoles().stream()
                        .map(AuthRole::getName)
                        .collect(Collectors.toSet()))
                .build();
    }
}
