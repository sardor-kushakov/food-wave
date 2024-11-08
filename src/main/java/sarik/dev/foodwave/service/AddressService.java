package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.address.AddressCreateDTO;
import sarik.dev.foodwave.dto.address.AddressDTO;
import sarik.dev.foodwave.dto.address.AddressUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface AddressService {

    AddressDTO createAddress(AddressCreateDTO addressCreateDTO);

    AddressDTO getAddressById(UUID addressId);

    List<AddressDTO> getAddressesByUser(UUID userId);

    AddressDTO updateAddress(UUID addressId, AddressUpdateDTO addressUpdateDTO);

    void deleteAddress(UUID addressId);

    void setPrimaryAddress(UUID addressId);
}
