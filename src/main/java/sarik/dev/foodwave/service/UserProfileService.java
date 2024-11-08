package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.user.profile.UserProfileCreateDTO;
import sarik.dev.foodwave.dto.user.profile.UserProfileDTO;
import sarik.dev.foodwave.dto.user.profile.UserProfileUpdateDTO;

import java.util.UUID;

public interface UserProfileService {

    UserProfileDTO createUserProfile(UserProfileCreateDTO userProfileCreateDTO);

    UserProfileDTO getUserProfileById(UUID profileId);

    UserProfileDTO getUserProfileByUserId(UUID userId);

    UserProfileDTO updateUserProfile(UUID profileId, UserProfileUpdateDTO userProfileUpdateDTO);

    void deleteUserProfile(UUID profileId);

    void verifyUserProfile(UUID profileId);
}
