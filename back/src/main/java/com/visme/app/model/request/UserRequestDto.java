package com.visme.app.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private Long id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;
}
