package sarik.dev.foodwave.entity.auth;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRole {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name; // Masalan: ADMIN, USER

    /**
     * -- GETTER --
     * Gets all permissions associated with this role.
     */
    @Getter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<AuthPermission> permissions = new HashSet<>();

    /**
     * -- GETTER --
     * Gets all users associated with this role.
     */
    @Getter
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<AuthUser> users = new HashSet<>(); // Qo'shilgan

    // Methods

    /**
     * Adds a permission to this role.
     */
    public void addPermission(AuthPermission permission) {
        permissions.add(permission);
        permission.getRoles().add(this);
    }

    /**
     * Removes a permission from this role.
     */
    public void removePermission(AuthPermission permission) {
        permissions.remove(permission);
        permission.getRoles().remove(this);
    }

}
