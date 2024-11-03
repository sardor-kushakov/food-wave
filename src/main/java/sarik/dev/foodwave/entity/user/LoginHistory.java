package sarik.dev.foodwave.entity.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "login_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "login_time", updatable = false)
    @CreationTimestamp
    private LocalDateTime loginTime;

    @Column(name = "ip_address")
    private String ipAddress; // Foydalanuvchining IP manzili

    @Column(name = "device_info")
    private String deviceInfo; // Foydalanuvchi qurilmasi haqidagi ma'lumot

    // Methods

    /**
     * Retrieves a summary of the login history.
     */
    public String getLoginSummary() {
        return String.format("User ID: %s, Login Time: %s, IP Address: %s, Device: %s",
                user.getId(), loginTime, ipAddress, deviceInfo);
    }
}
