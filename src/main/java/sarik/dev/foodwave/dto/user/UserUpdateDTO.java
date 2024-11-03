package sarik.dev.foodwave.dto.user;

import lombok.Data;
import sarik.dev.foodwave.enums.user.Gender;
import sarik.dev.foodwave.enums.user.UserRole;

import java.util.UUID;

@Data
public class UserUpdateDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private UserRole userRole;
    private String profilePictureUrl;
    private boolean isActive;
}
