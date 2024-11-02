package sarik.dev.foodwave.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank
    @Size(max = 100)
    @Column(name = "address_line_1")
    private String addressLine1;

    @Size(max = 100)
    @Column(name = "address_line_2")
    private String addressLine2;

    @NotBlank
    @Size(max = 50)
    private String city;

    @NotBlank
    @Size(max = 50)
    private String state;

    @NotBlank
    @Size(max = 20)
    private String zipCode;

    @NotBlank
    @Size(max = 50)
    private String country;

    @Past
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Size(max = 15)
    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "is_verified", nullable = false)
    private boolean isVerified = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Optional fields for additional information
    @Size(max = 50)
    private String occupation;

    @Size(max = 255)
    private String bio;

    // Contact information
    @Size(max = 50)
    @Column(name = "alternate_phone")
    private String alternatePhone;

    // Methods for managing relationships, if needed
}
