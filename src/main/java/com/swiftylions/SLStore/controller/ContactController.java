package com.swiftylions.SLStore.controller;

import com.swiftylions.SLStore.dto.ContactInfoDto;
import com.swiftylions.SLStore.dto.ContactRequestDto;
import com.swiftylions.SLStore.dto.ContactResponseDto;
import com.swiftylions.SLStore.service.IContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final IContactService iContactService;
    private final ContactInfoDto contactInfoDto;

    @PostMapping
    public ResponseEntity<String> saveContact(
            @Valid @RequestBody ContactRequestDto contactRequestDto) {//DTO Pattern
        iContactService.saveContact(contactRequestDto);
//        throw new RuntimeException("Oops something bad happened");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Request processed successfully");

    }

    @GetMapping
    public ResponseEntity<ContactInfoDto> getContactInfo() {
        return ResponseEntity.ok(contactInfoDto);
    }
}
