package com.gov.justice.govministerjustice.dto;


import com.gov.justice.govministerjustice.enums.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminLoginDto {
    private int ministerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Roles role;

}
