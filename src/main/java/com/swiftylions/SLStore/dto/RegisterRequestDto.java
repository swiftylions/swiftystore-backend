package com.swiftylions.SLStore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDto {
    @NotBlank(message = "Name is Required")
    @Size(min = 3, max = 30, message = "The length of the name should be between 3 to 30 characters")
    private String name;

    @NotBlank(message = "Email is Required")
    @Email(message = "Email address must be a valid value")
    private String email;

    @NotBlank(message = "Mobile number is Required")
    @Pattern(regexp = "^(?:\\+?98|0)?9\\d{9}$", message = "Mobile number must be exactly 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Password is Required")
    @Size(min = 8, max = 20, message = "Password length must be between 8 to 20 characters")
    private String password;
}
