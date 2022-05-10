package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private int role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

//    @NotBlank
    @Size(max = 200, min = 2)
    private String name;

    @NotBlank
    private String phone;
}
