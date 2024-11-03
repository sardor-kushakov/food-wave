package sarik.dev.foodwave.dto.address;

import lombok.Data;
import sarik.dev.foodwave.dto.user.UserDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AddressDTO {

    private UUID id;
    private UserDTO user;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private boolean isPrimary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String latitude;
    private String longitude;
}
