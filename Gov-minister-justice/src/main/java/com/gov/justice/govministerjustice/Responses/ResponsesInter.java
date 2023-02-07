package com.gov.justice.govministerjustice.Responses;

import org.springframework.http.ResponseEntity;

public interface ResponsesInter {

    ResponseEntity<LoginResponse> getLoginResponse(LoginResponse loginResponse);
}
