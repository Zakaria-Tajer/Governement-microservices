package com.gov.eco.govministereco.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private Date timestamp;
    private String token;
    private int statusCode;
    private String message;

}
