package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty @Size(min = 6, max = 12)
    private String password;
    private String roles;
}





