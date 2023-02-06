package com.gov.eco.govministereco.Responses;

import org.springframework.http.ResponseEntity;

public interface ResponsesInter {

    ResponseEntity<LoginResponse> getLoginResponse(LoginResponse loginResponse);
}
