package com.uncode.stop.rest_api.security;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticationRequest {
    @NotBlank
    private String username;
    @NotBlank
    @Length(min = 6)
    private String password;
}
