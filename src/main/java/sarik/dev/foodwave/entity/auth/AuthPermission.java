package sarik.dev.foodwave.entity.auth;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String name; // Masalan: VIEW_USER, EDIT_USER

    @ManyToMany(mappedBy = "permissions")
    private Set<AuthRole> roles = new HashSet<>();

    // Methods

    /**
     * Adds a role to this permission.
     */
    public void addRole(AuthRole role) {
        roles.add(role);
        role.getPermissions().add(this);
    }

    /**
     * Removes a role from this permission.
     */
    public void removeRole(AuthRole role) {
        roles.remove(role);
        role.getPermissions().remove(this);
    }
}
