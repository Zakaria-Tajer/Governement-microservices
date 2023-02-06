package com.gov.eco.govministereco.Responses;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Responses implements ResponsesInter{

    @Override
    public ResponseEntity<LoginResponse> getLoginResponse(LoginResponse loginResponse) {
        return ResponseEntity.ok().body(
                new LoginResponse(
                        loginResponse.getTimestamp(),
                        loginResponse.getToken(),
                        loginResponse.getStatusCode(),
                        loginResponse.getMessage()
                )
        );
    }
}
