package sarik.dev.foodwave.dto.address;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.entity.user.Address;

import java.util.UUID;

@Data
@Builder
public class AddressDTO {

    private UUID id;
    private UUID userId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String latitude;
    private String longitude;
    private boolean isPrimary;

    // Entitydan DTOga aylantiruvchi metod
    public static AddressDTO fromEntity(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .userId(address.getUser().getId()) // User ID ni olish
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(address.getZipCode())
                .country(address.getCountry())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .isPrimary(address.isPrimary())
                .build();
    }
}
