package com.swiftylions.SLStore.service;

import com.swiftylions.SLStore.dto.ProfileRequestDto;
import com.swiftylions.SLStore.dto.ProfileResponseDto;

public interface IProfileService {
    ProfileResponseDto getProfile();

    ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto);
}
