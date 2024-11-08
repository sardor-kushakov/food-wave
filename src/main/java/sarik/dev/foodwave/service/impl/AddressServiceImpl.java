package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.address.AddressCreateDTO;
import sarik.dev.foodwave.dto.address.AddressDTO;
import sarik.dev.foodwave.dto.address.AddressUpdateDTO;
import sarik.dev.foodwave.entity.user.Address;
import sarik.dev.foodwave.entity.user.User;
import sarik.dev.foodwave.repository.AddressRepository;
import sarik.dev.foodwave.repository.UserRepository;
import sarik.dev.foodwave.service.AddressService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public AddressDTO createAddress(AddressCreateDTO addressCreateDTO) {
        User user = userRepository.findById(addressCreateDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Address address = Address.builder()
                .user(user)
                .addressLine1(addressCreateDTO.getAddressLine1())
                .addressLine2(addressCreateDTO.getAddressLine2())
                .city(addressCreateDTO.getCity())
                .state(addressCreateDTO.getState())
                .zipCode(addressCreateDTO.getZipCode())
                .country(addressCreateDTO.getCountry())
                .latitude(addressCreateDTO.getLatitude())
                .longitude(addressCreateDTO.getLongitude())
                .isPrimary(addressCreateDTO.isPrimary())
                .build();

        addressRepository.save(address);
        return AddressDTO.fromEntity(address);
    }

    @Override
    public AddressDTO getAddressById(UUID addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address not found"));
        return AddressDTO.fromEntity(address);
    }

    @Override
    public List<AddressDTO> getAddressesByUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return addressRepository.findByUser(user).stream()
                .map(AddressDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AddressDTO updateAddress(UUID addressId, AddressUpdateDTO addressUpdateDTO) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address not found"));

        address.setAddressLine1(addressUpdateDTO.getAddressLine1());
        address.setAddressLine2(addressUpdateDTO.getAddressLine2());
        address.setCity(addressUpdateDTO.getCity());
        address.setState(addressUpdateDTO.getState());
        address.setZipCode(addressUpdateDTO.getZipCode());
        address.setCountry(addressUpdateDTO.getCountry());
        address.setLatitude(addressUpdateDTO.getLatitude());
        address.setLongitude(addressUpdateDTO.getLongitude());

        addressRepository.save(address);
        return AddressDTO.fromEntity(address);
    }

    @Override
    @Transactional
    public void deleteAddress(UUID addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address not found"));
        addressRepository.delete(address);
    }

    @Override
    @Transactional
    public void setPrimaryAddress(UUID addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address not found"));

        addressRepository.findByUserAndIsPrimary(address.getUser(), true).ifPresent(existingPrimary -> {
            existingPrimary.setPrimary(false);
            addressRepository.save(existingPrimary);
        });

        address.setAsPrimary();
        addressRepository.save(address);
    }
}
