package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.user.profile.UserProfileCreateDTO;
import sarik.dev.foodwave.dto.user.profile.UserProfileDTO;
import sarik.dev.foodwave.dto.user.profile.UserProfileUpdateDTO;
import sarik.dev.foodwave.entity.user.User;
import sarik.dev.foodwave.entity.user.UserProfile;
import sarik.dev.foodwave.repository.UserProfileRepository;
import sarik.dev.foodwave.repository.UserRepository;
import sarik.dev.foodwave.service.UserProfileService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserProfileDTO createUserProfile(UserProfileCreateDTO userProfileCreateDTO) {
        User user = userRepository.findById(userProfileCreateDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        UserProfile userProfile = UserProfile.builder()
                .user(user)
                .addressLine1(userProfileCreateDTO.getAddressLine1())
                .addressLine2(userProfileCreateDTO.getAddressLine2())
                .city(userProfileCreateDTO.getCity())
                .state(userProfileCreateDTO.getState())
                .zipCode(userProfileCreateDTO.getZipCode())
                .country(userProfileCreateDTO.getCountry())
                .dateOfBirth(userProfileCreateDTO.getDateOfBirth())
                .nationalId(userProfileCreateDTO.getNationalId())
                .profilePictureUrl(userProfileCreateDTO.getProfilePictureUrl())
                .occupation(userProfileCreateDTO.getOccupation())
                .bio(userProfileCreateDTO.getBio())
                .alternatePhone(userProfileCreateDTO.getAlternatePhone())
                .isVerified(false)
                .build();

        userProfileRepository.save(userProfile);
        return UserProfileDTO.fromEntity(userProfile);
    }

    @Override
    public UserProfileDTO getUserProfileById(UUID profileId) {
        UserProfile userProfile = userProfileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("User profile not found"));
        return UserProfileDTO.fromEntity(userProfile);
    }

    @Override
    public UserProfileDTO getUserProfileByUserId(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        UserProfile userProfile = userProfileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("User profile not found"));

        return UserProfileDTO.fromEntity(userProfile);
    }

    @Override
    @Transactional
    public UserProfileDTO updateUserProfile(UUID profileId, UserProfileUpdateDTO userProfileUpdateDTO) {
        UserProfile userProfile = userProfileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("User profile not found"));

        userProfile.setAddressLine1(userProfileUpdateDTO.getAddressLine1());
        userProfile.setAddressLine2(userProfileUpdateDTO.getAddressLine2());
        userProfile.setCity(userProfileUpdateDTO.getCity());
        userProfile.setState(userProfileUpdateDTO.getState());
        userProfile.setZipCode(userProfileUpdateDTO.getZipCode());
        userProfile.setCountry(userProfileUpdateDTO.getCountry());
        userProfile.setDateOfBirth(userProfileUpdateDTO.getDateOfBirth());
        userProfile.setNationalId(userProfileUpdateDTO.getNationalId());
        userProfile.setProfilePictureUrl(userProfileUpdateDTO.getProfilePictureUrl());
        userProfile.setOccupation(userProfileUpdateDTO.getOccupation());
        userProfile.setBio(userProfileUpdateDTO.getBio());
        userProfile.setAlternatePhone(userProfileUpdateDTO.getAlternatePhone());

        userProfileRepository.save(userProfile);
        return UserProfileDTO.fromEntity(userProfile);
    }

    @Override
    @Transactional
    public void deleteUserProfile(UUID profileId) {
        userProfileRepository.deleteById(profileId);
    }

    @Override
    @Transactional
    public void verifyUserProfile(UUID profileId) {
        UserProfile userProfile = userProfileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("User profile not found"));

        userProfile.setVerified(true);
        userProfileRepository.save(userProfile);
    }
}
