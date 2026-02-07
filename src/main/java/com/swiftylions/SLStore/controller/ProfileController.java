package com.swiftylions.SLStore.controller;

import com.swiftylions.SLStore.dto.ProfileRequestDto;
import com.swiftylions.SLStore.dto.ProfileResponseDto;
import com.swiftylions.SLStore.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final IProfileService iprofileService;

    @GetMapping
    public ResponseEntity<ProfileResponseDto> getProfile(){
        ProfileResponseDto responseDto =  iprofileService.getProfile();
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping
    public ResponseEntity<ProfileResponseDto> updateProfile(
            @Validated @RequestBody ProfileRequestDto profileRequestDto){
        ProfileResponseDto responseDto = iprofileService.updateProfile(profileRequestDto);
        return ResponseEntity.ok(responseDto);
    }
}
