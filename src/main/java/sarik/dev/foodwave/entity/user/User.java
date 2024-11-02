package sarik.dev.foodwave.entity.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.*;
import sarik.dev.foodwave.enums.user.Gender;
import sarik.dev.foodwave.enums.user.UserRole;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email"), @UniqueConstraint(columnNames = "phoneNumber")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate // Efficiently updates only modified fields
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE id = ?") // Soft delete
@Filter(name = "deletedFilter", condition = "is_deleted = :isDeleted")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number format")
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @Lob
    private String profilePictureUrl;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "last_login")
    private LocalDateTime lastLogin; // Last login timestamp

    // Relationships

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Notification> notifications = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<LoginHistory> loginHistories = new HashSet<>(); // Login history records

    // Enums


    // Methods

    /**
     * Custom method to add an address to the user.
     */
    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    /**
     * Custom method to remove an address from the user.
     */
    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }

    /**
     * Custom method to add an order to the user.
     */
    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    /**
     * Custom method to remove an order from the user.
     */
    public void removeOrder(Order order) {
        orders.remove(order);
        order.setUser(null);
    }

    /**
     * Record the user's login time.
     */
    public void recordLogin() {
        this.lastLogin = LocalDateTime.now();
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setUser(this);
        loginHistory.setLoginTime(this.lastLogin);
        loginHistories.add(loginHistory);
    }
}
