package sarik.dev.foodwave.dto.auth.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sarik.dev.foodwave.entity.auth.AuthRole;
import sarik.dev.foodwave.entity.auth.AuthUser;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserDTO {

    private UUID id;
    private String username;
    private String email;
    private Set<String> roles; // Role nomlarini saqlaydi

    public static AuthUserDTO fromEntity(AuthUser authUser) {
        return AuthUserDTO.builder()
                .id(authUser.getId())
                .username(authUser.getUsername())
                .email(authUser.getEmail())
                .roles(authUser.getRoles().stream()
                        .map(AuthRole::getName)
                        .collect(Collectors.toSet()))
                .build();
    }
}
