package com.swiftylions.SLStore.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequestDto {

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3,max = 30,message = "Name must be in the range of 3 to 30 characters")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Mobile number cannot be empty")
    @Pattern(regexp = "^(?:\\+?98|0)?9\\d{9}$",message = "Invalid mobile number. Please enter real mobile number")
    private String mobileNumber;

    @NotBlank(message = "Message cannot be empty")
    @Size(min = 5,max = 500,message = "Message must be between 5 to 500 characters")
    private String message;
}